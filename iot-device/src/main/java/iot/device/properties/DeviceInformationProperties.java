package iot.device.properties;

import common.data.DeviceInformation;


public class DeviceInformationProperties {
	
	private DeviceInformation deviceInformation;
	
	public DeviceInformationProperties(DeviceInformation deviceInformation)  {
		this.deviceInformation = deviceInformation;
	}

	public DeviceInformation getDeviceInformation() {
		return deviceInformation;
	}

}
