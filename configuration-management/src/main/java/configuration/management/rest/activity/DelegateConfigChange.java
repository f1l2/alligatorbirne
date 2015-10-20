package configuration.management.rest.activity;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

import common.data.ConfigurationDelegation;
import common.data.Connection;
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

    @Autowired
    private ThreadPoolTaskExecutor taskExecutor;

    @Override
    public ResponseEntity<ConfigurationDelegation> doStep(ConfigurationDelegation item) {

        List<IoTDeviceRO> devicesToBeContacted = deviceRepo.findByIoTDeviceDataSources(item.getDeviceInformation().getName(), item.getDomainInformation().getName());

        List<Connection> connectionsToBeContacted = transformer.toRemote(devicesToBeContacted);

        taskExecutor.execute(new DelegationTask(item.getConfigurationModification(), connectionsToBeContacted));

        return next(item, item);
    }

}
