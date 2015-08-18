package event.processing.rest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import event.processing.engine.Engine;
import event.processing.engine.EngineFactory;
import event.processing.engine.impl.EsperEngineFactory;

@RestController
public class EProcManageQueryImpl implements EProcManageQuery {

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public void register(String query) {

        EngineFactory factory = new EsperEngineFactory();

        Engine engine = factory.getEngine();

        query = "select * from DeviceInformation";

        engine.registerQuery("select * from DeviceInformation");

    }

    @RequestMapping(value = "/unregister", method = RequestMethod.POST)
    public void unregister(String query) {

        EngineFactory factory = new EsperEngineFactory();

        Engine engine = factory.getEngine();

        engine.unregisterQuery(query);

    }

}
