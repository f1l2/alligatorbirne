package iot.device.rest.activity;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

import common.data.ConfigurationModification;
import iot.device.ApplicationConfig;
import iot.device.repo.DeliveryTask;
import iot.device.repo.DeliveryTaskRO;
import iot.device.repo.DeliveryTaskRepositoryImpl;
import iot.device.repo.DeliveryTaskTransformer;

@Component
public class SetProperty extends Activity<String, ConfigurationModification> {

    final static Logger logger = LoggerFactory.getLogger(SetProperty.class);;

    @Autowired
    private DeliveryTaskRepositoryImpl repo;

    @Autowired
    private ThreadPoolTaskExecutor taskExecutor;

    @Autowired
    private DeliveryTaskTransformer transformer;

    @Override
    public ResponseEntity<String> doStep(ConfigurationModification cm) {

        DeliveryTaskRO taskRO = repo.findByUrl(cm.getDataSink().getUrl());

        if (null != taskRO) {
            /**
             * Update properties
             */

            taskRO.setProperties(cm.getProperties());
            repo.save(taskRO);
        }

        else {
            /**
             * Create new data delivery task
             */
            if (taskExecutor.getActiveCount() < ApplicationConfig.MAX_TASKS) {

                taskRO = transformer.toLocal(cm);
                repo.save(taskRO);

                DeliveryTask task = new DeliveryTask(taskRO);

                taskExecutor.execute(task);
            } else {

                return new ResponseEntity<String>("Device has no free slot.", HttpStatus.INTERNAL_SERVER_ERROR);
            }

        }

        return next("OK", cm);
    }
}
