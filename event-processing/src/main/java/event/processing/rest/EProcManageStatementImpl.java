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

import common.data.ConfigurationDelegation;
import common.data.Connection;
import common.data.builder.CDBuilder;
import common.data.setting.SettingUtils;
import common.data.type.COMPONENT_TYPE;
import common.rest.RESOURCE_NAMING;
import common.rest.ResourceUtils;
import event.processing.engine.EngineFactory;
import event.processing.engine.EngineListener;
import event.processing.engine.impl.EsperEngineListener;
import event.processing.query.Query;
import event.processing.query.QueryFactory;
import event.processing.repo.QueryRepository;
import event.processing.repo.RuleRepository;
import event.processing.rule.Rule;
import event.processing.rule.RuleFactory;
import event.processing.rule.model.Reaction;
import event.processing.status.STATUS_TYPE;
import event.processing.status.Status;

@RestController
public class EProcManageStatementImpl implements EProcManageStatement {

    private static final Logger logger = LoggerFactory.getLogger(EProcManageStatementImpl.class);

    @Autowired
    @Qualifier("esper")
    private EngineFactory factory;

    @Autowired
    private QueryRepository queryRepository;

    @Autowired
    private RuleRepository ruleRepository;

    @Autowired
    private QueryFactory queryFactory;

    @Autowired
    private RuleFactory ruleFactory;

    @Autowired
    private Status status;

    @Override
    @RequestMapping(value = "/registrations/query/{name}", method = RequestMethod.POST)
    public ResponseEntity<String> registerQuery(@PathVariable("name") String name, @RequestBody String query) {
        logger.info(ResourceUtils.getLogMessage(RESOURCE_NAMING.EPROCESSING_REGISTRATION_QUERY));

        /**
         * Make sure that parameter 'name' is not empty or that it isn't already awarded.
         */
        if (StringUtils.isEmpty(name)) {
            return EP_ERROR_CODES.ERROR_MISSING_QUERY_NAME.getErrorResponse();
        } else if ((null != queryRepository.findOne(name))) {
            return EP_ERROR_CODES.ERROR_EXISTING_QUERY.getErrorResponse();
        }

        /**
         * Parse query and store it in the repository.
         */
        try {
            Query q = queryFactory.parse(query);
            queryRepository.save(name, q);

        } catch (Exception e) {
            return EP_ERROR_CODES.ERROR_PARSING_QUERY.getErrorResponse();
        }

        return new ResponseEntity<String>(OK, HttpStatus.OK);

    }

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
        try {
            Rule r = ruleFactory.parse(rule);
            ruleRepository.save(name, r);
        } catch (Exception e) {
            return EP_ERROR_CODES.ERROR_PARSING_RULE.getErrorResponse();
        }

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
        Query query = queryRepository.findOne(rule.getQuery());
        if (null == query) {
            return EP_ERROR_CODES.ERROR_NON_EXISTING_QUERY.getErrorResponse();
        }

        /**
         * Register query at event engine.
         */
        try {
            List<String> epls = factory.getTransformer().transformQuery(query);

            EngineListener engineListener = factory.getEngineListener();
            ((EsperEngineListener) engineListener).addRuleListener(rule);

            factory.getEngine().register(epls, engineListener);

            RestTemplate restTemplate = new RestTemplate();

            Connection local = SettingUtils.getLocalConnection();
            local.setComponentType(COMPONENT_TYPE.EVENT_PROCESSING);
            Connection cm = SettingUtils.getCMConnection();

            String url = ResourceUtils.getUrl(RESOURCE_NAMING.CMGMT_DELEGATION, cm);

            if (STATUS_TYPE.TEST.equals(status.getCurrent())) {
                /**
                 * Is instance in test modus, don't do anything. Normally, CM wouldn't be online and test fails.
                 */
            } else {

                for (Reaction reaction : rule.getReactions()) {

                    /**
                     * First configuration change is used for startup. Therefore ignore property part in reaction.
                     */

                    CDBuilder cDBuilder = new CDBuilder();
                    cDBuilder.buildDeviceInformation(reaction.getDeviceInformation())
                            //
                            .buildDomainInformation(reaction.getDomainInformation())
                            //
                            .buildConfigurationModification(local, reaction.getDeviceInformation());

                    restTemplate.postForEntity(url, cDBuilder.getResult(), ConfigurationDelegation.class);
                }
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
        Query query = queryRepository.findOne(rule.getQuery());
        if (null == query) {
            return EP_ERROR_CODES.ERROR_NON_EXISTING_QUERY.getErrorResponse();
        }

        /**
         * Destroy query at event engine.
         */
        try {
            List<String> epls = factory.getTransformer().transformQuery(query);
            factory.getEngine().unregister(epls);
        } catch (Exception e) {
            System.out.println(e);
            return EP_ERROR_CODES.ERROR_DEACTIVATE.getErrorResponse();
        }

        rule.setIsActivated(false);
        return new ResponseEntity<String>(OK, HttpStatus.OK);
    }

    @Override
    @RequestMapping(value = "/deregistrations/query/{name}", method = RequestMethod.DELETE)
    public ResponseEntity<String> withdrawQuery(@PathVariable("name") String name) {
        logger.info(ResourceUtils.getLogMessage(RESOURCE_NAMING.EPROCESSING_DEREGISTRATION_QUERY));

        /**
         * Make sure that parameter 'name' is not empty.
         */
        if (StringUtils.isEmpty(name)) {
            return EP_ERROR_CODES.ERROR_MISSING_QUERY_NAME.getErrorResponse();
        }

        /**
         * Make sure that corresponding query exists.
         * 
         */
        Query query = queryRepository.findOne(name);
        if (null == query) {
            return EP_ERROR_CODES.ERROR_NON_EXISTING_QUERY.getErrorResponse();
        }

        /**
         * Make sure that query isn't assigned to a rule.
         * 
         */
        if (!CollectionUtils.isEmpty(ruleRepository.findRulesByQueryName(name))) {
            return EP_ERROR_CODES.ERROR_DEREGISTER_ASSIGNED.getErrorResponse();
        }

        ruleRepository.delete(name);
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
    @RequestMapping(value = "/registrations/queries", method = RequestMethod.GET)
    public @ResponseBody List<Query> getAllQueries() {
        logger.info(ResourceUtils.getLogMessage(RESOURCE_NAMING.EPROCESSING_GET_ALL_QUERIES));

        return queryRepository.findAll();
    }

    @Override
    @RequestMapping(value = "/registrations/rules", method = RequestMethod.GET)
    public @ResponseBody List<Rule> getAllRules() {
        logger.info(ResourceUtils.getLogMessage(RESOURCE_NAMING.EPROCESSING_GET_ALL_RULES));

        return ruleRepository.findAll();
    }
}
