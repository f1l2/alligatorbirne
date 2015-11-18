package event.processing.rest;

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
import org.springframework.web.bind.annotation.RestController;

import common.data.dto.DeviceDataDTO;
import common.data.model.DeviceData;
import common.rest.RESOURCE_NAMING;
import common.rest.ResourceUtils;
import common.transformer.DeviceDataTransformer;
import event.processing.engine.EngineFactory;

@RestController
public class EPManageDataImpl implements EPManageData {

    private static final Logger logger = LoggerFactory.getLogger(EPManageDataImpl.class);

    @Autowired
    @Qualifier("esper")
    private EngineFactory factory;

    @Override
    @RequestMapping(value = "/send", method = RequestMethod.POST)
    public ResponseEntity<String> receive(@RequestBody DeviceDataDTO deviceDataDTO) {

        logger.info(ResourceUtils.getLogMessage(RESOURCE_NAMING.EP_SEND));

        List<DeviceData> deviceData = DeviceDataTransformer.fork(deviceDataDTO);

        deviceData.forEach(item -> logger.info(item.toString()));
        deviceData.forEach(item -> factory.getEngine().send(item));

        return new ResponseEntity<String>("OK", HttpStatus.OK);
    }
}
