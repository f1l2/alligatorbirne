package configuration.management.repo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import common.data.Connection;
import common.transformer.Transformer;
import configuration.management.model.IoTDeviceRO;
import configuration.management.util.Util;

@Component
public class IoTDeviceTransformer extends Transformer<IoTDeviceRO, Connection> {

    final static Logger logger = LoggerFactory.getLogger(Transformer.class);

    @Override
    public IoTDeviceRO toLocal(Connection remote) {

        if (null == remote) {
            return null;
        }

        IoTDeviceRO device = new IoTDeviceRO();
        device.setId(remote.getId());
        device.setAuthority(remote.getUrl().getAuthority());

        return device;
    }

    @Override
    public Connection toRemote(IoTDeviceRO local) {

        if (null == local) {
            return null;
        }

        Connection connection = new Connection();
        connection.setId(local.getId());
        connection.setUrl(Util.parseUrl(local.getAuthority()));

        return connection;
    }

}
