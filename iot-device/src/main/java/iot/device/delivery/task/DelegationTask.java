package iot.device.delivery.task;

import java.time.Instant;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import common.data.model.DeviceInformation;
import common.rest.RESOURCE_NAMING;
import common.rest.ResourceUtils;
import iot.device.property.Configuration;
import iot.device.property.SystemReservedProperty;
import iot.device.repo.DeliveryTaskRO;
import iot.device.sensor.DynamicSensorFactory;
import iot.device.sensor.Sensor;
import iot.device.status.STATUS_TYPE;
import iot.device.status.Status;
import iot.device.vt.VtData;
import iot.device.vt.VtEP;

@Component
public class DelegationTask implements Runnable {

    final static Logger logger = LoggerFactory.getLogger(DelegationTask.class);

    private DeliveryTaskRO deliveryTaskRO;

    private String deliveryUrl;

    @Autowired
    private Status status;

    @Autowired
    private DynamicSensorFactory dsf;

    public DelegationTask() {

    }

    public DelegationTask(DeliveryTaskRO deliveryTask) {
        this.deliveryTaskRO = deliveryTask;
        this.deliveryUrl = ResourceUtils.getUrl(RESOURCE_NAMING.EPROCESSING_SEND, deliveryTaskRO.getUrlDataSink().getAuthority());
    }

    @Override
    public void run() {

        RestTemplate restTemplate = new RestTemplate();

        for (;;) {

            Configuration configuration = deliveryTaskRO.getConfiguration();
            int sleepTime = configuration.getValue(SystemReservedProperty.TASK_INTERVAL_MS);

            try {

                for (String sensorData : configuration.getSupplyingSensor()) {

                    Sensor<?> sensor = (Sensor<?>) dsf.getBean(sensorData);
                    String value = sensor.getValue();

                    DeviceInformation deviceInformation = new DeviceInformation();
                    deviceInformation.setName(sensorData);
                    deviceInformation.setValue(value);

                    if (STATUS_TYPE.TEST.equals(status.getCurrent())) {
                        VtEP.send(new VtData(deviceInformation, deliveryUrl, Instant.now()));
                    } else {
                        ResponseEntity<Void> responseRegistration = restTemplate.postForEntity(deliveryUrl, deviceInformation, Void.class);
                        logger.info("Device data send. Status: " + responseRegistration.getStatusCode() + " Response body: ");
                    }
                }

            } catch (Exception ex) {
                logger.error("Error sending data. {}", ex.getMessage());
            }

            try {
                Thread.sleep(sleepTime);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void stop() {

    }

}
