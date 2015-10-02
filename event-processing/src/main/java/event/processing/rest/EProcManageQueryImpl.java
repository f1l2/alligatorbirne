package event.processing.rest;

import java.io.IOException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import common.rest.RESOURCE_NAMING;
import common.rest.UtilsResource;
import event.processing.engine.Engine;
import event.processing.engine.EngineFactory;
import event.processing.engine.QueryTransformer;
import event.processing.query.Query;
import event.processing.query.QueryFactory;
import event.processing.repo.QueryRepository;
import event.processing.rule.Rule;
import event.processing.rule.RuleFactory;

@RestController
public class EProcManageQueryImpl implements EProcManageQuery {

    private static final Logger logger = LoggerFactory.getLogger(EProcManageQueryImpl.class);

    @Autowired
    @Qualifier("esper")
    private EngineFactory factory;

    @Autowired
    private QueryFactory queryFactory;

    @Autowired
    private RuleFactory ruleFactory;

    @Autowired
    private QueryRepository queryRepository;

    @Override
    @RequestMapping(value = "/registrations/query", method = RequestMethod.POST)
    public ResponseEntity<String> register(@RequestBody String query) {
        logger.info(UtilsResource.getLogMessage(RESOURCE_NAMING.EPROCESSING_REGISTRATION_QUERY));

        try {
            Engine engine = factory.getEngine();

            QueryTransformer qT = factory.getQueryTransformer();
            List<String> nativeQueries = qT.transform(query);

            engine.registerQuery(nativeQueries, factory.getEngineListener());

        } catch (Exception e) {
            logger.error("Couldn't parse query string. Please check correctness of query.");
            return new ResponseEntity<String>("Couldn't parse query string. Please check correctness of query.", HttpStatus.NOT_ACCEPTABLE);
        }

        return new ResponseEntity<String>("ok", HttpStatus.OK);

    }

    @Override
    @RequestMapping(value = "/unregistrations/query", method = RequestMethod.POST)
    public void unregister(@RequestBody String query) {
        logger.info(UtilsResource.getLogMessage(RESOURCE_NAMING.EPROCESSING_UNREGISTRATION_QUERY));

        Engine engine = factory.getEngine();
        engine.unregisterQuery(query);
    }

    @Override
    @RequestMapping(value = "/registrations/rule", method = RequestMethod.POST)
    public void registerRule(@RequestBody String rule) {
        logger.info(UtilsResource.getLogMessage(RESOURCE_NAMING.EPROCESSING_REGISTRATION_RULE));

        try {
            Rule r = ruleFactory.parse(rule);
        } catch (IOException e) {
            logger.error("Couldn't parse rule string. Please check correctness of rule.");
            throw new IllegalArgumentException("Couldn't parse rule string. Please check coorectness of rule.");
        }

    }

    @Override
    @RequestMapping(value = "/unregistrations/rule", method = RequestMethod.POST)
    public void unregisterRule(@RequestBody String rule) {
        logger.info(UtilsResource.getLogMessage(RESOURCE_NAMING.EPROCESSING_UNREGISTRATION_RULE));

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

        return null;
    }

}
