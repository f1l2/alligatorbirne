package iot.device.repo;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import common.data.DeviceInformation;
import common.rest.RESOURCE_NAMING;
import common.rest.UtilsResource;
import iot.device.Application;
import iot.device.property.Configuration;
import iot.device.property.SystemReservedProperty;
import iot.device.sensor.Sensor;
import iot.device.status.STATUS_TYPE;
import iot.device.status.Status;
import iot.device.vt.VtData;
import iot.device.vt.VtEP;

public class DeliveryTask implements Runnable {

    final static Logger logger = LoggerFactory.getLogger(DeliveryTask.class);

    private DeliveryTaskRO deliveryTaskRO;

    private String deliveryUrl;

    @Autowired
    private Status status;

    public DeliveryTask(DeliveryTaskRO deliveryTask) {
        this.deliveryTaskRO = deliveryTask;
        this.deliveryUrl = UtilsResource.getUrl(RESOURCE_NAMING.EPROCESSING_SEND, deliveryTaskRO.getUrlDataSink().getAuthority());
    }

    @Override
    public void run() {

        Configuration configuration = deliveryTaskRO.getConfiguration();
        int sleepTime = configuration.getValue(SystemReservedProperty.TASK_INTERVAL_MS);

        RestTemplate restTemplate = new RestTemplate();

        for (;;) {

            try {

                for (String sensorData : configuration.getSupplyingSensor()) {

                    System.out.println(sensorData);

                    if (Application.CONTEXT == null) {
                        System.out.println("AUTSCH");
                    }

                    Sensor<?> sensor = (Sensor<?>) Application.CONTEXT.getBean(sensorData);
                    String value = sensor.getValue();

                    DeviceInformation deviceInformation = new DeviceInformation();
                    deviceInformation.setName(sensorData);
                    deviceInformation.setValue(value);

                    if (STATUS_TYPE.TEST.equals(status)) {
                        VtEP.send(new VtData(deviceInformation, deliveryUrl, new Date()));
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
