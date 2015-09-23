package iot.device;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
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

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    private RestTemplate restTemplate = new RestTemplate();

    private String url;

    @Autowired
    private Status status;

    // TODO change fixedRate

    @Scheduled(initialDelay = 1000, fixedRate = 20000)
    public void carryOutActivity() {

        switch (status.getCurrent()) {
        case STARTED_UP:

            try {
                /**
                 * Load connection data.
                 */
                local = UtilsConfiguration.getIoTDeviceConnection();
                logger.info("Retrieve local connection data ... ");
                logger.info(local.toString());

                /**
                 * Load CM connection data and prepare URL
                 */
                cm = UtilsConfiguration.getCMConnection();
                logger.info("Load CM connection data ...");
                logger.info(cm.toString());

                url = UtilsResource.getUrl(RESOURCE_NAMING.CMGMT_REGISTER_DEVICE, cm);
                logger.info(url);

                status.setCurrent(STATUS_TYPE.REGISTER_DEVICE);

            } catch (Exception ex) {

                logger.error("Register error. Call: " + url);
                logger.error(ex.getMessage());
            }
            break;

        case REGISTER_DEVICE:

            try {

                logger.info("Try to register device ...");

                ResponseEntity<Connection> responseRegistration = restTemplate.postForEntity(url, local, Connection.class);
                local = responseRegistration.getBody();

                logger.info("Device registered. Status: " + responseRegistration.getStatusCode() + " Response body: " + local);

                status.setCurrent(STATUS_TYPE.REGISTER_DATA_SOURCES);

            } catch (Exception ex) {
                logger.error("Register error. Exception {}", ex);
            }

            break;

        case REGISTER_DATA_SOURCES:

            try {

                logger.info("Convey data sources ...");

                DataSources data = UtilsConfiguration.loadMeasurementData();

                Connection cmConnection = UtilsConfiguration.getCMConnection();
                url = UtilsResource.getUrl(RESOURCE_NAMING.CMGMT_REGISTER_DEVICE_SOURCES, cmConnection);
                url = url.replace("{id}", String.valueOf(local.getId()));

                ResponseEntity<Void> responseRegisteriationSources = restTemplate.postForEntity(url, data, Void.class);
                logger.info("Sources of device registered. Status: " + responseRegisteriationSources.getStatusCode() + " Response body: ");

                status.setCurrent(STATUS_TYPE.WORKING);

            } catch (Exception ex) {
                logger.error("Error registration sources of device. Url: {}; Exception {}", url, ex);
            }

            break;

        case WORKING:

            // TODO send heart beat
            logger.info("Send heart beat: " + dateFormat.format(new Date()));

            break;

        case ERROR:
            break;

        default:
            break;
        }

    }
}
