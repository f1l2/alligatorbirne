package common.data;

public class DataSource {

    private DomainInformation domainInformation;

    private DeviceInformation deviceInformation;

    public DataSource() {
        // default costructor
    }

    public DataSource(DomainInformation domain, DeviceInformation deviceInformation) {
        this.setDomainInformation(domain);
        this.setDeviceInformation(deviceInformation);
    }

    public DomainInformation getDomainInformation() {
        return domainInformation;
    }

    public void setDomainInformation(DomainInformation domainInformation) {
        this.domainInformation = domainInformation;
    }

    public DeviceInformation getDeviceInformation() {
        return deviceInformation;
    }

    public void setDeviceInformation(DeviceInformation deviceInformation) {
        this.deviceInformation = deviceInformation;
    }
}
