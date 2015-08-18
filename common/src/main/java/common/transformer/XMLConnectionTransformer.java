package common.transformer;

import common.data.Connection;
import common.data.configuration.XMLConnection;

public class XMLConnectionTransformer extends Transformer<XMLConnection, Connection> {

    @Override
    public XMLConnection toLocal(Connection remote) {

        if (null == remote) {
            return null;
        }

        XMLConnection xMLConnection = new XMLConnection();
        xMLConnection.setIp(remote.getIp());
        xMLConnection.setPort(remote.getPort());
        xMLConnection.setUrl(remote.getUrl());

        return xMLConnection;
    }

    @Override
    public Connection toRemote(XMLConnection local) {

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
