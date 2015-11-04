package configuration.management.task;

import java.util.List;

import org.springframework.web.client.RestTemplate;

import common.data.Connection;
import common.data.dto.DataSourcesDTO;
import common.rest.RESOURCE_NAMING;
import common.rest.ResourceUtils;

public class StartDeliveryDelegation extends DelegationTask {

    private DataSourcesDTO dsDTO;

    public StartDeliveryDelegation(DataSourcesDTO dsDTO, List<Connection> connections) {

        super(connections);

        this.dsDTO = dsDTO;
    }

    @Override
    public void doRun(Connection connection) {

        RestTemplate restTemplate = new RestTemplate();

        logger.info("Delegate start delivery for {}", connection.getUrl().getAuthority());

        try {
            String url = ResourceUtils.getUrl(RESOURCE_NAMING.IDEV_START_DELIVERY, connection.getUrl().getAuthority());
            restTemplate.postForEntity(url, dsDTO, String.class);
        } catch (Exception e) {
            logger.error("{}", e);

        }
    }
}
