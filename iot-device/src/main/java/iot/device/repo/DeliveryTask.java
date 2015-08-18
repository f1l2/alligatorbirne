package iot.device.repo;

import org.apache.log4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import common.data.DeviceInformation;
import common.rest.RESOURCE_NAMING;
import common.rest.UtilsResource;

public class DeliveryTask implements Runnable {

    final static Logger logger = Logger.getLogger(DeliveryTask.class);

    private DeliveryTaskRO deliveryTask;

    public DeliveryTask(DeliveryTaskRO job) {
        this.deliveryTask = job;
    }

    @Override
    public void run() {

        RestTemplate restTemplate = new RestTemplate();

        for (;;) {

            try {

                String url = UtilsResource.getUrl(RESOURCE_NAMING.EPROCESSING_SEND, deliveryTask.getEventProcessingUrl());

                DeviceInformation deviceInformation = new DeviceInformation();
                deviceInformation.setName("Name");

                ResponseEntity<Void> responseRegistration = restTemplate.postForEntity(url, deviceInformation, Void.class);

                logger.info("Device data send. Status: " + responseRegistration.getStatusCode() + " Response body: ");

            } catch (Exception ex) {

                logger.error("Error sending device data.");
                logger.error(ex);
            }

            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
