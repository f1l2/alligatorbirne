package common.data.builder;

import common.data.dto.DataSourcesDTO;
import common.data.model.DataSource;
import common.data.model.DeviceInformation;
import common.data.model.DomainInformation;
import common.data.type.DEVICE_INFORMATION_TYPE;
import common.data.type.DOMAIN_INFORMATION_TYPE;

public class DSBuilder {

    private DataSourcesDTO dataSources;

    public DSBuilder() {
        dataSources = new DataSourcesDTO();
    }

    public DSBuilder buildDataSource(String device, String domain) {
        DataSource ds = new DataSource();
        ds.setDeviceInformation(buildDeviceInformation(device));
        ds.setDomainInformation(buildDomainInformation(domain));

        dataSources.add(ds);

        return this;
    }

    public DSBuilder buildDataSource(String device, DEVICE_INFORMATION_TYPE s_type, String domain, DOMAIN_INFORMATION_TYPE d_type) {
        DataSource ds = new DataSource();
        ds.setDeviceInformation(buildDeviceInformation(device, s_type));
        ds.setDomainInformation(buildDomainInformation(domain, d_type));

        dataSources.add(ds);

        return this;
    }

    private DeviceInformation buildDeviceInformation(String name) {
        DeviceInformation devInfo = new DeviceInformation();
        devInfo.setName(name);
        return devInfo;
    }

    private DeviceInformation buildDeviceInformation(String name, DEVICE_INFORMATION_TYPE s_type) {
        DeviceInformation devInfo = new DeviceInformation();
        devInfo.setName(name);
        devInfo.setType(s_type);
        return devInfo;
    }

    private DomainInformation buildDomainInformation(String name) {
        DomainInformation domInfo = new DomainInformation();
        domInfo.setName(name);

        return domInfo;
    }

    private DomainInformation buildDomainInformation(String name, DOMAIN_INFORMATION_TYPE d_type) {
        DomainInformation domInfo = new DomainInformation();
        domInfo.setName(name);

        return domInfo;
    }

    public DataSourcesDTO getResult() {
        return dataSources;
    }

}
