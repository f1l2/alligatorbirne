package iot.device.delivery;

import java.time.Instant;
import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import common.data.dto.DeviceDataDTO;
import common.data.model.DataSource;
import common.data.model.SensorData;
import common.data.setting.SettingUtils;
import common.property.SystemReservedProperty;
import common.rest.RESOURCE_NAMING;
import common.rest.ResourceUtils;
import iot.device.messaging.MessageHandlerImpl;
import iot.device.property.Configuration;
import iot.device.repo.DeliveryTaskRO;
import iot.device.repo.DeliveryTaskRepository;
import iot.device.sensor.DynamicSensorFactory;
import iot.device.sensor.Sensor;
import iot.device.status.STATUS_TYPE;
import iot.device.status.Status;
import iot.device.utility.VirtualData;
import iot.device.utility.VirtualEP;

@Component
public class DeliveryTask implements Runnable {

    final static Logger logger = LoggerFactory.getLogger(DeliveryTask.class);

    private String deliveryUrl;

    private String deliveryAuthority;

    @Autowired
    private Status status;

    @Autowired
    private DynamicSensorFactory dsf;

    @Autowired
    private DeliveryTaskRepository dtr;

    @Autowired
    private MessageHandlerImpl messageHandler;

    private String identification;

    public DeliveryTask() {

    }

    public DeliveryTask(DeliveryTaskRO deliveryTaskRO) {

        this.deliveryAuthority = deliveryTaskRO.getUrlDataSink().getAuthority();
        this.deliveryUrl = ResourceUtils.getUrl(RESOURCE_NAMING.EP_SEND, deliveryTaskRO.getUrlDataSink().getAuthority());
        this.setIdentification(String.format("deliveryTask_%s", Arrays.hashCode(new Object[] { deliveryTaskRO.getUrlDataSink().getAuthority() })));
    }

    @Override
    public void run() {

        for (;;) {

            DeliveryTaskRO deliveryTaskRO = dtr.findByAuthority(deliveryAuthority);

            if (deliveryTaskRO.notUsed()) {
                logger.info("DeliveryTask {} terminates.", this.identification);
                dtr.delete(deliveryTaskRO);
                break;
            }

            Configuration configuration = deliveryTaskRO.getConfiguration();

            logger.info("DeliveryTask {} is executed.", this.identification);
            logger.info("Configuration: {}", configuration.toString());

            int sleepTime = configuration.getValue(SystemReservedProperty.TASK_INTERVAL_MS);

            try {

                for (DataSource dataSource : deliveryTaskRO.getDataSources()) {

                    Sensor<?> sensor = (Sensor<?>) dsf.getBean(dataSource.getDeviceInformation().getName().toLowerCase());

                    SensorData<Integer> sensorData = new SensorData<Integer>();
                    sensorData.setRawValue(sensor.getValue());

                    DeviceDataDTO ddDTO = new DeviceDataDTO();
                    ddDTO.setDevice(dataSource.getDeviceInformation());
                    ddDTO.setDomains(SettingUtils.loadDomainsByDeviceInformation(dataSource.getDeviceInformation().getName()));
                    ddDTO.setSensorData(sensorData);

                    if (STATUS_TYPE.TEST.equals(status.getCurrent())) {
                        VirtualEP.send(new VirtualData(ddDTO, deliveryUrl, Instant.now()));
                    } else {
                        // logger.info("Send data ...");
                        // ResponseEntity<String> response =
                        // restTemplate.postForEntity(deliveryUrl, ddDTO, String.class);

                        messageHandler.start(SettingUtils.getMBConnection());
                        messageHandler.produce(ddDTO);

                        // logger.info("Device data send. Status: " + response.getStatusCode() + " Response body: " + response.getBody());
                    }
                }

            } catch (IllegalStateException e) {

            } catch (Exception e) {
                logger.error("Error sending data", e);
            }

            try {
                Thread.sleep(sleepTime);
            } catch (InterruptedException e) {
                logger.error("{}", e);
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
