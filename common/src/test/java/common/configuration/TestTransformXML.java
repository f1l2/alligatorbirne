package common.configuration;

import java.io.File;
import java.net.URI;
import java.util.List;

import javax.xml.bind.UnmarshalException;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import common.data.DataSource;
import common.data.configuration.XMLConfiguration;
import common.data.configuration.XMLConnections;
import common.data.configuration.XMLDataSource;
import common.data.configuration.XMLDataSources;
import common.data.configuration.XMLDeviceInformation;
import common.data.configuration.XMLDomainInformation;
import common.transformer.XMLConfigurationTransformer;
import common.transformer.XMLParser;

public class TestTransformXML {

    private String configurationStr;

    private File configurationFile;

    private URI configurationURI;

    private XMLConfigurationTransformer transformerConfig = new XMLConfigurationTransformer();

    @Before
    public void setup() {
        configurationStr = "src/test/resources/configuration.xml";
        configurationFile = new File(configurationStr);

        configurationURI = configurationFile.toURI();
        configurationURI = configurationURI.normalize();
    }

    @Test
    public void testUnmarshal() throws Exception {

        XMLConfiguration configuration = XMLParser.unmarshal(configurationURI);

        XMLDataSources measurementData = configuration.getDataSources();
        XMLConnections connections = configuration.getConnections();

        Assert.assertNotNull(measurementData.getDataSource());
        Assert.assertEquals(1, measurementData.getDataSource().size());
        Assert.assertNotNull(measurementData.getDataSource().get(0).getDeviceInformation());
        Assert.assertNotNull(measurementData.getDataSource().get(0).getDomainInformation());
        Assert.assertEquals("SENSOR", measurementData.getDataSource().get(0).getDeviceInformation().getName());
        Assert.assertEquals("FLOOR1", measurementData.getDataSource().get(0).getDomainInformation().getName());

        Assert.assertNotNull(connections);
        Assert.assertNotNull(connections.getConnection());
        Assert.assertEquals(2, connections.getConnection().size());
        connections.getConnection().get(0).getComponent();
        Assert.assertEquals("IOT_DEVICE", connections.getConnection().get(0).getComponent().value());
        Assert.assertEquals("127.0.0.1", connections.getConnection().get(0).getHost());
        Assert.assertEquals("5000", connections.getConnection().get(0).getPort());
        Assert.assertEquals("127.0.0.1:5000", connections.getConnection().get(0).getHost() + ":" + connections.getConnection().get(0).getPort());

    }

    @Test(expected = UnmarshalException.class)
    public void testUnmarshalFailure() throws Exception {

        configurationStr = "src/test/resources/blabla.xml";
        configurationFile = new File(configurationStr);

        configurationURI = configurationFile.toURI();
        configurationURI = configurationURI.normalize();

        XMLParser.unmarshal(configurationURI);

    }

    @Test
    public void testMarshal() throws Exception {

        XMLConfiguration unmarshal = XMLParser.unmarshal(configurationURI);

        XMLDeviceInformation deviceInformation = new XMLDeviceInformation();
        deviceInformation.setName("ALARM");

        XMLDomainInformation domainInformation = new XMLDomainInformation();
        domainInformation.setName("SECOND FLOOR");

        XMLDataSource measurementPoint = new XMLDataSource();
        measurementPoint.setDeviceInformation(deviceInformation);
        measurementPoint.setDomainInformation(domainInformation);

        unmarshal.getDataSources().getDataSource().add(measurementPoint);

        configurationStr = "target/configuration_test_output.xml";
        configurationFile = new File(configurationStr);

        XMLParser.marshal(unmarshal, configurationFile);

    }

    @Test
    public void testTransformConfiguration() throws Exception {

        XMLConfiguration configuration = XMLParser.unmarshal(configurationURI);

        List<DataSource> remote = transformerConfig.toRemote(configuration.getDataSources().getDataSource());

        Assert.assertNotNull(remote);
        Assert.assertNotNull(remote.get(0));
        Assert.assertNotNull(remote.get(0).getDevice());

    }
}
