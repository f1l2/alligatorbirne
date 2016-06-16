package configuration.management.rest.activity.call;

import java.util.List;

import common.data.ConfigurationDelegation;
import common.data.Connection;
import common.rest.RESOURCE_NAMING;
import common.rest.ResourceUtils;
import configuration.management.rest.task.Task;

public class StartDeliveryDelegation extends Task<Connection> {

    private ConfigurationDelegation cd;

    public StartDeliveryDelegation(ConfigurationDelegation cd, List<Connection> connections) {
        super(connections);
        this.cd = cd;
    }

    @Override
    public void doTask(Connection connection) {
        logger.info("Delegate start delivery for {}", connection.getUrl().getAuthority());
        try {
            String url = ResourceUtils.getUrl(RESOURCE_NAMING.DEV_START_DELIVERY, connection.getUrl().getAuthority());
            restTemplate.postForEntity(url, cd, String.class);
        } catch (Exception e) {
            logger.error("{}", e);
        }
    }
}
