package configuration.management.rest.activity;

import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

import common.data.ConfigurationDelegation;
import common.data.Connection;
import common.data.DataSource;
import common.data.type.COMPONENT_TYPE;
import configuration.management.model.DeviceDLO;
import configuration.management.repo.DeviceRepository;
import configuration.management.repo.DeviceTransformer;
import configuration.management.rest.task.ExecuteRestTask;
import configuration.management.rest.task.SetConfigDelegation;

@Component
public class DelegateConfigChange extends Activity<String, ConfigurationDelegation> {

    final static Logger logger = LoggerFactory.getLogger(DelegateConfigChange.class);
    @Autowired
    private DeviceRepository deviceRepo;

    @Autowired
    private DeviceTransformer transformer;

    @Autowired
    private ThreadPoolTaskExecutor taskExecutor;

    @Override
    public ResponseEntity<String> doStep(ConfigurationDelegation item) {

        /**
         * No worry about NPE because item was already checked during validations step.
         */

        for (DataSource dsEP : item.getDataSources()) {
            for (DeviceDLO dev : deviceRepo.findByDataSources(dsEP.getDeviceInformation().getName(), dsEP.getDomainInformation().getName())) {

                Connection remote = transformer.toRemote(dev);
                remote.setComponentType(COMPONENT_TYPE.DEVICE);

                taskExecutor.execute(new ExecuteRestTask<SetConfigDelegation>(new SetConfigDelegation(item, Arrays.asList(remote))));
            }
        }
        return next("OK", item);
    }

}
