package iot.device;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import common.data.Connection;
import common.data.MeasurementData;
import common.data.config.UtilsConfig;
import common.data.configuration.ConnectionConfig;
import common.rest.Url;

@Component
public class Scheduler {

    final static Logger logger = Logger.getLogger(Scheduler.class);

    private static int statusRegistration = 0;

    private static Connection connection;

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    @Scheduled(fixedRate = 20000)
    public void action() {

        RestTemplate restTemplate = new RestTemplate();
        String url = null;

        if (statusRegistration == 0) {

            try {

                ConnectionConfig cmConnection = UtilsConfig.getCMConnection();
                url = Url.CMGMT_REGISTER_DEVICE.getUrl(cmConnection);

                connection = new Connection();
                connection.setIp("127.0.0.1");
                connection.setPort("5002");
                connection.setUrl("http://127.0.0.1:5002");

                ResponseEntity<Connection> responseRegistration = restTemplate.postForEntity(url, connection, Connection.class);
                connection = responseRegistration.getBody();

                logger.info("Device registered. Status: " + responseRegistration.getStatusCode() + " Response body: " + connection);

                statusRegistration++;

            } catch (Exception ex) {
                logger.error("Register error. Call: " + url);
                logger.error(ex);
            }

        } else if (statusRegistration == 1) {

            try {
                MeasurementData data = UtilsConfig.loadMeasurementData();

                ConnectionConfig cmConnection = UtilsConfig.getCMConnection();
                url = Url.CMGMT_REGISTER_DEVICE_SOURCES.getUrl(cmConnection);
                url = url.replace("{id}", String.valueOf(connection.getId()));

                ResponseEntity<Void> responseRegisteriationSources = restTemplate.postForEntity(url, data, Void.class);
                logger.info("Sources of device registered. Status: " + responseRegisteriationSources.getStatusCode() + " Response body: ");

                statusRegistration++;

            } catch (Exception ex) {
                logger.error("Error registration sources of device. Url: " + url);
                logger.error(ex);
            }

        } else {
            // TODO send heart beat
            logger.info("Send heart beat: " + dateFormat.format(new Date()));
        }

    }
}
