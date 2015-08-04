package configuration.management.repo;

import org.springframework.stereotype.Component;

import common.data.Connection;
import common.transformer.Transformer;

import configuration.management.model.DeviceJPA;

@Component
public class DeviceTransformer extends Transformer<DeviceJPA, Connection> {

    @Override
    public DeviceJPA toLocal(Connection remote) {

        if (null == remote) {
            return null;
        }

        DeviceJPA device = new DeviceJPA();
        device.setId(remote.getId());
        device.setUrl(remote.getUrl());

        return device;
    }

    @Override
    public Connection toRemote(DeviceJPA local) {

        if (null == local) {
            return null;
        }

        Connection connection = new Connection();
        connection.setId(local.getId());
        connection.setUrl(local.getUrl());

        return connection;
    }

}
