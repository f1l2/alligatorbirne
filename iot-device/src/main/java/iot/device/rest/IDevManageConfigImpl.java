package iot.device.rest;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import common.data.ConfigurationModification;
import common.rest.RESOURCE_NAMING;
import common.rest.UtilsResource;
import iot.device.ApplicationConfig;
import iot.device.repo.DeliveryTask;
import iot.device.repo.DeliveryTaskRO;
import iot.device.repo.DeliveryTaskRepositoryImpl;
import iot.device.repo.DeliveryTaskTransformer;

@RestController
public class IDevManageConfigImpl implements IDevManageConfig {

    final static Logger logger = LoggerFactory.getLogger(IDevManageConfigImpl.class);

    @Autowired
    private DeliveryTaskRepositoryImpl repo;

    @Autowired
    private DeliveryTaskTransformer transformer;

    @Autowired
    private ThreadPoolTaskExecutor taskExecutor;

    @Override
    @RequestMapping(value = "/configurations", method = RequestMethod.GET)
    public ResponseEntity<List<DeliveryTaskRO>> getAllConfiguration() {

        logger.info(UtilsResource.getLogMessage(RESOURCE_NAMING.IDEV_GET_ALL_CONFIGURATION));

        List<DeliveryTaskRO> result = repo.findAll();
        return new ResponseEntity<List<DeliveryTaskRO>>(result, HttpStatus.OK);
    }

    @Override
    @RequestMapping(value = "/configurations/{authority}", method = RequestMethod.GET)
    public ResponseEntity<DeliveryTaskRO> getConfigurationByEPAuthority(@PathVariable("authority") String authority) {

        logger.info(UtilsResource.getLogMessage(RESOURCE_NAMING.IDEV_GET_CONFIGURATION_BY_EP));

        DeliveryTaskRO result = repo.findByAuthority(authority);

        return new ResponseEntity<DeliveryTaskRO>(result, HttpStatus.OK);
    }

    @Override
    @RequestMapping(value = "/configurations", method = RequestMethod.POST)
    public ResponseEntity<String> setConfiguration(ConfigurationModification configurationModification) {

        logger.info(UtilsResource.getLogMessage(RESOURCE_NAMING.IDEV_SET_CONFIGURATION));

        DeliveryTaskRO taskRO = repo.findByUrl(configurationModification.getDataSink().getUrl());

        if (null == taskRO) {

            /**
             * Create new task for EP
             */

            /**
             * 
             */

            if (taskExecutor.getActiveCount() > ApplicationConfig.MAX_TASKS) {
                taskRO = transformer.toLocal(configurationModification);
                repo.save(taskRO);

                DeliveryTask task = new DeliveryTask(taskRO);

                taskExecutor.execute(task);
            } else {
                return new ResponseEntity<String>("Device has no free slot.", HttpStatus.INTERNAL_SERVER_ERROR);
            }

        } else {

            /**
             * Update properties
             */

            taskRO.setProperties(configurationModification.getProperties());
            repo.save(taskRO);

        }

        return new ResponseEntity<String>("OK", HttpStatus.OK);
    }

}
