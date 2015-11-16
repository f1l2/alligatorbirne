package event.processing.rest;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import common.data.Connection;
import common.data.builder.CDBuilder;
import common.data.setting.SettingUtils;
import common.data.type.COMPONENT_TYPE;
import common.rest.RESOURCE_NAMING;
import common.rest.ResourceUtils;
import event.processing.ApplicationScheduler;
import event.processing.engine.EngineFactory;
import event.processing.engine.EngineListener;
import event.processing.engine.impl.EsperEngineListener;
import event.processing.messaging.MessageHandlerImpl;
import event.processing.messaging.MessageHandlerListener;
import event.processing.query.Query;
import event.processing.repo.QueryRepository;
import event.processing.repo.RuleRepository;
import event.processing.rule.Rule;
import event.processing.rule.RuleFactory;
import event.processing.rule.model.Reaction;
import event.processing.status.STATUS_TYPE;
import event.processing.status.Status;
import event.processing.utilities.Utilities;

@RestController
public class EProcManageRuleImpl implements EProcManageRule {

    private static final Logger logger = LoggerFactory.getLogger(EProcManageRuleImpl.class);

    @Autowired
    @Qualifier("esper")
    private EngineFactory factory;

    @Autowired
    private QueryRepository queryRepository;

    @Autowired
    private RuleRepository ruleRepository;

    @Autowired
    private RuleFactory ruleFactory;

    @Autowired
    private MessageHandlerImpl messageHandler;

    @Autowired
    protected MessageHandlerListener messageHandlerListener;

    @Autowired
    private Status status;

    private Connection local, cm, mb;

    @Override
    @RequestMapping(value = "/registrations/rule/{name}", method = RequestMethod.POST)
    public ResponseEntity<String> registerRule(@PathVariable("name") String name, @RequestBody String rule) {
        logger.info(ResourceUtils.getLogMessage(RESOURCE_NAMING.EPROCESSING_REGISTRATION_RULE));

        /**
         * Make sure that parameter 'name' is not empty or that it isn't already awarded.
         */
        if (StringUtils.isEmpty(name)) {
            return EP_ERROR_CODES.ERROR_MISSING_RULE_NAME.getErrorResponse();
        } else if ((null != ruleRepository.findOne(name))) {
            return EP_ERROR_CODES.ERROR_EXISTING_RULE.getErrorResponse();
        }

        /**
         * Parse rule and store it in the repository.
         */
        Rule r = null;
        try {
            r = ruleFactory.parse(rule);
        } catch (Exception e) {
            return EP_ERROR_CODES.ERROR_PARSING_RULE.getErrorResponse();
        }

        /**
         * Find query to name. If query doesn't exist throw exception.
         */

        try {
            Utilities.findQueriesToQueryNames(r, queryRepository);
        } catch (Exception e) {
            return EP_ERROR_CODES.ERROR_NON_EXISTING_QUERY.getErrorResponse();
        }

        ruleRepository.save(name, r);

        return new ResponseEntity<String>(OK, HttpStatus.OK);
    }

    @Override
    @RequestMapping(value = "/activations/rule/{name}", method = RequestMethod.GET)
    public ResponseEntity<String> activateRule(@PathVariable("name") String name) {
        logger.info(ResourceUtils.getLogMessage(RESOURCE_NAMING.EPROCESSING_ACTIVATIONS_RULE));

        /**
         * Make sure that parameter 'name' is not empty.
         */
        if (StringUtils.isEmpty(name)) {
            return EP_ERROR_CODES.ERROR_MISSING_RULE_NAME.getErrorResponse();
        }

        /**
         * Make sure that rule with given name exists.
         */
        Rule rule = ruleRepository.findOne(name);
        if (null == rule) {
            return EP_ERROR_CODES.ERROR_NON_EXISTING_RULE.getErrorResponse();
        }

        /**
         * Make sure that corresponding query exists.
         */
        List<Query> queries = queryRepository.findAllByQueries(rule.getQueries());
        if (null == queries) {
            return EP_ERROR_CODES.ERROR_NON_EXISTING_QUERY.getErrorResponse();
        }

        /**
         * Register query at event engine.
         */
        try {

            loadConnections();

            List<Query> notActiveQueries = Utilities.filterActiveQueries(rule.getQueries(), ruleRepository);
            List<String> epls = factory.getTransformer().transformQuery(notActiveQueries);

            epls.addAll(factory.getTransformer().transformRulePuristic(rule));

            EngineListener engineListener = factory.getEngineListener();
            ((EsperEngineListener) engineListener).addRuleListener(rule);

            factory.getEngine().register(epls, engineListener);

            String url = ResourceUtils.getUrl(RESOURCE_NAMING.CMGMT_REGISTER_EVENT_PROCESSING_SOURCES, cm, ApplicationScheduler.id);

            if (STATUS_TYPE.TEST.equals(status.getCurrent())) {
                /**
                 * Is instance in test modus, don't do anything. Normally, CM wouldn't be online and test fails.
                 */
            } else {

                final CDBuilder cdBuilder = new CDBuilder();
                cdBuilder.buildDataSink(local);

                final RestTemplate restTemplate = new RestTemplate();
                for (Reaction reaction : rule.getReactions()) {
                    cdBuilder.addDataSource(reaction.getDeviceInformation(), reaction.getDomainInformation());
                }

                restTemplate.postForEntity(url, cdBuilder.getResult(), String.class);

                messageHandler.start(mb);
                messageHandler.consume(Utilities.createSelectorForActiveRules(ruleRepository), messageHandlerListener);

            }

        } catch (Exception e) {
            logger.error("{}", e);
            return EP_ERROR_CODES.ERROR_ACTIVATE.getErrorResponse();
        }

        rule.setIsActivated(true);
        return new ResponseEntity<String>(OK, HttpStatus.OK);
    }

    @Override
    @RequestMapping(value = "/deactivations/rule/{name}", method = RequestMethod.GET)
    public ResponseEntity<String> deactivateRule(@PathVariable("name") String name) {
        logger.info(ResourceUtils.getLogMessage(RESOURCE_NAMING.EPROCESSING_DEACTIVATIONS_RULE));

        /**
         * Make sure that parameter 'name' is not empty.
         */
        if (StringUtils.isEmpty(name)) {
            return EP_ERROR_CODES.ERROR_MISSING_RULE_NAME.getErrorResponse();
        }

        /**
         * Make sure that rule with given name exists.
         */
        Rule rule = ruleRepository.findOne(name);
        if (null == rule) {
            return EP_ERROR_CODES.ERROR_NON_EXISTING_RULE.getErrorResponse();
        }

        /**
         * Make sure that corresponding query exists.
         * 
         */
        List<Query> queries = queryRepository.findAllByQueries(rule.getQueries());
        if (CollectionUtils.isEmpty(queries)) {
            return EP_ERROR_CODES.ERROR_NON_EXISTING_QUERY.getErrorResponse();
        }

        /**
         * Destroy query at event engine.
         */
        try {

            loadConnections();

            List<String> epls = factory.getTransformer().transformQuery(queries);
            factory.getEngine().deregister(epls);

            String url = ResourceUtils.getUrl(RESOURCE_NAMING.CMGMT_DEREGISTER_EVENT_PROCESSING_SOURCES, cm, ApplicationScheduler.id);

            if (STATUS_TYPE.TEST.equals(status.getCurrent())) {
                /**
                 * Is instance in test modus, don't do anything. Normally, CM wouldn't be online and test fails.
                 */
            } else {

                final CDBuilder cdBuilder = new CDBuilder();
                cdBuilder.buildDataSink(local);
                rule.getReactions().forEach(item -> cdBuilder.addDataSource(item.getDeviceInformation(), item.getDomainInformation()));

                RestTemplate restTemplate = new RestTemplate();
                restTemplate.postForEntity(url, cdBuilder.getResult(), String.class);
            }

            rule.setIsActivated(false);
            ruleRepository.save(name, rule);

        } catch (Exception e) {
            return EP_ERROR_CODES.ERROR_DEACTIVATE.getErrorResponse();
        }

        return new ResponseEntity<String>(OK, HttpStatus.OK);
    }

    @Override
    @RequestMapping(value = "/deregistrations/rule/{name}", method = RequestMethod.DELETE)
    public ResponseEntity<String> withdrawRule(@PathVariable("name") String name) {
        logger.info(ResourceUtils.getLogMessage(RESOURCE_NAMING.EPROCESSING_DEREGISTRATION_RULE));

        /**
         * Make sure that parameter 'name' is not empty.
         */
        if (StringUtils.isEmpty(name)) {
            return EP_ERROR_CODES.ERROR_MISSING_RULE_NAME.getErrorResponse();
        }

        /**
         * Make sure that rule with given name exists.
         */
        Rule rule = ruleRepository.findOne(name);
        if (null == rule) {
            return EP_ERROR_CODES.ERROR_NON_EXISTING_RULE.getErrorResponse();
        }

        /**
         * Before deregistration make sure that rule is not active.
         */
        if (rule.getIsActivated()) {
            return EP_ERROR_CODES.ERROR_DEREGISTER_ACTIVE.getErrorResponse();
        }

        this.ruleRepository.delete(name);

        return new ResponseEntity<String>(OK, HttpStatus.OK);
    }

    @Override
    @RequestMapping(value = "/registrations/rules", method = RequestMethod.GET)
    public @ResponseBody List<Rule> getAllRules() {
        logger.info(ResourceUtils.getLogMessage(RESOURCE_NAMING.EPROCESSING_GET_ALL_RULES));

        return ruleRepository.findAll();
    }

    private void loadConnections() {
        try {
            local = SettingUtils.getLocalConnection();
            local.setComponentType(COMPONENT_TYPE.EVENT_PROCESSING);
            cm = SettingUtils.getCMConnection();

            mb = SettingUtils.getMBConnection();

        } catch (Exception e) {
            logger.error("Error retrieving settings.");
        }
    }
}
