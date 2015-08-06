package configuration.management.repo;

import org.springframework.stereotype.Component;

import common.data.ConnectionProperties;
import common.transformer.Transformer;

import configuration.management.model.DeviceJPA;

@Component
public class DeviceTransformer extends Transformer<DeviceJPA, ConnectionProperties> {

    @Override
    public DeviceJPA toLocal(ConnectionProperties remote) {

        if (null == remote) {
            return null;
        }

        DeviceJPA device = new DeviceJPA();
        device.setId(remote.getId());
        device.setUrl(remote.getUrl());

        return device;
    }

    @Override
    public ConnectionProperties toRemote(DeviceJPA local) {

        if (null == local) {
            return null;
        }

        ConnectionProperties connection = new ConnectionProperties();
        connection.setId(local.getId());
        connection.setUrl(local.getUrl());

        return connection;
    }

}
