package common.data;

public class MeasurementPoint {

	private DomainInformation domain;
	
	private DeviceInformation deviceInformation;
	
	public MeasurementPoint() {
		
	}
	
	public MeasurementPoint(DomainInformation domain, DeviceInformation deviceInformation) {
		this.setDomain(domain);
		this.setDeviceInformation(deviceInformation);
	}

	public DomainInformation getDomain() {
		return domain;
	}

	public void setDomain(DomainInformation domain) {
		this.domain = domain;
	}
	
	public DeviceInformation getDeviceInformation() {
		return deviceInformation;
	}

	public void setDeviceInformation(DeviceInformation deviceInformation) {
		this.deviceInformation = deviceInformation;
	}
}
