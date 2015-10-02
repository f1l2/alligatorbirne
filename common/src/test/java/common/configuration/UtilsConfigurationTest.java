package common.configuration;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;

import javax.xml.bind.JAXBException;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.xml.sax.SAXException;

import common.data.Configuration;
import common.data.Connection;
import common.data.DataSource;
import common.data.DeviceInformation;
import common.data.DomainInformation;
import common.data.config.UtilsConfiguration;
import common.data.type.COMPONENT_TYPE;
import common.data.type.DEVICE_INFORMATION_TYPE;
import common.data.type.DOMAIN_INFORMATION_TYPE;

public class UtilsConfigurationTest {

    private static final String PATH_TO_CONFIGURATION_FILE = "src/test/resources/configuration.xml";

    private static final String PATH_TO_TEST_OUTPUT = "target/configuration_test_output.xml";

    private File configurationFile;

    private URI configurationURI;

    private Configuration configuration;

    @Before
    public void before() throws MalformedURLException, JAXBException, SAXException {
        configurationFile = new File(PATH_TO_CONFIGURATION_FILE);
        configurationURI = configurationFile.toURI();
        configurationURI = configurationURI.normalize();

        UtilsConfiguration.setPATH_TO_CONFIG_FILE(PATH_TO_CONFIGURATION_FILE);

        configuration = UtilsConfiguration.loadConfiguration();

        Assert.assertNotNull(configuration);
        Assert.assertNotNull(configuration.getConnections());
        Assert.assertNotNull(configuration.getDataSources());
        Assert.assertEquals(2, configuration.getConnections().size());
        Assert.assertEquals(1, configuration.getDataSources().size());
    }

    @Test
    public void saveNewConnection() throws MalformedURLException, JAXBException, SAXException {

        URL url = new URL("http", "localhost", 1234, "/");
        Connection newConnection = new Connection();
        newConnection.setName("new connection");
        newConnection.setUrl(url);

        configuration.getConnections().add(newConnection);

        UtilsConfiguration.setPATH_TO_CONFIG_FILE(PATH_TO_TEST_OUTPUT);

        UtilsConfiguration.saveConfiguration(configuration);

        Configuration newConfiguration = UtilsConfiguration.loadConfiguration();

        Assert.assertNotNull(newConfiguration);
        Assert.assertNotNull(newConfiguration.getConnections());
        Assert.assertNotNull(newConfiguration.getDataSources());
        Assert.assertEquals(3, newConfiguration.getConnections().size());
        Assert.assertEquals(1, newConfiguration.getDataSources().size());

        Assert.assertEquals("localhost", newConfiguration.getConnections().get(2).getUrl().getHost());
        Assert.assertEquals(1234, newConfiguration.getConnections().get(2).getUrl().getPort());
    }

    @Test
    public void saveNewDataSource() throws MalformedURLException, JAXBException, SAXException {

        DeviceInformation deviceInformation = new DeviceInformation();
        deviceInformation.setName("SENSOR 1");
        deviceInformation.setType(DEVICE_INFORMATION_TYPE.SENSOR);

        DomainInformation domainInformation = new DomainInformation();
        domainInformation.setName("DOMAIN 1");
        domainInformation.setType(DOMAIN_INFORMATION_TYPE.FIRST_FLOOR);

        DataSource dataSource = new DataSource();
        dataSource.setDeviceInformation(deviceInformation);
        dataSource.setDomainInformation(domainInformation);

        configuration.getDataSources().add(dataSource);

        UtilsConfiguration.setPATH_TO_CONFIG_FILE(PATH_TO_TEST_OUTPUT);

        UtilsConfiguration.saveConfiguration(configuration);

        Configuration newConfiguration = UtilsConfiguration.loadConfiguration();

        Assert.assertNotNull(newConfiguration);
        Assert.assertNotNull(newConfiguration.getConnections());
        Assert.assertNotNull(newConfiguration.getDataSources());
        Assert.assertEquals(2, newConfiguration.getConnections().size());
        Assert.assertEquals(2, newConfiguration.getDataSources().size());

        Assert.assertEquals("SENSOR 1", newConfiguration.getDataSources().get(1).getDeviceInformation().getName());
        Assert.assertEquals("DOMAIN 1", newConfiguration.getDataSources().get(1).getDomainInformation().getName());

    }

    @Test
    public void replace() throws MalformedURLException, JAXBException, SAXException {

        URL url = new URL("http", "localhost", 1234, "/");
        Connection newConnection = new Connection();
        newConnection.setName("new connection");
        newConnection.setUrl(url);
        newConnection.setComponentType(COMPONENT_TYPE.IOT_DEVICE);

        Configuration newConfiguration1 = UtilsConfiguration.replaceConnection(newConnection, COMPONENT_TYPE.IOT_DEVICE);

        UtilsConfiguration.setPATH_TO_CONFIG_FILE(PATH_TO_TEST_OUTPUT);

        UtilsConfiguration.saveConfiguration(newConfiguration1);

        Configuration newConfiguration2 = UtilsConfiguration.loadConfiguration();

        Assert.assertNotNull(newConfiguration2);
        Assert.assertNotNull(newConfiguration2.getConnections());
        Assert.assertNotNull(newConfiguration2.getDataSources());
        Assert.assertEquals(2, newConfiguration2.getConnections().size());
        Assert.assertEquals(1, newConfiguration2.getDataSources().size());

        before();

    }

}
