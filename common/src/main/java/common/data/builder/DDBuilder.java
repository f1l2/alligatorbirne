package common.data.builder;

import java.util.List;

import common.data.dto.DeviceDataDTO;
import common.data.model.DeviceData;
import common.data.model.DeviceInformation;
import common.data.model.DomainInformation;
import common.data.model.SensorData;
import common.transformer.DeviceDataTransformer;

public class DDBuilder {

    private DeviceData deviceData;

    private DeviceDataTransformer ddt;

    public DDBuilder() {
        this.deviceData = new DeviceData();
        this.ddt = new DeviceDataTransformer();
    }

    public DDBuilder buildDeviceInformation(String name) {

        DeviceInformation devInfo = new DeviceInformation();
        devInfo.setName(name);

        deviceData.setDevice(devInfo);

        return this;
    }

    public DDBuilder buildDomainInformation(DomainInformation domain) {

        deviceData.setDomain(domain);
        return this;
    }

    public <T> DDBuilder buildSensorData(T value) {

        SensorData<T> sensorData = new SensorData<T>();
        sensorData.setRawValue(value);

        deviceData.setSensorData(sensorData);

        return this;
    }

    public DeviceData getResult() {
        return deviceData;
    }

    public DeviceDataDTO getResultDTO(List<DomainInformation> domains) {

        DeviceDataDTO deviceDataDTO = ddt.toRemote(deviceData);
        deviceDataDTO.setDomains(domains);

        return deviceDataDTO;
    }
}
