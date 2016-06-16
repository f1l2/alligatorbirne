package configuration.management.rest.activity.call;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import common.data.ConfigurationDelegation;
import common.data.Connection;
import common.data.model.DataSource;
import common.utilities.NormalizeString;
import configuration.management.model.DeviceDLO;
import configuration.management.rest.task.ExecuteRestTask;

@Component
public class DelegateDeliveryChange extends CallActivity<String, ConfigurationDelegation> {

    final static Logger logger = LoggerFactory.getLogger(DelegateDeliveryChange.class);

    private boolean isStop;

    @Override
    public ResponseEntity<String> doStep(ConfigurationDelegation item) {

        /**
         * No worry about NPE because item was already checked during validations step.
         */
        Set<DeviceDLO> devices = new HashSet<DeviceDLO>();

        for (DataSource ds : item.getDataSources()) {
            String deviceName = NormalizeString.normalize(ds.getDeviceInformation().getName());
            String domainName = NormalizeString.normalize(ds.getDomainInformation().getName());

            List<DeviceDLO> devicesTemp = deviceRepository.findByDataSources(deviceName, domainName);

            if (!CollectionUtils.isEmpty(devicesTemp)) {
                devices.addAll(devicesTemp);
            }
        }

        if (!CollectionUtils.isEmpty(devices)) {
            List<Connection> connections = deviceTransformer.toRemote(devices);
            if (isStop) {
                taskExecutor.execute(new ExecuteRestTask<StopDeliveryDelegation>(new StopDeliveryDelegation(item, connections)));
            } else {
                taskExecutor.execute(new ExecuteRestTask<StartDeliveryDelegation>(new StartDeliveryDelegation(item, connections)));
            }
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
