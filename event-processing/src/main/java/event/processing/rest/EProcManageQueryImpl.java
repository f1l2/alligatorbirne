package event.processing.rest;

import java.io.IOException;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import event.processing.engine.Engine;
import event.processing.engine.EngineFactory;
import event.processing.engine.EngineListener;
import event.processing.engine.QueryTransformer;
import event.processing.engine.impl.EsperEngineFactory;
import event.processing.query.Query;
import event.processing.query.QueryFactory;

@RestController
public class EProcManageQueryImpl implements EProcManageQuery {

    private final EngineFactory engineFactory = EsperEngineFactory.getEsperEngineFactory();

    private final QueryFactory queryFactory = new QueryFactory();

    @Override
    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public void register(String query) {

        try {
            Query q = queryFactory.create(query);
        } catch (IOException ex) {

        }

        Engine engine = engineFactory.getEngine();

        EngineListener engineListener = engineFactory.getEngineListener();

        QueryTransformer queryTransformer = engineFactory.getQueryTransformer();

        query = "select * from DeviceInformation";

        engine.registerQuery(queryTransformer.transform(query), engineListener);

    }

    @Override
    @RequestMapping(value = "/unregistration", method = RequestMethod.POST)
    public void unregister(String query) {

        Engine engine = engineFactory.getEngine();

        engine.unregisterQuery(query);

    }

}
