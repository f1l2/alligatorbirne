package common.data.builder;

import java.util.Properties;

import common.data.ConfigurationDelegation;
import common.data.ConfigurationModification;
import common.data.Connection;
import common.data.model.DeviceInformation;
import common.data.model.DomainInformation;
import common.property.SensorReservedProperty;

public class CDBuilder {

    private ConfigurationDelegation cd;

    public CDBuilder() {
        cd = new ConfigurationDelegation();
    }

    public CDBuilder buildDeviceInformation(String name) {
        DeviceInformation devInfo = new DeviceInformation();
        devInfo.setName(name);

        cd.setDeviceInformation(devInfo);

        return this;
    }

    public CDBuilder buildDomainInformation(String name) {
        DomainInformation domInfo = new DomainInformation();
        domInfo.setName(name);

        cd.setDomainInformation(domInfo);

        return this;
    }

    public CDBuilder buildConfigurationModification(Connection dataSink, Properties properties) {

        ConfigurationModification cm = new ConfigurationModification();
        cm.setDataSink(dataSink);
        cm.setProperties(properties);

        cd.setConfigurationModification(cm);

        return this;

    }

    public CDBuilder buildConfigurationModificationStartUp(Connection dataSink, String name) {

        /**
         * Startup
         */
        Properties properties = new Properties();
        properties.setProperty(SensorReservedProperty.REQUEST_FOR_DELIVERY.getName(), name);

        ConfigurationModification cm = new ConfigurationModification();
        cm.setDataSink(dataSink);
        cm.setProperties(properties);

        cd.setConfigurationModification(cm);

        return this;

    }

    public CDBuilder buildConfigurationModificationShutdown(Connection dataSink, String name) {

        /**
         * Startup
         */
        Properties properties = new Properties();
        properties.setProperty(SensorReservedProperty.STOP_DELIVERY.getName(), name);

        ConfigurationModification cm = new ConfigurationModification();
        cm.setDataSink(dataSink);
        cm.setProperties(properties);

        cd.setConfigurationModification(cm);

        return this;

    }

    public ConfigurationDelegation getResult() {
        return cd;
    }

}
