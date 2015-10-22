package common.data.model;

import java.util.ArrayList;
import java.util.List;

public class DeviceData extends DataModel {

    private SensorData<?> sensorData;

    private DeviceInformation device;

    private List<DomainInformation> domains;

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

    public List<DomainInformation> getDomains() {
        return domains;
    }

    public void setDomains(List<DomainInformation> domains) {
        this.domains = domains;
    }

    public void addDomain(DomainInformation domain) {
        if (null == this.domains) {
            this.domains = new ArrayList<DomainInformation>();
        }
        this.domains.add(domain);
    }
}
