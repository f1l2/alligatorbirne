package event.processing;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import common.data.Connection;
import common.data.DataSources;
import common.data.config.UtilsConfiguration;
import common.rest.RESOURCE_NAMING;
import common.rest.UtilsResource;

import event.processing.engine.Engine;
import event.processing.engine.EngineFactory;
import event.processing.engine.impl.EsperEngineFactory;

@Component
public class ApplicationScheduler {

    final static Logger logger = Logger.getLogger(ApplicationScheduler.class);

    private static UtilsConfiguration utilsConfig = new UtilsConfiguration();

    private static int status = 0;

    private static String url;

    private static Connection connection;

    private final static SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    @Scheduled(fixedRate = 20000)
    public void action() {

        RestTemplate restTemplate = new RestTemplate();

        if (status == 0) {
            try {
                Connection cmConnection = utilsConfig.getCMConnection();

                UtilsResource.getUrl(RESOURCE_NAMING.CMGMT_REGISTER_EVENT_PROCESSING, cmConnection);

                connection = utilsConfig.getEPConnection().get(0);

                ResponseEntity<Connection> responseRegistration = restTemplate.postForEntity(url, connection, Connection.class);
                connection = responseRegistration.getBody();

                logger.info("Event processing registered. Status: " + responseRegistration.getStatusCode() + " Response body: " + connection);

                status++;

            } catch (Exception ex) {
                logger.error("Registration of event processing failed - " + url);
                logger.error(ex);
            }

        } else if (status == 1) {

            EngineFactory factory = new EsperEngineFactory();

            Engine engine = factory.getEngine();

            engine.registerQuery("select * from DeviceInformation");

            status++;

        }

        else if (status == 2) {

            try {
                DataSources data = UtilsConfiguration.loadMeasurementData();

                Connection cmConnection = utilsConfig.getCMConnection();

                url = UtilsResource.getUrl(RESOURCE_NAMING.CMGMT_DELEGATION, cmConnection);
                url = url.replace("{id}", String.valueOf(connection.getId()));

                ResponseEntity<Void> responseRegisteriationSources = restTemplate.postForEntity(url, data, Void.class);
                logger.info("Delegation successfully called. Status: " + responseRegisteriationSources.getStatusCode() + " Response body: ");

                status++;

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
