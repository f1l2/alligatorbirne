package common.data;

public class MeasurementPoint {

	private Domain domain;
	
	private DeviceInformation deviceInformation;
	
	public MeasurementPoint() {
		
	}
	
	public MeasurementPoint(Domain domain, DeviceInformation deviceInformation) {
		this.setDomain(domain);
		this.setDeviceInformation(deviceInformation);
	}

	public Domain getDomain() {
		return domain;
	}

	public void setDomain(Domain domain) {
		this.domain = domain;
	}
	
	public DeviceInformation getDeviceInformation() {
		return deviceInformation;
	}

	public void setDeviceInformation(DeviceInformation deviceInformation) {
		this.deviceInformation = deviceInformation;
	}
}
