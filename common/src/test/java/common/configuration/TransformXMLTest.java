package common.configuration;

import java.io.File;
import java.net.URI;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import common.data.MeasurementPoint;
import common.data.configuration.Configuration;
import common.data.configuration.Connections;
import common.data.configuration.DeviceInformationConfig;
import common.data.configuration.DomainConfig;
import common.data.configuration.MeasurementDataConfig;
import common.data.configuration.MeasurementPointConfig;
import common.data.xml.XMLParser;
import common.transformer.ConfigTransformer;

public class TransformXMLTest {
	
	private String configurationStr;
	private File configurationFile;
	private URI configurationURI;
	
	private ConfigTransformer transformerConfig = new ConfigTransformer();
	
	@Before
	public void setup() {
		configurationStr = "src/test/resources/configuration.xml";
		configurationFile = new File(configurationStr);
		
		configurationURI = configurationFile.toURI();
        configurationURI = configurationURI.normalize();
	}


	@Test
    public void testUnmarshal() throws Exception {
		
		Configuration configuration = XMLParser.unmarshal(configurationURI);
		
		MeasurementDataConfig measurementData = configuration.getMeasurementDataConfig();
		Connections connections = configuration.getConnections();
		
		Assert.assertNotNull(measurementData.getMeasurementPointConfig());
		Assert.assertEquals(1, measurementData.getMeasurementPointConfig().size());
		Assert.assertNotNull(measurementData.getMeasurementPointConfig().get(0).getDeviceInformationConfig());
		Assert.assertNotNull(measurementData.getMeasurementPointConfig().get(0).getDomainConfig());
		Assert.assertEquals("SENSOR", measurementData.getMeasurementPointConfig().get(0).getDeviceInformationConfig().getName());
		Assert.assertEquals("FLOOR1", measurementData.getMeasurementPointConfig().get(0).getDomainConfig().getName());
		
		Assert.assertNotNull(connections);
		Assert.assertNotNull(connections.getConnectionConfig());
		Assert.assertEquals(1, connections.getConnectionConfig().size());
		Assert.assertEquals("IoTDevice", connections.getConnectionConfig().get(0).getComponent());
		Assert.assertEquals("127.0.0.1", connections.getConnectionConfig().get(0).getIp());
		Assert.assertEquals("5000", connections.getConnectionConfig().get(0).getPort());
		Assert.assertEquals("127.0.0.1:5000", connections.getConnectionConfig().get(0).getUrl());

    }
	
	@Test
    public void testMarshal() throws Exception {

		Configuration unmarshal = XMLParser.unmarshal(configurationURI);
		
		DeviceInformationConfig deviceInformation = new DeviceInformationConfig();
		deviceInformation.setName("ALARM");
		
		DomainConfig domain = new DomainConfig();
		domain.setName("SECOND FLOOR");
		
		MeasurementPointConfig measurementPoint = new MeasurementPointConfig();
		measurementPoint.setDeviceInformationConfig(deviceInformation);
		measurementPoint.setDomainConfig(domain);
		
		unmarshal.getMeasurementDataConfig().getMeasurementPointConfig().add(measurementPoint);
	
		configurationStr = "target/configuration_test_output.xml";
		configurationFile = new File(configurationStr);
				
		XMLParser.marshal(unmarshal, configurationFile);
		
    }
	
	@Test
    public void testTransformConfiguration() throws Exception {
		
		Configuration configuration = XMLParser.unmarshal(configurationURI);
		
		List<MeasurementPoint> remote = transformerConfig.toRemote(configuration.getMeasurementDataConfig().getMeasurementPointConfig());
		
		Assert.assertNotNull(remote);
		Assert.assertNotNull(remote.get(0));
		Assert.assertNotNull(remote.get(0).getDeviceInformation());
		
	}
}
