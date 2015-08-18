package event.processing.rest;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import common.data.DeviceInformation;

import event.processing.engine.Engine;
import event.processing.engine.impl.EsperEngineFactory;

@RestController
public class EProcManageDataImpl implements EProcManageData {

    @Override
    @RequestMapping(value = "/send", method = RequestMethod.POST)
    public void receive(@RequestBody DeviceInformation deviceInformation) {

        EsperEngineFactory factory = new EsperEngineFactory();

        Engine engine = factory.getEngine();

        engine.sendEvent(deviceInformation);

    }
}
