package iot.device.repo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import common.data.DeviceInformation;
import common.rest.RESOURCE_NAMING;
import common.rest.UtilsResource;
import iot.device.property.Configuration;
import iot.device.property.SystemReservedProperty;

public class DeliveryTask implements Runnable {

    final static Logger logger = LoggerFactory.getLogger(DeliveryTask.class);

    private DeliveryTaskRO deliveryTaskRO;

    public DeliveryTask(DeliveryTaskRO deliveryTask) {
        this.deliveryTaskRO = deliveryTask;
    }

    @Override
    public void run() {

        Configuration configuration = deliveryTaskRO.getConfiguration();
        int sleepTime = configuration.getValue(SystemReservedProperty.TASK_INTERVAL_MS);

        RestTemplate restTemplate = new RestTemplate();

        for (;;) {

            try {

                String url = UtilsResource.getUrl(RESOURCE_NAMING.EPROCESSING_SEND, deliveryTaskRO.getUrlDataSink().getAuthority());

                DeviceInformation deviceInformation = new DeviceInformation();
                deviceInformation.setName("Name");

                ResponseEntity<Void> responseRegistration = restTemplate.postForEntity(url, deviceInformation, Void.class);

                logger.info("Device data send. Status: " + responseRegistration.getStatusCode() + " Response body: ");

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
