package common.transformer;

import common.data.Connection;
import common.data.configuration.ConnectionConfig;

public class ConnectionTransformer extends Transformer<ConnectionConfig, Connection> {

    @Override
    public ConnectionConfig toLocal(Connection remote) {

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
    public Connection toRemote(ConnectionConfig local) {

        if (null == local) {
            return null;
        }

        Connection connection = new Connection();
        connection.setIp(local.getIp());
        connection.setPort(local.getPort());
        connection.setUrl(local.getUrl());

        return connection;
    }

}
