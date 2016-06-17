package common.data.model;

public class DeviceData extends DataModel {

    private static final long serialVersionUID = -4534758782540633379L;

    private SensorData<?> sensorData;

    private DeviceInformation device;

    private DomainInformation domain;

    public DeviceData(DomainInformation domain, DeviceInformation device) {
        this.device = device;
        this.domain = domain;
    }

    public DeviceData(DomainInformation domain, DeviceInformation device, SensorData<?> sensorData) {
        this.device = device;
        this.domain = domain;
        this.sensorData = sensorData;
    }

    public DeviceData() {
    }

    public SensorData<?> getSensorData() {
        return sensorData;
    }

    public void setSensorData(SensorData<?> sensorData) {
        this.sensorData = sensorData;
    }

    public DeviceInformation getDevice() {
        return device;
    }

    public void setDevice(DeviceInformation device) {
        this.device = device;
    }

    public DomainInformation getDomain() {
        return domain;
    }

    public void setDomain(DomainInformation domain) {
        this.domain = domain;
    }

    @Override
    public String toString() {
        return "DeviceData [sensorData=" + sensorData + ", device=" + device + ", domain=" + domain + "]";
    }

}
