package configuration.management.rest.activity;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

import common.data.ConfigurationDelegation;
import common.data.Connection;
import common.data.DataSource;
import configuration.management.model.DeviceDLO;
import configuration.management.repo.DeviceRepository;
import configuration.management.repo.DeviceTransformer;
import configuration.management.rest.task.ExecuteRestTask;
import configuration.management.rest.task.StartDeliveryDelegation;
import configuration.management.rest.task.StopDeliveryDelegation;

@Component
public class DelegateDeliveryChange extends Activity<String, ConfigurationDelegation> {

    final static Logger logger = LoggerFactory.getLogger(DelegateDeliveryChange.class);
    @Autowired
    private DeviceRepository deviceRepo;

    @Autowired
    private DeviceTransformer transformer;

    @Autowired
    private ThreadPoolTaskExecutor taskExecutor;

    private boolean isStop = false;

    @Override
    public ResponseEntity<String> doStep(ConfigurationDelegation item) {

        /**
         * No worry about NPE because item was already checked during validations step.
         */
        List<DeviceDLO> devicesToBeContacted = new ArrayList<DeviceDLO>();

        for (DataSource ds : item.getDataSources()) {
            devicesToBeContacted.addAll(deviceRepo.findByDataSources(//
                    ds.getDeviceInformation().getName().toLowerCase(),
                    //
                    ds.getDomainInformation().getName().toLowerCase()));
        }

        List<Connection> connectionsToBeContacted = transformer.toRemote(devicesToBeContacted);

        if (isStop) {

            taskExecutor.execute(new ExecuteRestTask<StopDeliveryDelegation>(new StopDeliveryDelegation(item, connectionsToBeContacted)));
        } else {
            taskExecutor.execute(new ExecuteRestTask<StartDeliveryDelegation>(new StartDeliveryDelegation(item, connectionsToBeContacted)));
        }

        return next("OK", item);
    }

    public boolean isStop() {
        return isStop;
    }

    public void setStop(boolean isStop) {
        this.isStop = isStop;
    }
}
