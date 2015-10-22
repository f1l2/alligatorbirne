package iot.device.delivery;

import java.time.Instant;
import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import common.data.DataSource;
import common.data.model.DeviceInformation;
import common.data.setting.SettingUtils;
import common.property.SystemReservedProperty;
import common.rest.RESOURCE_NAMING;
import common.rest.ResourceUtils;
import iot.device.property.Configuration;
import iot.device.repo.DeliveryTaskRO;
import iot.device.sensor.DynamicSensorFactory;
import iot.device.sensor.Sensor;
import iot.device.status.STATUS_TYPE;
import iot.device.status.Status;
import iot.device.utility.VirtualData;
import iot.device.utility.VirtualEP;

@Component
public class DeliveryTask implements Runnable {

    final static Logger logger = LoggerFactory.getLogger(DeliveryTask.class);

    private DeliveryTaskRO deliveryTaskRO;

    private String deliveryUrl;

    @Autowired
    private Status status;

    @Autowired
    private DynamicSensorFactory dsf;

    private String identification;

    public DeliveryTask() {
        this.identification = null;
    }

    public DeliveryTask(DeliveryTaskRO deliveryTask) {
        this.deliveryTaskRO = deliveryTask;
        this.deliveryUrl = ResourceUtils.getUrl(RESOURCE_NAMING.EPROCESSING_SEND, deliveryTaskRO.getUrlDataSink().getAuthority());
        this.setIdentification(String.format("deliveryTask_%s", Arrays.hashCode(new Object[] { deliveryTask.getUrlDataSink().getAuthority() })));
    }

    @Override
    public void run() {

        RestTemplate restTemplate = new RestTemplate();

        for (;;) {

            Configuration configuration = deliveryTaskRO.getConfiguration();

            logger.info("DeliveryTask {} is executed.", this.identification);
            logger.info("Configuration: {}", configuration.toString());
            logger.info("Status: {}", status.getCurrent().name());

            int sleepTime = configuration.getValue(SystemReservedProperty.TASK_INTERVAL_MS);

            try {

                for (String sensorData : configuration.getSupplyingSensor()) {

                    Sensor<?> sensor = (Sensor<?>) dsf.getBean(sensorData.toLowerCase());
                    Integer value = sensor.getValue();

                    List<DataSource> loadDataSources = SettingUtils.loadDataSourcesByDeviceInformation(sensorData);

                    DeviceInformation deviceInformation = new DeviceInformation();
                    deviceInformation.setName(sensorData);
                    deviceInformation.setValue(value);

                    if (STATUS_TYPE.TEST.equals(status.getCurrent())) {
                        VirtualEP.send(new VirtualData(deviceInformation, deliveryUrl, Instant.now()));
                    } else {

                        logger.info("Send data ...");
                        ResponseEntity<Void> responseRegistration = restTemplate.postForEntity(deliveryUrl, deviceInformation, Void.class);
                        logger.info("Device data send. Status: " + responseRegistration.getStatusCode() + " Response body: ");
                    }
                }

            } catch (Exception e) {
                logger.error("Error sending data. {}", e);
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

    public String getIdentification() {
        return identification;
    }

    public void setIdentification(String identification) {
        this.identification = identification;
    }

}
