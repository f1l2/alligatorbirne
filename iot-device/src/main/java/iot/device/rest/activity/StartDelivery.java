package iot.device.rest.activity;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

import common.data.dto.DataSourcesDTO;
import common.rest.UrlUtils;
import iot.device.ApplicationConfig;
import iot.device.delivery.DeliveryTask;
import iot.device.delivery.DynamicDeliveryTaskFactory;
import iot.device.repo.DeliveryTaskRO;
import iot.device.repo.DeliveryTaskRepositoryImpl;

@Component
public class StartDelivery extends Activity<String, DataSourcesDTO> {

    final static Logger logger = LoggerFactory.getLogger(StartDelivery.class);

    private String epAuthority;

    @Autowired
    private DeliveryTaskRepositoryImpl repo;

    @Autowired
    private ThreadPoolTaskExecutor taskExecutor;

    @Autowired
    private DynamicDeliveryTaskFactory ddtf;

    @Override
    public ResponseEntity<String> doStep(DataSourcesDTO item) {

        DeliveryTaskRO taskRO = repo.findByAuthority(epAuthority);

        if (null != taskRO) {
            /**
             * Update data sources.
             */
            taskRO.getDataSources().addAll(item.getDataSources());

            repo.save(taskRO);

            logger.info("Data Sinks gets already supplied. Data sources updated.");
        }

        else if (taskExecutor.getActiveCount() >= ApplicationConfig.MAX_TASKS) {

            /**
             * According to the settings the limit for the number of maximal tasks is reached.
             */

            return new ResponseEntity<String>("Device has no spare capacity.", HttpStatus.BAD_REQUEST);

        } else {

            /**
             * Create new data delivery task.
             */

            taskRO = new DeliveryTaskRO();
            taskRO.setDataSources(item.getDataSources());
            taskRO.setUrlDataSink(UrlUtils.parseUrl(epAuthority));

            if (repo.create(taskRO)) {

                DeliveryTask task = ddtf.createBean(taskRO);

                logger.info("DeliveryTask {} is created. Execution starts ...", task.getIdentification());

                taskExecutor.execute(task);
            } else {
                taskRO.getDataSources().addAll(item.getDataSources());

                repo.save(taskRO);

                logger.info("Data Sinks gets already supplied. Configuration changed.");
            }
        }

        return next("OK", item);
    }

    public String getEpAuthority() {
        return epAuthority;
    }

    public void setEpAuthority(String epAuthority) {
        this.epAuthority = epAuthority;
    }
}
