package iot.device.repo;

import iot.device.IotDevice;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import common.data.Connection;
import common.data.MeasurementData;

@Component
public class ScheduledJob {

    final static Logger logger = Logger.getLogger(ScheduledJob.class);

    @Autowired
    private IotDevice iotDevice;

    private static int statusRegistration = 0;

    private static Connection connection;

    private static String url;

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    @Scheduled(fixedRate = 50000)
    public void reportCurrentTime() {

        RestTemplate restTemplate = new RestTemplate();

        if (statusRegistration == 0) {

            try {

                url = "http://" + iotDevice.getCMConnection().getUrl() + "/registrations";

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
                MeasurementData data = iotDevice.loadMeasurementData();

                url = "http://" + iotDevice.getCMConnection().getUrl() + "/registrations/sources/" + connection.getId();

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
