package common.data.builder;

import java.util.Properties;
import java.util.Set;

import common.data.ConfigurationDelegation;
import common.data.Connection;
import common.data.DataSource;
import common.data.model.DeviceInformation;
import common.data.model.DomainInformation;
import common.data.type.COMPONENT_TYPE;
import common.rest.UrlUtils;

public class CDBuilder {

    private ConfigurationDelegation cd;

    public CDBuilder() {
        cd = new ConfigurationDelegation();
    }

    public CDBuilder addDataSource(String device, String domain) {
        DeviceInformation devInfo = new DeviceInformation();
        devInfo.setName(device);

        DomainInformation domainInfo = new DomainInformation();
        domainInfo.setName(domain);

        cd.getDataSources().add(new DataSource(domainInfo, devInfo));

        return this;
    }

    public CDBuilder buildDataSink(String authority, COMPONENT_TYPE ct) {

        Connection connection = new Connection();
        connection.setUrl(UrlUtils.parseUrl(authority));
        connection.setComponentType(ct);

        cd.setDataSink(connection);

        return this;

    }

    public CDBuilder buildDataSink(Connection connection) {

        cd.setDataSink(connection);

        return this;

    }

    public CDBuilder buildDataSink(String authority, long id) {

        Connection connection = new Connection();
        connection.setUrl(UrlUtils.parseUrl(authority));
        connection.setId(id);

        cd.setDataSink(connection);

        return this;

    }

    public CDBuilder buildDataSources(Set<DataSource> dataSources) {
        cd.setDataSources(dataSources);

        return this;
    }

    public CDBuilder buildProperties(Properties properties) {
        cd.setProperties(properties);

        return this;
    }

    public ConfigurationDelegation getResult() {
        return cd;
    }

}
