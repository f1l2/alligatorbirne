package event.processing;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import common.data.ConnectionProperties;
import common.data.MeasurementData;
import common.data.config.UtilsConfig;
import common.data.configuration.ConnectionConfig;
import common.rest.RESOURCE_NAMING;
import common.transformer.ConnectionTransformer;

import event.processing.engine.Engine;
import event.processing.engine.EngineFactory;
import event.processing.engine.impl.EsperEngineFactory;

@Component
public class Scheduler {

    final static Logger logger = Logger.getLogger(Scheduler.class);

    private static int status = 0;

    private static String url;

    private static ConnectionProperties connection;

    private final static SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    @Scheduled(fixedRate = 20000)
    public void action() {

        RestTemplate restTemplate = new RestTemplate();

        if (status == 0) {
            try {
                ConnectionConfig cmConnection = UtilsConfig.getCMConnection();
                url = RESOURCE_NAMING.CMGMT_REGISTER_EVENT_PROCESSING.getUrl(cmConnection);

                ConnectionConfig epConnectionConfig = UtilsConfig.getEPConnection();
                ConnectionTransformer transformer = new ConnectionTransformer();
                connection = transformer.toRemote(epConnectionConfig);

                ResponseEntity<ConnectionProperties> responseRegistration = restTemplate.postForEntity(url, connection, ConnectionProperties.class);
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
                MeasurementData data = UtilsConfig.loadMeasurementData();

                ConnectionConfig cmConnection = UtilsConfig.getCMConnection();
                url = RESOURCE_NAMING.CMGMT_DELEGATION.getUrl(cmConnection);
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
