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

import common.data.ConfigurationModification;
import common.data.type.COMPONENT_TYPE;
import common.rest.RESOURCE_NAMING;
import common.rest.ResourceUtils;
import iot.device.repo.DeliveryTaskRO;
import iot.device.repo.DeliveryTaskRepositoryImpl;
import iot.device.rest.activity.SetConfiguration;
import iot.device.rest.activity.ValidateRequestBody;

@RestController
public class IDevManageConfigImpl implements IDevManageConfig {

    final static Logger logger = LoggerFactory.getLogger(IDevManageConfigImpl.class);

    @Autowired
    private DeliveryTaskRepositoryImpl repo;

    @Autowired
    private ValidateRequestBody validateRB;

    @Autowired
    private SetConfiguration setConfig;

    @Override
    @RequestMapping(value = "/configurations", method = RequestMethod.GET)
    public ResponseEntity<List<DeliveryTaskRO>> getAllConfiguration() {

        logger.info(ResourceUtils.getLogMessage(RESOURCE_NAMING.IDEV_GET_ALL_CONFIGURATION));

        List<DeliveryTaskRO> result = repo.findAll();
        return new ResponseEntity<List<DeliveryTaskRO>>(result, HttpStatus.OK);
    }

    @Override
    @RequestMapping(value = "/configurations/{authority}", method = RequestMethod.GET)
    public ResponseEntity<DeliveryTaskRO> getConfigurationByEPAuthority(@PathVariable("authority") String authority) {

        logger.info(ResourceUtils.getLogMessage(RESOURCE_NAMING.IDEV_GET_CONFIGURATION_BY_EP));

        DeliveryTaskRO result = repo.findByAuthority(authority);

        return new ResponseEntity<DeliveryTaskRO>(result, HttpStatus.OK);
    }

    @Override
    @RequestMapping(value = "/configurations", method = RequestMethod.POST)
    public ResponseEntity<String> setConfiguration(@RequestBody ConfigurationModification configurationModification) {

        logger.info(ResourceUtils.getLogMessage(RESOURCE_NAMING.IDEV_SET_CONFIGURATION));

        /**
         * Build "to-do" chain
         */
        validateRB.setNextActivity(setConfig);
        validateRB.setCt(COMPONENT_TYPE.EVENT_PROCESSING);

        return validateRB.doStep(configurationModification);

    }

}
