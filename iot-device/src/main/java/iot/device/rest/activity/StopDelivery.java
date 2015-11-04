package iot.device.rest.activity;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import common.data.dto.DataSourcesDTO;
import iot.device.repo.DeliveryTaskRO;
import iot.device.repo.DeliveryTaskRepositoryImpl;

@Component
public class StopDelivery extends Activity<String, DataSourcesDTO> {

    final static Logger logger = LoggerFactory.getLogger(StopDelivery.class);

    private String epAuthority;

    @Autowired
    private DeliveryTaskRepositoryImpl repo;

    @Override
    public ResponseEntity<String> doStep(DataSourcesDTO item) {

        DeliveryTaskRO taskRO = repo.findByAuthority(epAuthority);

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

    public String getEpAuthority() {
        return epAuthority;
    }

    public void setEpAuthority(String epAuthority) {
        this.epAuthority = epAuthority;
    }

}
