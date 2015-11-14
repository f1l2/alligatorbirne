package common.setting;

import java.io.File;
import java.net.URI;
import java.util.List;

import javax.xml.bind.UnmarshalException;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import common.data.DataSource;
import common.data.setting.XMLConnections;
import common.data.setting.XMLDataSource;
import common.data.setting.XMLDataSources;
import common.data.setting.XMLDeviceInformation;
import common.data.setting.XMLDomainInformation;
import common.data.setting.XMLSetting;
import common.transformer.XMLDataSourceTransformer;
import common.transformer.XMLParser;

public class TransformXMLTest {

    private static final String PATH_TO_SETTING_FILE = "src/test/resources/setting.xml";

    private static final String PATH_TO_TEST_OUTPUT = "target/setting_test_output.xml";

    private File settingFile;

    private URI settingURI;

    private XMLDataSourceTransformer transformerConfig = new XMLDataSourceTransformer();

    @Before
    public void before() {
        settingFile = new File(PATH_TO_SETTING_FILE);
        settingURI = settingFile.toURI();
        settingURI = settingURI.normalize();
    }

    @Test
    public void testUnmarshal() throws Exception {

        XMLSetting setting = XMLParser.unmarshal(settingURI);

        XMLDataSources measurementData = setting.getDataSources();
        XMLConnections connections = setting.getConnections();

        Assert.assertNotNull(measurementData.getDataSource());
        Assert.assertEquals(1, measurementData.getDataSource().size());
        Assert.assertNotNull(measurementData.getDataSource().get(0).getDeviceInformation());
        Assert.assertNotNull(measurementData.getDataSource().get(0).getDomainInformation());
        Assert.assertEquals("SENSOR", measurementData.getDataSource().get(0).getDeviceInformation().getName());
        Assert.assertEquals("FLOOR1", measurementData.getDataSource().get(0).getDomainInformation().getName());

        Assert.assertNotNull(connections);
        Assert.assertNotNull(connections.getConnection());
        Assert.assertEquals(3, connections.getConnection().size());
        connections.getConnection().get(0).getComponent();
        Assert.assertEquals("IOT_DEVICE", connections.getConnection().get(0).getComponent().value());
        Assert.assertEquals("127.0.0.1", connections.getConnection().get(0).getHost());
        Assert.assertEquals("5000", connections.getConnection().get(0).getPort());
        Assert.assertEquals("127.0.0.1:5000", connections.getConnection().get(0).getHost() + ":" + connections.getConnection().get(0).getPort());

    }

    @Test(expected = UnmarshalException.class)
    public void testUnmarshalFailure() throws Exception {

        String pathToUnexistingFile = "src/test/resources/blabla.xml";
        settingFile = new File(pathToUnexistingFile);

        settingURI = settingFile.toURI();
        settingURI = settingURI.normalize();

        XMLParser.unmarshal(settingURI);

    }

    @Test
    public void testMarshal() throws Exception {

        XMLSetting unmarshal = XMLParser.unmarshal(settingURI);

        XMLDeviceInformation deviceInformation = new XMLDeviceInformation();
        deviceInformation.setName("ALARM");

        XMLDomainInformation domainInformation = new XMLDomainInformation();
        domainInformation.setName("SECOND FLOOR");

        XMLDataSource measurementPoint = new XMLDataSource();
        measurementPoint.setDeviceInformation(deviceInformation);
        measurementPoint.setDomainInformation(domainInformation);

        unmarshal.getDataSources().getDataSource().add(measurementPoint);

        XMLParser.marshal(unmarshal, new File(PATH_TO_TEST_OUTPUT));

    }

    @Test
    public void testTransformConfiguration() throws Exception {

        XMLSetting setting = XMLParser.unmarshal(settingURI);

        List<DataSource> remote = transformerConfig.toRemote(setting.getDataSources().getDataSource());

        Assert.assertNotNull(remote);
        Assert.assertNotNull(remote.get(0));
        Assert.assertNotNull(remote.get(0).getDeviceInformation());

    }
}
