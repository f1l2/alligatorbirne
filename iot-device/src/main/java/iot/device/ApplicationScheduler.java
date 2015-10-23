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
                local.setComponentType(COMPONENT_TYPE.IOT_DEVICE);
                logger.info("Retrieve local connection data ... ");
                logger.info(local.toString());

                /**
                 * Load CM connection data
                 */
                cm = SettingUtils.getCMConnection();
                logger.info("Load CM connection data ...");
                logger.info(cm.toString());

                status.setCurrent(STATUS_TYPE.REGISTER_DEVICE);

            } catch (Exception ex) {

                logger.error(ex.getMessage());
            }
            break;

        case REGISTER_DEVICE:

            try {

                logger.info("Try to register device ...");

                String url = ResourceUtils.getUrl(RESOURCE_NAMING.CMGMT_REGISTER_DEVICE, cm);
                logger.info(url);

                ResponseEntity<Connection> responseRegistration = restTemplate.postForEntity(url, local, Connection.class);
                local = responseRegistration.getBody();

                logger.info("Device registered. Status: " + responseRegistration.getStatusCode() + " Response body: " + local);

                status.setCurrent(STATUS_TYPE.REGISTER_DATA_SOURCES);

            } catch (Exception ex) {
                logger.error("Register error. Exception: {}", ex.getMessage());
            }

            break;

        case REGISTER_DATA_SOURCES:

            try {

                logger.info("Convey data sources ...");

                DataSourcesDTO data = SettingUtils.loadDataSources();

                Connection cmConnection = SettingUtils.getCMConnection();
                String url = ResourceUtils.getUrl(RESOURCE_NAMING.CMGMT_REGISTER_DEVICE_SOURCES, cmConnection);
                url = url.replace("{id}", String.valueOf(local.getId()));

                ResponseEntity<Void> responseRegisteriationSources = restTemplate.postForEntity(url, data, Void.class);
                logger.info("Sources of device registered. Status: " + responseRegisteriationSources.getStatusCode() + " Response body: ");

                status.setCurrent(STATUS_TYPE.WORKING);

            } catch (Exception ex) {
                logger.error("Error registration sources of device. Exception: {}", ex.getMessage());
            }

            break;

        case WORKING:

            logger.info("Send heart beat.");
            String url = ResourceUtils.getUrl(RESOURCE_NAMING.CMGMT_HEART_BEAT_DEVICE, cm);

            url = url.replace("{id}", Long.toString(local.getId()));
            restTemplate.put(url, null);

            break;

        case ERROR:
            break;

        default:
            break;
        }

    }
}
