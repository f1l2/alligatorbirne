package iot.device.rest;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import common.data.ConfigurationDelegation;
import common.data.type.COMPONENT_TYPE;
import common.rest.RESOURCE_NAMING;
import common.rest.ResourceUtils;
import iot.device.repo.DeliveryTaskRO;
import iot.device.repo.DeliveryTaskRepositoryImpl;
import iot.device.rest.activity.SetConfiguration;
import iot.device.rest.activity.StartDelivery;
import iot.device.rest.activity.StopDelivery;
import iot.device.rest.activity.ValidateConnection;
import iot.device.rest.activity.ValidateDataSources;

@RestController
public class DevManageConfigImpl implements DevManageConfig {

    final static Logger logger = LoggerFactory.getLogger(DevManageConfigImpl.class);

    @Autowired
    private DeliveryTaskRepositoryImpl repo;

    @Autowired
    private ValidateConnection validateConnection;

    @Autowired
    private ValidateDataSources validateDataSources;

    @Autowired
    private SetConfiguration setConfig;

    @Autowired
    private StartDelivery startDelivery;

    @Autowired
    private StopDelivery stopDelivery;

    @Override
    @RequestMapping(value = "/configurations", method = RequestMethod.GET)
    public ResponseEntity<List<DeliveryTaskRO>> getAllConfiguration() {

        logger.info(ResourceUtils.getLogMessage(RESOURCE_NAMING.DEV_GET_ALL_CONFIGURATION));

        List<DeliveryTaskRO> result = repo.findAll();
        return new ResponseEntity<List<DeliveryTaskRO>>(result, HttpStatus.OK);
    }

    @Override
    @RequestMapping(value = "/configurations/{authority}", method = RequestMethod.GET)
    public ResponseEntity<DeliveryTaskRO> getConfigurationByEPAuthority(@PathVariable("authority") String authority) {

        logger.info(ResourceUtils.getLogMessage(RESOURCE_NAMING.DEV_GET_CONFIGURATION_BY_EP));

        DeliveryTaskRO result = repo.findByAuthority(authority);

        return new ResponseEntity<DeliveryTaskRO>(result, HttpStatus.OK);
    }

    @Override
    @RequestMapping(value = "/configurations", method = RequestMethod.POST)
    public ResponseEntity<String> setConfiguration(@RequestBody ConfigurationDelegation body) {

        logger.info(ResourceUtils.getLogMessage(RESOURCE_NAMING.DEV_SET_CONFIGURATION));

        /**
         * Build "to-do" chain
         */
        validateConnection.setNextActivity(setConfig);
        validateConnection.setCt(COMPONENT_TYPE.EVENT_PROCESSING);

        return validateConnection.doStep(body);

    }

    @Override
    @RequestMapping(value = "/delivery/start", method = RequestMethod.POST)
    public ResponseEntity<String> startDelivery(@RequestBody ConfigurationDelegation body) {

        logger.info(ResourceUtils.getLogMessage(RESOURCE_NAMING.DEV_START_DELIVERY));

        /**
         * Build "to-do" chain
         */
        validateConnection.setNextActivity(validateDataSources);
        validateDataSources.setNextActivity(startDelivery);

        return validateConnection.doStep(body);
    }

    @Override
    @RequestMapping(value = "/delivery/stop", method = RequestMethod.POST)
    public ResponseEntity<String> stopDelivery(@RequestBody ConfigurationDelegation body) {

        logger.info(ResourceUtils.getLogMessage(RESOURCE_NAMING.DEV_STOP_DELIVERY));

        /**
         * Build "to-do" chain
         */

        validateConnection.setNextActivity(validateDataSources);
        validateDataSources.setNextActivity(stopDelivery);

        return validateConnection.doStep(body);
    }
}
