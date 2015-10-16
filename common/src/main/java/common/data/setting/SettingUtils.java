package common.data.setting;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.SAXException;

import common.data.Connection;
import common.data.DataSource;
import common.data.DataSources;
import common.data.Setting;
import common.data.type.COMPONENT_TYPE;
import common.transformer.XMLConnectionTransformer;
import common.transformer.XMLDataSourceTransformer;
import common.transformer.XMLParser;
import common.transformer.XMLSettingTransformer;

public class SettingUtils {

    final static Logger logger = LoggerFactory.getLogger(SettingUtils.class);

    private static String PATH_TO_SETTING_FILE = "setting.xml";

    public static File getPATH_TO_SETTING_FILE() {

        File file = new File(PATH_TO_SETTING_FILE);

        return file;
    }

    public static void setPATH_TO_SETTING_FILE(String pATH_TO_SETTING_FILE) {
        PATH_TO_SETTING_FILE = pATH_TO_SETTING_FILE;
    }

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

        if (null != connections && connections.size() > 0) {

            if (connections.size() > 1) {
                logger.error("Configuraiton file contains multiple 'CM connection' entries. Problem of ambiguous.");
            }

            return connections.get(0);
        }

        return new Connection();
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
    public static List<Connection> getIoTDeviceConnection() throws MalformedURLException, JAXBException, SAXException {

        return getConnections(COMPONENT_TYPE.IOT_DEVICE);
    }

    public static Connection getLocalConnection() throws MalformedURLException, JAXBException, SAXException {
        final List<Connection> connections = getConnections(COMPONENT_TYPE.LOCAL);

        if (null != connections && connections.size() > 0) {

            if (connections.size() > 1) {
                logger.error("Configuraiton file contains multiple 'LOCAL connection' entries. Problem of ambiguous.");
            }

            return connections.get(0);
        }

        return new Connection();
    }

    /**
     * Replace connection
     * 
     * @throws SAXException
     */
    public static Setting replaceConnection(Connection newConnection, COMPONENT_TYPE type) throws MalformedURLException, JAXBException, SAXException {

        Setting setting = SettingUtils.loadSetting();
        Connection tempConnection = null;

        for (Connection connection : setting.getConnections()) {
            if (type.equals(connection.getComponentType())) {
                tempConnection = connection;
                break;
            }
        }

        setting.getConnections().remove(tempConnection);
        setting.getConnections().add(newConnection);

        return setting;
    }

    /**
     * Retrieve all connection data.
     * 
     * @throws SAXException
     */
    public static XMLConnections loadConnections() throws MalformedURLException, JAXBException, SAXException {

        final XMLSetting xMLSetting = loadSettingNative();

        return xMLSetting.getConnections();
    }

    /**
     * Retrieve all measurement data.
     * 
     * @throws SAXException
     */
    public static DataSources loadMeasurementData() throws MalformedURLException, JAXBException, SAXException {

        final XMLSetting xMLsetting = loadSettingNative();
        final XMLDataSourceTransformer transformer = new XMLDataSourceTransformer();

        List<DataSource> remote = transformer.toRemote(xMLsetting.getDataSources().getDataSource());

        DataSources measurementData = new DataSources();
        measurementData.add(remote);

        return measurementData;
    }

    /**
     * Load and unmarshal xml setting file. Return native data structure generated by xsd definition.
     * 
     * @throws SAXException
     */
    public static XMLSetting loadSettingNative() throws MalformedURLException, JAXBException, SAXException {

        final File settingFile = SettingUtils.getPATH_TO_SETTING_FILE();

        URI settingURI = settingFile.toURI();
        settingURI = settingURI.normalize();

        return XMLParser.unmarshal(settingURI);
    }

    /**
     * Load configuration.
     * 
     * @throws SAXException
     */

    public static Setting loadSetting() throws MalformedURLException, JAXBException, SAXException {

        final XMLSetting loadSettingNative = SettingUtils.loadSettingNative();

        final XMLSettingTransformer transformer = new XMLSettingTransformer();

        return transformer.toRemote(loadSettingNative);

    }

    /**
     * Save setting. Pass native data structure generated by xsd definition.
     * 
     * @throws SAXException
     */
    public static void saveSettingNative(XMLSetting setting) throws JAXBException, SAXException {

        final File settingFile = SettingUtils.getPATH_TO_SETTING_FILE();

        XMLParser.marshal(setting, settingFile);

    }

    /**
     * Save setting.
     * 
     * @throws SAXException
     */

    public static void saveSetting(Setting setting) throws JAXBException, SAXException {
        final XMLSettingTransformer transformer = new XMLSettingTransformer();

        SettingUtils.saveSettingNative(transformer.toLocal(setting));
    }

}
