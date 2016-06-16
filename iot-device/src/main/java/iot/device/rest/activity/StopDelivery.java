package iot.device.rest.activity;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import common.data.ConfigurationDelegation;
import iot.device.repo.DeliveryTaskRO;

@Component
public class StopDelivery extends IoTActivity<String, ConfigurationDelegation> {

    final static Logger logger = LoggerFactory.getLogger(StopDelivery.class);

    @Override
    public ResponseEntity<String> doStep(ConfigurationDelegation item) {

        DeliveryTaskRO taskRO = deliveryTaskRepository.findByUrl(item.getDataSink().getUrl());

        if (null != taskRO) {
            /**
             * Update properties.
             */
            taskRO.getDataSources().removeAll(item.getDataSources());
            deliveryTaskRepository.save(taskRO);
            logger.info("Data Sinks gets already supplied. Configuration changed.");
        } else {
            logger.info("Task for data sink not found.");
        }

        return next("OK", item);
    }
}
