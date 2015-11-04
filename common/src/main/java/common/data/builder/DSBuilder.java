package common.data.builder;

import common.data.DataSource;
import common.data.dto.DataSourcesDTO;
import common.data.model.DeviceInformation;
import common.data.model.DomainInformation;

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

    private DeviceInformation buildDeviceInformation(String name) {
        DeviceInformation devInfo = new DeviceInformation();
        devInfo.setName(name);
        return devInfo;
    }

    private DomainInformation buildDomainInformation(String name) {
        DomainInformation domInfo = new DomainInformation();
        domInfo.setName(name);

        return domInfo;
    }

    public DataSourcesDTO getResult() {
        return dataSources;
    }

}
