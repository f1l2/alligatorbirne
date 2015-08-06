package common.transformer;

import common.data.ConnectionProperties;
import common.data.configuration.ConnectionConfig;

public class ConnectionTransformer extends Transformer<ConnectionConfig, ConnectionProperties> {

    @Override
    public ConnectionConfig toLocal(ConnectionProperties remote) {

        if (null == remote) {
            return null;
        }

        ConnectionConfig connectionConfig = new ConnectionConfig();
        connectionConfig.setIp(remote.getIp());
        connectionConfig.setPort(remote.getPort());
        connectionConfig.setUrl(remote.getUrl());

        return connectionConfig;
    }

    @Override
    public ConnectionProperties toRemote(ConnectionConfig local) {

        if (null == local) {
            return null;
        }

        ConnectionProperties connection = new ConnectionProperties();
        connection.setIp(local.getIp());
        connection.setPort(local.getPort());
        connection.setUrl(local.getUrl());

        return connection;
    }

}
