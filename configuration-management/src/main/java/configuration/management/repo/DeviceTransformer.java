package configuration.management.repo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import common.data.Connection;
import common.transformer.Transformer;
import configuration.management.model.Device;
import configuration.management.util.Util;

@Component
public class DeviceTransformer extends Transformer<Device, Connection> {

    final static Logger logger = LoggerFactory.getLogger(Transformer.class);

    @Override
    public Device toLocal(Connection remote) {

        if (null == remote) {
            return null;
        }

        Device device = new Device();
        device.setId(remote.getId());
        device.setAuthority(remote.getUrl().getAuthority());

        return device;
    }

    @Override
    public Connection toRemote(Device local) {

        if (null == local) {
            return null;
        }

        Connection connection = new Connection();
        connection.setId(local.getId());
        connection.setUrl(Util.parseUrl(local.getAuthority()));
        connection.setName(local.getName());
        connection.setUpdated(local.getUpdated());

        return connection;
    }

}
