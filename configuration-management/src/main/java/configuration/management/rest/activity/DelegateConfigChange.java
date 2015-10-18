package configuration.management.rest.activity;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import common.data.ConfigurationDelegation;
import common.data.Connection;
import common.rest.RESOURCE_NAMING;
import common.rest.UtilsResource;
import configuration.management.model.IoTDeviceRO;
import configuration.management.repo.IoTDeviceRepository;
import configuration.management.repo.IoTDeviceTransformer;

@Component
public class DelegateConfigChange extends Activity<ConfigurationDelegation, ConfigurationDelegation> {

    final static Logger logger = LoggerFactory.getLogger(DelegateConfigChange.class);
    @Autowired
    private IoTDeviceRepository deviceRepo;

    @Autowired
    private IoTDeviceTransformer transformer;

    @Override
    public ResponseEntity<ConfigurationDelegation> doStep(ConfigurationDelegation item) {

        List<IoTDeviceRO> devicesToBeContacted = deviceRepo.findByIoTDeviceDataSources(item.getDeviceInformation().getName(), item.getDomainInformation().getName());

        List<Connection> connectionsToBeContacted = transformer.toRemote(devicesToBeContacted);

        for (Connection connection : connectionsToBeContacted) {
            try {
                RestTemplate restTemplate = new RestTemplate();
                String url = UtilsResource.getUrl(RESOURCE_NAMING.IDEV_SET_CONFIGURATION, connection.getUrl().getAuthority());
                ResponseEntity<String> response = restTemplate.postForEntity(url, item.getConfigurationModification(), String.class);
                logger.info("Device notification - " + url + " Status: " + response.getStatusCode() + " Response body: " + response.getBody());
            } catch (Exception e) {
                logger.error("{}", e);
                this.setErrorResponse(new ResponseEntity<ConfigurationDelegation>(item, HttpStatus.BAD_REQUEST));
            }
        }
        return next(item, item);
    }

}
