package event.processing.rest;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import common.rest.RESOURCE_NAMING;
import common.rest.UtilsResource;
import event.processing.engine.EngineFactory;
import event.processing.engine.impl.EsperEngineListener;
import event.processing.query.Query;
import event.processing.query.QueryFactory;
import event.processing.repo.QueryRepository;
import event.processing.repo.RuleRepository;
import event.processing.rule.Rule;
import event.processing.rule.RuleFactory;

@RestController
public class EProcManageQueryImpl implements EProcManageQuery {

    private static final Logger logger = LoggerFactory.getLogger(EProcManageQueryImpl.class);

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

    @Override
    @RequestMapping(value = "/registrations/query/{name}", method = RequestMethod.POST)
    public ResponseEntity<String> registerQuery(@PathVariable("name") String name, @RequestBody String query) {
        logger.info(UtilsResource.getLogMessage(RESOURCE_NAMING.EPROCESSING_REGISTRATION_QUERY));

        /**
         * Check if name of query is empty or already awarded.
         */
        if (StringUtils.isEmpty(name)) {
            logger.error(ERROR_MISSING_QUERY_NAME);
            return new ResponseEntity<String>(ERROR_MISSING_QUERY_NAME, HttpStatus.INTERNAL_SERVER_ERROR);
        } else if ((null != queryRepository.findOne(name))) {
            logger.error(ERROR_EXISTING_QUERY);
            return new ResponseEntity<String>(ERROR_EXISTING_QUERY, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        /**
         * Parse query and save it.
         */
        try {
            Query q = queryFactory.parse(query);
            queryRepository.save(name, q);

        } catch (Exception e) {
            logger.error(ERROR_PARSING_QUERY);
            return new ResponseEntity<String>(ERROR_PARSING_QUERY, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<String>(OK, HttpStatus.OK);

    }

    @Override
    @RequestMapping(value = "/registrations/rule/{name}", method = RequestMethod.POST)
    public ResponseEntity<String> registerRule(@PathVariable("name") String name, @RequestBody String rule) {
        logger.info(UtilsResource.getLogMessage(RESOURCE_NAMING.EPROCESSING_REGISTRATION_RULE));

        /**
         * Check if name of rule is empty or already awarded.
         */
        if (StringUtils.isEmpty(name)) {
            logger.error(ERROR_MISSING_RULE_NAME);
            return new ResponseEntity<String>(ERROR_MISSING_RULE_NAME, HttpStatus.INTERNAL_SERVER_ERROR);
        } else if ((null != ruleRepository.findOne(name))) {
            logger.error(ERROR_EXISTING_RULE);
            return new ResponseEntity<String>(ERROR_EXISTING_RULE, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        /**
         * Parse rule and save it.
         */
        try {
            Rule r = ruleFactory.parse(rule);
            ruleRepository.save(name, r);

        } catch (Exception e) {
            logger.error(ERROR_PARSING_RULE);
            return new ResponseEntity<String>(ERROR_PARSING_RULE, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<String>(OK, HttpStatus.OK);
    }

    @Override
    @RequestMapping(value = "/activations/rule/{name}", method = RequestMethod.POST)
    public ResponseEntity<String> activateRule(@PathVariable("name") String name) {
        logger.info(UtilsResource.getLogMessage(RESOURCE_NAMING.EPROCESSING_ACTIVATIONS_RULE));

        /**
         * Check if name of rule is empty and already exists.
         */

        if (StringUtils.isEmpty(name)) {
            logger.error(ERROR_MISSING_RULE_NAME);
            return new ResponseEntity<String>(ERROR_MISSING_RULE_NAME, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        Rule rule = ruleRepository.findOne(name);
        if (null == rule) {
            logger.error(ERROR_NON_EXISTING_RULE);
            return new ResponseEntity<String>(ERROR_NON_EXISTING_RULE, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        /**
         * Check if corresponding rule exists.
         */

        Query query = queryRepository.findOne(rule.getQuery());
        if (null == query) {
            logger.error(ERROR_NON_EXISTING_QUERY + " {}", rule.getQuery());
            return new ResponseEntity<String>(ERROR_NON_EXISTING_QUERY, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        /**
         * Register query at event engine.
         */

        try {
            List<String> epls = factory.getTransformer().transformQuery(query);

            EsperEngineListener engineListener = (EsperEngineListener) factory.getEngineListener();
            engineListener.addRuleListener(rule);

            factory.getEngine().register(epls, engineListener);
        } catch (Exception e) {
            logger.error(ERROR_REGISTER_QUERY);
            return new ResponseEntity<String>(ERROR_REGISTER_QUERY, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<String>(OK, HttpStatus.OK);
    }

    @Override
    @RequestMapping(value = "/unregistrations/query", method = RequestMethod.POST)
    public ResponseEntity<String> unregisterQuery(@RequestBody String query) {
        logger.info(UtilsResource.getLogMessage(RESOURCE_NAMING.EPROCESSING_UNREGISTRATION_QUERY));

        // TODO

        return null;
    }

    @Override
    @RequestMapping(value = "/unregistrations/rule", method = RequestMethod.POST)
    public ResponseEntity<String> unregisterRule(@RequestBody String rule) {
        logger.info(UtilsResource.getLogMessage(RESOURCE_NAMING.EPROCESSING_UNREGISTRATION_RULE));

        // TODO

        return null;
    }

    @Override
    @RequestMapping(value = "/registrations/queries", method = RequestMethod.GET)
    public @ResponseBody List<Query> getAllQueries() {
        logger.info(UtilsResource.getLogMessage(RESOURCE_NAMING.EPROCESSING_GET_ALL_QUERIES));

        return queryRepository.findAll();
    }

    @Override
    @RequestMapping(value = "/registrations/rules", method = RequestMethod.GET)
    public @ResponseBody List<Rule> getAllRules() {
        logger.info(UtilsResource.getLogMessage(RESOURCE_NAMING.EPROCESSING_GET_ALL_RULES));

        return ruleRepository.findAll();
    }

}
