package common.data.builder;

import java.util.ArrayList;
import java.util.List;

import common.data.model.DeviceData;
import common.data.model.DeviceInformation;
import common.data.model.DomainInformation;
import common.data.model.SensorData;

public class DDBuilder {

    private DeviceData deviceData;

    public DDBuilder() {
        this.deviceData = new DeviceData();
    }

    public DDBuilder buildDeviceInformation(String name) {

        DeviceInformation devInfo = new DeviceInformation();
        devInfo.setName(name);

        deviceData.setDevice(devInfo);

        return this;
    }

    public DDBuilder buildDomainInformation(DomainInformation domain) {

        if (null == deviceData.getDomains()) {
            deviceData.setDomains(new ArrayList<DomainInformation>());
        }
        deviceData.getDomains().add(domain);
        return this;
    }

    public DDBuilder buildDomainInformations(List<DomainInformation> domains) {

        deviceData.setDomains(domains);
        return this;
    }

    public <T> DDBuilder buildSensorData(T value) {

        SensorData<T> sensorData = new SensorData<T>();
        sensorData.setValue(value);

        deviceData.setSensorData(sensorData);

        return this;
    }

    public DeviceData getResult() {
        return deviceData;
    }
}
