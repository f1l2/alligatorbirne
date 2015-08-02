package iot.device.properties;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class IoTDeviceProperties {

	private ConnectionProperties connectionPropertiesIoTDevice;
	
	private ConnectionProperties connectionPropertiesConfigurationManagement;

	private List<ConnectionProperties> connectionPropertiesEventProcessing;
	
	private DomainProperties domainProperties;
	
	private DeviceInformationProperties deviceInformationProperties;
	
	public ConnectionProperties getConnectionPropertiesIoTDevice() {
		return connectionPropertiesIoTDevice;
	}

	public void setConnectionPropertiesIoTDevice(
			ConnectionProperties connectionPropertiesIoTDevice) {
		this.connectionPropertiesIoTDevice = connectionPropertiesIoTDevice;
	}

	public ConnectionProperties getConnectionPropertiesConfigurationManagement() {
		return connectionPropertiesConfigurationManagement;
	}

	public void setConnectionPropertiesConfigurationManagement(
			ConnectionProperties connectionPropertiesConfigurationManagement) {
		this.connectionPropertiesConfigurationManagement = connectionPropertiesConfigurationManagement;
	}

	public List<ConnectionProperties> getConnectionPropertiesEventProcessing() {
		return connectionPropertiesEventProcessing;
	}

	public void addConnectionPropertiesEventProcessing(
			ConnectionProperties connectionPropertiesEventProcessing) {
		
		if (null == connectionPropertiesEventProcessing) {
			this.connectionPropertiesEventProcessing  = new ArrayList<ConnectionProperties>();
		}
		
		this.connectionPropertiesEventProcessing.add(connectionPropertiesEventProcessing);
	}

	public DeviceInformationProperties getDeviceInformationProperties() {
		return deviceInformationProperties;
	}

	public void setDeviceInformationProperties(
			DeviceInformationProperties deviceInformationProperties) {
		this.deviceInformationProperties = deviceInformationProperties;
	}

	public DomainProperties getDomainProperties() {
		return domainProperties;
	}

	public void setDomainProperties(DomainProperties domainProperties) {
		this.domainProperties = domainProperties;
	}
}
