package iot.device;

import iot.device.status.Status;

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

@Component
public class ApplicationScheduler {

    final static Logger logger = LoggerFactory.getLogger(ApplicationScheduler.class);

    private static int statusRegistration = 0;

    private static Connection connection;

    private static final UtilsConfiguration utilsConfig = new UtilsConfiguration();

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    @Autowired
    private Status status;

    @Scheduled(fixedRate = 20000)
    public void carryOutActivity() {

        RestTemplate restTemplate = new RestTemplate();
        String url = null;

        if (statusRegistration == 0) {

            try {

                Connection cmConnection = utilsConfig.getCMConnection();
                url = UtilsResource.getUrl(RESOURCE_NAMING.CMGMT_REGISTER_DEVICE, cmConnection);

                connection = new Connection();
                connection.setHost("127.0.0.1");
                connection.setPort("5002");
                connection.setUrl("http://127.0.0.1:5002");

                ResponseEntity<Connection> responseRegistration = restTemplate.postForEntity(url, connection, Connection.class);
                connection = responseRegistration.getBody();

                logger.info("Device registered. Status: " + responseRegistration.getStatusCode() + " Response body: " + connection);

                statusRegistration++;

            } catch (Exception ex) {
                logger.error("Register error. Call: " + url);
                logger.error(ex.getMessage());
            }

        } else if (statusRegistration == 1) {

            try {
                DataSources data = UtilsConfiguration.loadMeasurementData();

                Connection cmConnection = utilsConfig.getCMConnection();
                url = UtilsResource.getUrl(RESOURCE_NAMING.CMGMT_REGISTER_DEVICE_SOURCES, cmConnection);
                url = url.replace("{id}", String.valueOf(connection.getId()));

                ResponseEntity<Void> responseRegisteriationSources = restTemplate.postForEntity(url, data, Void.class);
                logger.info("Sources of device registered. Status: " + responseRegisteriationSources.getStatusCode() + " Response body: ");

                statusRegistration++;

            } catch (Exception ex) {
                logger.error("Error registration sources of device. Url: " + url);
                logger.error(ex.getMessage());
            }

        } else {
            // TODO send heart beat
            logger.info("Send heart beat: " + dateFormat.format(new Date()));
        }

    }
}
