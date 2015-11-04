package configuration.management.rest.activity;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

import common.data.Connection;
import common.data.DataSource;
import common.data.dto.DataSourcesDTO;
import configuration.management.model.Device;
import configuration.management.repo.DeviceRepository;
import configuration.management.repo.DeviceTransformer;
import configuration.management.task.StartDeliveryDelegation;
import configuration.management.task.StopDeliveryDelegation;

@Component
public class DelegateDeliveryChange extends Activity<String, DataSourcesDTO> {

    final static Logger logger = LoggerFactory.getLogger(DelegateDeliveryChange.class);
    @Autowired
    private DeviceRepository deviceRepo;

    @Autowired
    private DeviceTransformer transformer;

    @Autowired
    private ThreadPoolTaskExecutor taskExecutor;

    private boolean isStart = true;

    @Override
    public ResponseEntity<String> doStep(DataSourcesDTO item) {

        /**
         * No worry about NPE because item was already checked during validations step.
         */
        List<Device> devicesToBeContacted = new ArrayList<Device>();

        for (DataSource ds : item.getDataSources()) {
            devicesToBeContacted.addAll(deviceRepo.findByDataSources(//
                    ds.getDeviceInformation().getName().toLowerCase(),
                    //
                    ds.getDomainInformation().getName().toLowerCase()));
        }

        List<Connection> connectionsToBeContacted = transformer.toRemote(devicesToBeContacted);

        if (isStart) {
            taskExecutor.execute(new StartDeliveryDelegation(item, connectionsToBeContacted));
        } else {
            taskExecutor.execute(new StopDeliveryDelegation(item, connectionsToBeContacted));
        }

        return next("OK", item);
    }

    public boolean isStart() {
        return isStart;
    }

    public void setStart(boolean isStart) {
        this.isStart = isStart;
    }

}
