package iot.device;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import common.data.Connection;
import common.data.dto.DataSourcesDTO;
import common.data.setting.SettingUtils;
import common.data.type.COMPONENT_TYPE;
import common.rest.RESOURCE_NAMING;
import common.rest.ResourceUtils;
import iot.device.status.STATUS_TYPE;
import iot.device.status.Status;

@Component
public class ApplicationScheduler {

    final static Logger logger = LoggerFactory.getLogger(ApplicationScheduler.class);

    private static Connection local, cm;

    private RestTemplate restTemplate = new RestTemplate();

    @Autowired
    private Status status;

    public void carryOutActivity() {

        switch (status.getCurrent()) {
        case STARTED_UP:
            try {
                /**
                 * Load connection data.
                 */
                local = SettingUtils.getLocalConnection();
                local.setComponentType(COMPONENT_TYPE.DEVICE);
                logger.info("Local connection data loaded: {}", local.toString());

                /**
                 * Load CM connection data
                 */
                cm = SettingUtils.getCMConnection();
                logger.info("CM connection data loaded: {}", cm.toString());

                status.setCurrent(STATUS_TYPE.REGISTER_DEVICE);

            } catch (Exception ex) {
                logger.error(ex.getMessage());
            }
            break;

        case REGISTER_DEVICE:

            try {
                String url = ResourceUtils.getUrl(RESOURCE_NAMING.CM_REGISTER_DEVICE, cm);
                logger.info("Try to register device at {}", url);

                ResponseEntity<Connection> responseRegistration = restTemplate.postForEntity(url, local, Connection.class);
                local = responseRegistration.getBody();

                logger.info("Device registered. Status: {}; Response body: {}", responseRegistration.getStatusCode(), local);
                status.setCurrent(STATUS_TYPE.REGISTER_DATA_SOURCES);

            } catch (Exception ex) {
                logger.error("Register error. Exception: {}", ex.getMessage());
            }
            break;

        case REGISTER_DATA_SOURCES:

            try {
                DataSourcesDTO data = SettingUtils.loadDataSources();
                String url = ResourceUtils.getUrl(RESOURCE_NAMING.CM_REGISTER_DEVICE_SOURCES, cm, local.getId());
                ResponseEntity<Void> responseRegisteriationSources = restTemplate.postForEntity(url, data, Void.class);

                logger.info("Sources of device registered. Status: {}", responseRegisteriationSources.getStatusCode());
                status.setCurrent(STATUS_TYPE.WORKING);

            } catch (Exception ex) {
                logger.error("Error registration sources of device. Exception: {}", ex.getMessage());
            }
            break;

        case WORKING:

            logger.info("Send heart beat.");
            String url = ResourceUtils.getUrl(RESOURCE_NAMING.CM_HEART_BEAT_DEVICE, cm, local.getId());
            restTemplate.put(url, null);

            break;

        case ERROR:
            break;

        default:
            break;
        }

    }
}
