package configuration.management.repo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import common.data.DataSource;
import common.data.model.DeviceInformation;
import common.data.model.DomainInformation;
import common.transformer.Transformer;
import configuration.management.model.IoTDeviceDataSourceRO;

@Component
public class IoTDeviceDataSourceTransformer extends Transformer<IoTDeviceDataSourceRO, DataSource> {

    final static Logger logger = LoggerFactory.getLogger(Transformer.class);

    @Override
    public IoTDeviceDataSourceRO toLocal(DataSource remote) {

        IoTDeviceDataSourceRO item = new IoTDeviceDataSourceRO();
        item.setDevice(remote.getDeviceInformation().getName());
        item.setDomain(remote.getDomainInformation().getName());

        return item;
    }

    @Override
    public DataSource toRemote(IoTDeviceDataSourceRO local) {

        DeviceInformation devInfo = new DeviceInformation();
        devInfo.setName(local.getDevice());

        DomainInformation domInfo = new DomainInformation();
        domInfo.setName(local.getDomain());

        DataSource item = new DataSource();
        item.setDeviceInformation(devInfo);
        item.setDomainInformation(domInfo);

        return item;
    }

}
