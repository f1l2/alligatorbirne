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
import iot.device.delivery.DeliveryTask;
import iot.device.delivery.DynamicDeliveryTaskFactory;
import iot.device.repo.DeliveryTaskRO;
import iot.device.repo.DeliveryTaskRepositoryImpl;
import iot.device.repo.DeliveryTaskTransformer;

@Component
public class SetConfiguration extends Activity<String, ConfigurationModification> {

    final static Logger logger = LoggerFactory.getLogger(SetConfiguration.class);;

    @Autowired
    private DeliveryTaskRepositoryImpl repo;

    @Autowired
    private ThreadPoolTaskExecutor taskExecutor;

    @Autowired
    private DeliveryTaskTransformer transformer;

    @Autowired
    private DynamicDeliveryTaskFactory ddtf;

    @Override
    public ResponseEntity<String> doStep(ConfigurationModification cm) {

        DeliveryTaskRO taskRO = repo.findByUrl(cm.getDataSink().getUrl());

        if (null != taskRO) {
            /**
             * Update properties.
             */

            taskRO.getConfiguration().setAndUpdateProperties(cm.getProperties());

            repo.save(taskRO);

            logger.info("Data Sinks gets already supplied. Configuration changed.");
        }

        else if (taskExecutor.getActiveCount() >= ApplicationConfig.MAX_TASKS) {

            /**
             * According to the settings the limit for the number of maximal tasks is reached.
             */

            return new ResponseEntity<String>("Device has no free slot.", HttpStatus.BAD_REQUEST);

        } else {

            /**
             * Create new data delivery task.
             */

            taskRO = transformer.toLocal(cm);
            if (repo.create(taskRO)) {
                DeliveryTask task = ddtf.createBean(taskRO);

                logger.info("DeliveryTask {} is created. Execution starts ...", task.getIdentification());

                taskExecutor.execute(task);
            } else {
                taskRO.getConfiguration().setAndUpdateProperties(cm.getProperties());

                repo.save(taskRO);

                logger.info("Data Sinks gets already supplied. Configuration changed.");
            }
        }

        return next("OK", cm);
    }
}
