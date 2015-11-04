package iot.device.rest.activity;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import common.data.ConfigurationDelegation;
import iot.device.repo.DeliveryTaskRO;
import iot.device.repo.DeliveryTaskRepositoryImpl;

@Component
public class SetConfiguration extends Activity<String, ConfigurationDelegation> {

    final static Logger logger = LoggerFactory.getLogger(SetConfiguration.class);;

    @Autowired
    private DeliveryTaskRepositoryImpl repo;

    @Override
    public ResponseEntity<String> doStep(ConfigurationDelegation cd) {

        DeliveryTaskRO taskRO = repo.findByUrl(cd.getDataSink().getUrl());

        if (null != taskRO) {
            /**
             * Update properties.
             */

            taskRO.getConfiguration().setAndUpdateProperties(cd.getProperties());

            repo.save(taskRO);

            logger.info("Configuration updated.");

        } else {

            logger.error("Data Sinks is not delivered.");
            this.setErrorResponse(new ResponseEntity<String>("Data Sinks is not delivered.", HttpStatus.BAD_REQUEST));
        }

        return next("OK", cd);
    }
}
