package configuration.management.rest.activity;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import common.data.ConfigurationModification;
import common.data.Connection;
import common.rest.RESOURCE_NAMING;
import common.rest.ResourceUtils;

public class DelegationTask implements Runnable {

    final static Logger logger = LoggerFactory.getLogger(DelegationTask.class);

    private ConfigurationModification cm;

    private List<Connection> connections;

    public DelegationTask(ConfigurationModification cm, List<Connection> connections) {
        this.cm = cm;
        this.connections = connections;
    }

    @Override
    public void run() {

        RestTemplate restTemplate = new RestTemplate();

        for (Connection connection : connections) {

            logger.info("Delegate configuration modification for {}", connection.getUrl().getAuthority());

            try {
                String url = ResourceUtils.getUrl(RESOURCE_NAMING.IDEV_SET_CONFIGURATION, connection.getUrl().getAuthority());
                ResponseEntity<String> response = restTemplate.postForEntity(url, cm, String.class);
                logger.info("Device notification - " + url + " Status: " + response.getStatusCode() + " Response body: " + response.getBody());
            } catch (Exception e) {
                logger.error("{}", e);

            }
        }
    }

    public void stop() {

    }

}
