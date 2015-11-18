package configuration.management.task;

import java.util.List;

import org.springframework.web.client.RestTemplate;

import common.data.ConfigurationDelegation;
import common.data.Connection;
import common.rest.RESOURCE_NAMING;
import common.rest.ResourceUtils;

public class StopDeliveryDelegation extends DelegationTask {

    private ConfigurationDelegation cd;

    public StopDeliveryDelegation(ConfigurationDelegation cd, List<Connection> connections) {

        super(connections);

        this.cd = cd;
    }

    @Override
    public void doRun(Connection connection) {

        RestTemplate restTemplate = new RestTemplate();

        logger.info("Delegate stop delivery for {}", connection.getUrl().getAuthority());

        try {
            String url = ResourceUtils.getUrl(RESOURCE_NAMING.DEV_STOP_DELIVERY, connection.getUrl().getAuthority());
            restTemplate.postForEntity(url, cd, String.class);
        } catch (Exception e) {
            logger.error("{}", e);
        }
    }
}
