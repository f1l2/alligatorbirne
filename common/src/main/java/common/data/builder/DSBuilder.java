package common.data.builder;

import common.data.DataSource;
import common.data.model.DeviceInformation;
import common.data.model.DomainInformation;

public class DSBuilder {

    private DataSource dataSource;

    public DSBuilder buildDeviceInformation(String name, Integer value) {
        DeviceInformation devInfo = new DeviceInformation();
        devInfo.setName(name);

        dataSource.setDeviceInformation(devInfo);
        return this;
    }

    public DSBuilder buildDomainInformation(String name) {
        DomainInformation domInfo = new DomainInformation();
        domInfo.setName(name);

        dataSource.setDomainInformation(domInfo);
        return this;
    }

    public DataSource getResult() {
        return dataSource;
    }

}
