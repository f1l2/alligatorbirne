package common.data.dto;

import java.util.ArrayList;
import java.util.List;

import common.data.model.DeviceInformation;
import common.data.model.DomainInformation;
import common.data.model.SensorData;

/**
 * 
 * @author Manuel
 */

public class DeviceDataDTO {

    private SensorData<?> sensorData;

    private DeviceInformation device;

    private List<DomainInformation> domains;

    public SensorData<?> getSensorData() {
        return sensorData;
    }

    public <T> void setSensorData(T value) {
        SensorData<T> sensorData = new SensorData<T>();
        sensorData.setRawValue(value);

        this.sensorData = sensorData;
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
