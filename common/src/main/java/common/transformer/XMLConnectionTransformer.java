package common.transformer;

import java.net.URL;

import common.data.Connection;
import common.data.configuration.XMLConnection;

public class XMLConnectionTransformer extends Transformer<XMLConnection, Connection> {

    @Override
    public XMLConnection toLocal(Connection remote) {

        if (null == remote) {
            return null;
        }

        XMLConnection xMLConnection = new XMLConnection();
        xMLConnection.setHost(remote.getUrl().getHost());
        xMLConnection.setPort(Integer.toString(remote.getUrl().getPort()));

        return xMLConnection;
    }

    @Override
    public Connection toRemote(XMLConnection local) {

        if (null == local) {
            return null;
        }

        URL url = null;
        try {
            url = new URL("http", local.getHost(), Integer.parseInt(local.getPort()), "/");
        } catch (Exception ex) {
            logger.error("Error parsing configuration file (connection element). {}", ex);
        }

        Connection connection = new Connection();
        connection.setName(local.getName());
        connection.setUrl(url);

        return connection;
    }

}
