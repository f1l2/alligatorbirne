package common.transformer;

import java.net.URL;

import common.data.Connection;
import common.data.setting.XMLComponent;
import common.data.setting.XMLConnection;
import common.data.type.COMPONENT_TYPE;

public class XMLConnectionTransformer extends Transformer<XMLConnection, Connection> {

    @Override
    public XMLConnection toLocal(Connection remote) {

        if (null == remote) {
            return null;
        }

        XMLConnection xMLConnection = new XMLConnection();
        xMLConnection.setName(remote.getName());
        xMLConnection.setHost(remote.getUrl().getHost());
        xMLConnection.setPort(Integer.toString(remote.getUrl().getPort()));

        if (COMPONENT_TYPE.CONFIGURATION_MANAGEMENT.equals(remote.getComponentType())) {
            xMLConnection.setComponent(XMLComponent.CONFIGURATION_MANAGEMENT);
        } else if (COMPONENT_TYPE.EVENT_PROCESSING.equals(remote.getComponentType())) {
            xMLConnection.setComponent(XMLComponent.EVENT_PROCESSING);
        } else if (COMPONENT_TYPE.IOT_DEVICE.equals(remote.getComponentType())) {
            xMLConnection.setComponent(XMLComponent.IOT_DEVICE);
        } else if (COMPONENT_TYPE.LOCAL.equals(remote.getComponentType())) {
            xMLConnection.setComponent(XMLComponent.LOCAL);
        }

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
            logger.error("Error parsing setting file (connection element). {}", ex);
        }

        Connection connection = new Connection();
        connection.setName(local.getName());
        connection.setUrl(url);

        if (XMLComponent.CONFIGURATION_MANAGEMENT.equals(local.getComponent())) {
            connection.setComponentType(COMPONENT_TYPE.CONFIGURATION_MANAGEMENT);
        } else if (XMLComponent.EVENT_PROCESSING.equals(local.getComponent())) {
            connection.setComponentType(COMPONENT_TYPE.EVENT_PROCESSING);
        } else if (XMLComponent.IOT_DEVICE.equals(local.getComponent())) {
            connection.setComponentType(COMPONENT_TYPE.IOT_DEVICE);
        } else if (XMLComponent.LOCAL.equals(local.getComponent())) {
            connection.setComponentType(COMPONENT_TYPE.LOCAL);
        }

        return connection;
    }

}
