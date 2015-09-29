package event.processing.rest;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import common.rest.RESOURCE_NAMING;
import common.rest.UtilsResource;
import event.processing.engine.Engine;
import event.processing.engine.EngineFactory;
import event.processing.engine.EngineListener;
import event.processing.engine.QueryTransformer;
import event.processing.query.Query;
import event.processing.query.QueryFactory;
import event.processing.rule.Rule;
import event.processing.rule.RuleFactory;

@RestController
public class EProcManageQueryImpl implements EProcManageQuery {

    private static final Logger logger = LoggerFactory.getLogger(EProcManageQueryImpl.class);

    @Autowired
    @Qualifier("esper")
    private EngineFactory factory;

    @Autowired
    private QueryFactory qf;

    @Autowired
    private RuleFactory rf;

    @Override
    @RequestMapping(value = "/registration/query", method = RequestMethod.POST)
    public void register(@RequestBody String query) {

        logger.info(UtilsResource.getLogMessage(RESOURCE_NAMING.EPROCESSING_REGISTRATION_QUERY));

        try {
            Query q = qf.parse(query);
        } catch (IOException e) {
            logger.error("Couldn't parse query string. Please check correctness of query.");
            throw new IllegalArgumentException("Couldn't parse string. Please check coorectness of query.");
        }

        Engine engine = factory.getEngine();

        EngineListener engineListener = factory.getEngineListener();

        QueryTransformer queryTransformer = factory.getQueryTransformer();

        engine.registerQuery(queryTransformer.transform(query), engineListener);

    }

    @Override
    @RequestMapping(value = "/unregistration/query", method = RequestMethod.POST)
    public void unregister(@RequestBody String query) {

        logger.info(UtilsResource.getLogMessage(RESOURCE_NAMING.EPROCESSING_UNREGISTRATION_QUERY));

        Engine engine = factory.getEngine();
        engine.unregisterQuery(query);
    }

    @Override
    @RequestMapping(value = "/registration/rule", method = RequestMethod.POST)
    public void registerRule(@RequestBody String rule) {

        logger.info(UtilsResource.getLogMessage(RESOURCE_NAMING.EPROCESSING_REGISTRATION_RULE));

        try {
            Rule r = rf.parse(rule);
        } catch (IOException e) {
            logger.error("Couldn't parse rule string. Please check correctness of rule.");
            throw new IllegalArgumentException("Couldn't parse rule string. Please check coorectness of rule.");
        }

    }

    @Override
    @RequestMapping(value = "/unregistration/rule", method = RequestMethod.POST)
    public void unregisterRule(@RequestBody String rule) {

        logger.info(UtilsResource.getLogMessage(RESOURCE_NAMING.EPROCESSING_UNREGISTRATION_RULE));

    }
}
