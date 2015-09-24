package iot.device;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import common.data.Connection;
import common.data.DataSources;
import common.data.config.UtilsConfiguration;
import common.rest.RESOURCE_NAMING;
import common.rest.UtilsResource;
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
                local = UtilsConfiguration.getLocalConnection();
                logger.info("Retrieve local connection data ... ");
                logger.info(local.toString());

                /**
                 * Load CM connection data
                 */
                cm = UtilsConfiguration.getCMConnection();
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

                String url = UtilsResource.getUrl(RESOURCE_NAMING.CMGMT_REGISTER_DEVICE, cm);
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

                DataSources data = UtilsConfiguration.loadMeasurementData();

                Connection cmConnection = UtilsConfiguration.getCMConnection();
                String url = UtilsResource.getUrl(RESOURCE_NAMING.CMGMT_REGISTER_DEVICE_SOURCES, cmConnection);
                url = url.replace("{id}", String.valueOf(local.getId()));

                ResponseEntity<Void> responseRegisteriationSources = restTemplate.postForEntity(url, data, Void.class);
                logger.info("Sources of device registered. Status: " + responseRegisteriationSources.getStatusCode() + " Response body: ");

                status.setCurrent(STATUS_TYPE.WORKING);

            } catch (Exception ex) {
                logger.error("Error registration sources of device. Exception: {}", ex.getMessage());
            }

            break;

        case WORKING:

            // TODO send heart beat
            logger.info("Send heart beat");

            break;

        case ERROR:
            break;

        default:
            break;
        }

    }
}
