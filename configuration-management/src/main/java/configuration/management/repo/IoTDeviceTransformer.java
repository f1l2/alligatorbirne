package configuration.management.repo;

import org.springframework.stereotype.Component;

import common.data.Connection;
import common.transformer.Transformer;

import configuration.management.model.IoTDeviceRO;

@Component
public class IoTDeviceTransformer extends Transformer<IoTDeviceRO, Connection> {

    @Override
    public IoTDeviceRO toLocal(Connection remote) {

        if (null == remote) {
            return null;
        }

        IoTDeviceRO device = new IoTDeviceRO();
        device.setId(remote.getId());
        device.setUrl(remote.getUrl());

        return device;
    }

    @Override
    public Connection toRemote(IoTDeviceRO local) {

        if (null == local) {
            return null;
        }

        Connection connection = new Connection();
        connection.setId(local.getId());
        connection.setUrl(local.getUrl());

        return connection;
    }

}
