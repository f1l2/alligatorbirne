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
        xMLConnection.setHost(remote.getHost());
        xMLConnection.setPort(remote.getPort());

        return xMLConnection;
    }

    @Override
    public Connection toRemote(XMLConnection local) {

        if (null == local) {
            return null;
        }

        Connection connection = new Connection();
        connection.setHost(local.getHost());
        connection.setPort(local.getPort());
        connection.setUrl(local.getHost() + ":" + local.getPort());

        return connection;
    }

}
