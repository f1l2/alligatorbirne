package common.data.config;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.SAXException;

import common.data.Configuration;
import common.data.Connection;
import common.data.DataSource;
import common.data.DataSources;
import common.data.configuration.XMLConfiguration;
import common.data.configuration.XMLConnection;
import common.data.configuration.XMLConnections;
import common.data.type.COMPONENT_TYPE;
import common.transformer.XMLConfigurationTransformer;
import common.transformer.XMLConnectionTransformer;
import common.transformer.XMLDataSourceTransformer;
import common.transformer.XMLParser;

public class UtilsConfiguration {

    final static Logger logger = LoggerFactory.getLogger(UtilsConfiguration.class);

    private static final String PATH_TO_CONFIG_FILE = "src/main/resources/configuration.xml";

    /**
     * Method for retrieving a set of connection data.
     * 
     * @throws SAXException
     * 
     */
    public static List<Connection> getConnections(COMPONENT_TYPE component) throws MalformedURLException, JAXBException, SAXException {

        final XMLConnectionTransformer transformer = new XMLConnectionTransformer();

        final List<Connection> connection = new ArrayList<Connection>();

        for (XMLConnection xMLConnection : loadConnections().getConnection()) {
            if (component.equals(xMLConnection.getComponent())) {
                connection.add(transformer.toRemote(xMLConnection));
            }
        }
        return connection;
    }

    /**
     * Retrieve connection data for the defined configuration management component.
     * 
     * @throws SAXException
     * 
     */
    public static Connection getCMConnection() throws MalformedURLException, JAXBException, SAXException {
        final List<Connection> connections = getConnections(COMPONENT_TYPE.CONFIGURATION_MANAGEMENT);

        if (connections.size() > 1) {
            logger.error("Configuraiton file contains multiple 'CM connection' entries. Problem of ambiguous.");
        }

        if (null != connections && connections.size() > 0) {
            return connections.get(0);
        }

        return new Connection();
    }

    /**
     * Replace configuration management component
     * 
     * @throws SAXException
     */

    public static Configuration replaceCMConnection(Connection newConnection) throws MalformedURLException, JAXBException, SAXException {

        return UtilsConfiguration.replace(UtilsConfiguration.getCMConnection(), newConnection);
    }

    private static Configuration replace(Connection oldConnection, Connection newConnection) throws MalformedURLException, JAXBException, SAXException {

        Configuration configuration = UtilsConfiguration.loadConfiguration();
        configuration.getConnections().remove(oldConnection);
        configuration.getConnections().add(newConnection);

        return configuration;
    }

    /**
     * Retrieve a set of connection data for the defined event processing components.
     * 
     * @throws SAXException
     */
    public static List<Connection> getEPConnection() throws MalformedURLException, JAXBException, SAXException {

        return getConnections(COMPONENT_TYPE.EVENT_PROCESSING);
    }

    /**
     * Retrieve a set of connection data for the defined iot components.
     * 
     * @throws SAXException
     */
    public static Connection getIoTDeviceConnection() throws MalformedURLException, JAXBException, SAXException {

        final List<Connection> connections = getConnections(COMPONENT_TYPE.IOT_DEVICE);

        if (connections.size() > 1) {
            logger.error("Configuraiton file contains multiple 'EP connection' entries. Problem of ambiguous.");
        }

        if (null != connections && connections.size() > 0) {
            return connections.get(0);
        }

        return new Connection();
    }

    /**
     * Replace iot component.
     * 
     * @throws SAXException
     */
    public static Configuration replaceIoTDeviceConnection(Connection newConnection) throws MalformedURLException, JAXBException, SAXException {

        return UtilsConfiguration.replace(UtilsConfiguration.getIoTDeviceConnection(), newConnection);
    }

    /**
     * Retrieve all connection data.
     * 
     * @throws SAXException
     */
    public static XMLConnections loadConnections() throws MalformedURLException, JAXBException, SAXException {

        final XMLConfiguration xMLconfiguration = loadConfigurationNative(PATH_TO_CONFIG_FILE);

        return xMLconfiguration.getConnections();
    }

    /**
     * Retrieve all measurement data.
     * 
     * @throws SAXException
     */
    public static DataSources loadMeasurementData() throws MalformedURLException, JAXBException, SAXException {

        final XMLConfiguration xMLconfiguration = loadConfigurationNative(PATH_TO_CONFIG_FILE);
        final XMLDataSourceTransformer transformer = new XMLDataSourceTransformer();

        List<DataSource> remote = transformer.toRemote(xMLconfiguration.getDataSources().getDataSource());

        DataSources measurementData = new DataSources();
        measurementData.add(remote);

        return measurementData;
    }

    /**
     * Load and unmarshal xml configuration file. Return native data structure generated by xsd definition.
     * 
     * @throws SAXException
     */
    public static XMLConfiguration loadConfigurationNative(String pathToConfigFile) throws MalformedURLException, JAXBException, SAXException {

        final File configurationFile = new File(pathToConfigFile);

        URI configurationURI = configurationFile.toURI();
        configurationURI = configurationURI.normalize();

        return XMLParser.unmarshal(configurationURI);
    }

    /**
     * Load configuration.
     * 
     * @throws SAXException
     */
    public static Configuration loadConfiguration() throws MalformedURLException, JAXBException, SAXException {

        return UtilsConfiguration.loadConfiguration(PATH_TO_CONFIG_FILE);
    }

    public static Configuration loadConfiguration(String pathToConfigFile) throws MalformedURLException, JAXBException, SAXException {

        final XMLConfiguration loadConfigurationNative = UtilsConfiguration.loadConfigurationNative(pathToConfigFile);

        final XMLConfigurationTransformer transformer = new XMLConfigurationTransformer();

        return transformer.toRemote(loadConfigurationNative);

    }

    /**
     * Save configuration. Pass native data structure generated by xsd definition.
     * 
     * @throws SAXException
     */
    public static void saveConfigurationNative(XMLConfiguration configuration, String pathToConfigFile) throws JAXBException, SAXException {

        final File configurationFile = new File(pathToConfigFile);

        XMLParser.marshal(configuration, configurationFile);

    }

    /**
     * Save configuration.
     * 
     * @throws SAXException
     */
    public static void saveConfiguration(Configuration configuration) throws JAXBException, SAXException {

        UtilsConfiguration.saveConfiguration(configuration, PATH_TO_CONFIG_FILE);

    }

    public static void saveConfiguration(Configuration configuration, String pathToConfigFile) throws JAXBException, SAXException {
        final XMLConfigurationTransformer transformer = new XMLConfigurationTransformer();

        UtilsConfiguration.saveConfigurationNative(transformer.toLocal(configuration), pathToConfigFile);
    }

}
