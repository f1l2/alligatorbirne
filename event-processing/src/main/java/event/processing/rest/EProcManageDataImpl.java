package event.processing.rest;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import common.data.DataSource;

import event.processing.engine.Engine;
import event.processing.engine.EngineFactory;
import event.processing.engine.impl.EsperEngineFactory;

@RestController
public class EProcManageDataImpl implements EProcManageData {

    private final EngineFactory factory = EsperEngineFactory.getEsperEngineFactory();

    @Override
    @RequestMapping(value = "/send", method = RequestMethod.POST)
    public void receive(@RequestBody DataSource dataSource) {

        Engine engine = factory.getEngine();

        engine.sendEvent(dataSource);

    }
}
