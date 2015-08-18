package common.data.config;

import java.io.File;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import common.data.Connection;
import common.data.DataSource;
import common.data.DataSources;
import common.data.configuration.XMLConfiguration;
import common.data.configuration.XMLConnection;
import common.data.configuration.XMLConnections;
import common.data.type.COMPONENT_TYPE;
import common.transformer.XMLConfigurationTransformer;
import common.transformer.XMLConnectionTransformer;
import common.transformer.XMLParser;

public class UtilsConfiguration {

    private XMLConnectionTransformer transformer = new XMLConnectionTransformer();

    public List<Connection> getConnections(COMPONENT_TYPE component) {

        List<Connection> connection = new ArrayList<Connection>();

        for (XMLConnection xMLConnection : loadConnections().getConnection()) {
            if (component.equals(xMLConnection.getComponent())) {
                connection.add(transformer.toRemote(xMLConnection));
            }
        }
        return connection;

    }

    public Connection getCMConnection() {
        List<Connection> connections = getConnections(COMPONENT_TYPE.CONFIGURATION_MANAGEMENT);

        return connections.get(0);
    }

    public List<Connection> getEPConnection() {

        return getConnections(COMPONENT_TYPE.EVENT_PROCESSING);
    }

    public List<Connection> getIoTDevicesConnection() {

        return getConnections(COMPONENT_TYPE.IOT_DEVICE);
    }

    public static XMLConnections loadConnections() {

        XMLConfiguration xMLconfiguration = loadConfiguration();

        return xMLconfiguration.getConnections();
    }

    public static DataSources loadMeasurementData() {

        XMLConfiguration xMLconfiguration = loadConfiguration();
        XMLConfigurationTransformer transformer = new XMLConfigurationTransformer();

        List<DataSource> remote = transformer.toRemote(xMLconfiguration.getDataSources().getDataSource());

        DataSources measurementData = new DataSources();
        measurementData.add(remote);

        return measurementData;
    }

    private static XMLConfiguration loadConfiguration() {

        String configurationStr = "src/main/resources/configuration.xml";
        File configurationFile = new File(configurationStr);

        URI configurationURI = configurationFile.toURI();
        configurationURI = configurationURI.normalize();

        return XMLParser.unmarshal(configurationURI);
    }

}
