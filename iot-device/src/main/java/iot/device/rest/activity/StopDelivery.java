package iot.device.rest.activity;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import common.data.ConfigurationDelegation;
import iot.device.repo.DeliveryTaskRO;
import iot.device.repo.DeliveryTaskRepositoryImpl;

@Component
public class StopDelivery extends Activity<String, ConfigurationDelegation> {

    final static Logger logger = LoggerFactory.getLogger(StopDelivery.class);

    @Autowired
    private DeliveryTaskRepositoryImpl repo;

    @Override
    public ResponseEntity<String> doStep(ConfigurationDelegation item) {

        DeliveryTaskRO taskRO = repo.findByUrl(item.getDataSink().getUrl());

        if (null != taskRO) {
            /**
             * Update properties.
             */

            taskRO.getDataSources().removeAll(item.getDataSources());

            repo.save(taskRO);

            logger.info("Data Sinks gets already supplied. Configuration changed.");
        }

        else {

            logger.info("Task for data sink not found.");
        }

        return next("OK", item);
    }
}
