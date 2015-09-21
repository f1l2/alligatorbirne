package common.data.config;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBException;

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

    public static List<Connection> getConnections(COMPONENT_TYPE component) throws MalformedURLException, JAXBException {

        XMLConnectionTransformer transformer = new XMLConnectionTransformer();

        List<Connection> connection = new ArrayList<Connection>();

        for (XMLConnection xMLConnection : loadConnections().getConnection()) {
            if (component.equals(xMLConnection.getComponent())) {
                connection.add(transformer.toRemote(xMLConnection));
            }
        }
        return connection;
    }

    public static Connection getCMConnection() throws MalformedURLException, JAXBException {
        List<Connection> connections = getConnections(COMPONENT_TYPE.CONFIGURATION_MANAGEMENT);

        if (null != connections && connections.size() > 0) {
            return connections.get(0);
        } else {
            return null;
        }
    }

    public static List<Connection> getEPConnection() throws MalformedURLException, JAXBException {

        return getConnections(COMPONENT_TYPE.EVENT_PROCESSING);
    }

    public static List<Connection> getIoTDevicesConnection() throws MalformedURLException, JAXBException {

        return getConnections(COMPONENT_TYPE.IOT_DEVICE);
    }

    public static XMLConnections loadConnections() throws MalformedURLException, JAXBException {

        XMLConfiguration xMLconfiguration = loadConfiguration();

        return xMLconfiguration.getConnections();
    }

    public static DataSources loadMeasurementData() throws MalformedURLException, JAXBException {

        XMLConfiguration xMLconfiguration = loadConfiguration();
        XMLConfigurationTransformer transformer = new XMLConfigurationTransformer();

        List<DataSource> remote = transformer.toRemote(xMLconfiguration.getDataSources().getDataSource());

        DataSources measurementData = new DataSources();
        measurementData.add(remote);

        return measurementData;
    }

    public static XMLConfiguration loadConfiguration() throws MalformedURLException, JAXBException {

        String configurationStr = "src/main/resources/configuration.xml";
        File configurationFile = new File(configurationStr);

        URI configurationURI = configurationFile.toURI();
        configurationURI = configurationURI.normalize();

        return XMLParser.unmarshal(configurationURI);
    }

    public static void saveConfiguration(XMLConfiguration configuration) throws JAXBException {

        String configurationStr = "src/main/resources/configuration.xml";
        File configurationFile = new File(configurationStr);

        XMLParser.marshal(configuration, configurationFile);

    }

}
