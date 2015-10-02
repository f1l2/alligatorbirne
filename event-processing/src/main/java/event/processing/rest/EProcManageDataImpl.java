package event.processing.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import common.data.DataSource;
import event.processing.engine.Engine;
import event.processing.engine.EngineFactory;

@RestController
public class EProcManageDataImpl implements EProcManageData {

    private static final Logger logger = LoggerFactory.getLogger(EProcManageDataImpl.class);

    @Autowired
    @Qualifier("esper")
    private EngineFactory factory;

    @Override
    @RequestMapping(value = "/send", method = RequestMethod.POST)
    public void receive(@RequestBody DataSource dataSource) {

        logger.info("");

        Engine engine = factory.getEngine();

        engine.send(dataSource);

    }
}
