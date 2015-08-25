package common.data;

public class DataSource {

	private DomainInformation domain;
	
	private DeviceInformation device;
	
	public DataSource() {
		
	}
	
	public DataSource(DomainInformation domain, DeviceInformation deviceInformation) {
		this.setDomain(domain);
		this.setDevice(deviceInformation);
	}

	public DomainInformation getDomain() {
		return domain;
	}

	public void setDomain(DomainInformation domain) {
		this.domain = domain;
	}
	
	public DeviceInformation getDevice() {
		return device;
	}

	public void setDevice(DeviceInformation device) {
		this.device = device;
	}
}
