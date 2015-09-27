package event.processing.rest;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import event.processing.engine.Engine;
import event.processing.engine.EngineFactory;
import event.processing.engine.EngineListener;
import event.processing.engine.QueryTransformer;
import event.processing.engine.impl.EsperEngineFactory;
import event.processing.query.QueryFactory;

@RestController
public class EProcManageQueryImpl implements EProcManageQuery {

    private static final Logger logger = LoggerFactory.getLogger(EProcManageQueryImpl.class);

    private EngineFactory engineFactory = EsperEngineFactory.getEsperEngineFactory();

    @Autowired
    private QueryFactory qf;

    @Override
    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public void register(String query) {

        try {
            qf.parse(query);
        } catch (IOException e) {
            logger.error("Couldn't parse string. Please check correctness of query.");
            throw new IllegalArgumentException("Couldn't parse string. Please check coorectness of query.");
        }

        Engine engine = engineFactory.getEngine();

        EngineListener engineListener = engineFactory.getEngineListener();

        QueryTransformer queryTransformer = engineFactory.getQueryTransformer();

        engine.registerQuery(queryTransformer.transform(query), engineListener);

    }

    @Override
    @RequestMapping(value = "/unregistration", method = RequestMethod.POST)
    public void unregister(String query) {

        Engine engine = engineFactory.getEngine();

        engine.unregisterQuery(query);

    }

}
