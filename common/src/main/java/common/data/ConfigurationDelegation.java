package common.data;

import common.data.model.DeviceInformation;
import common.data.model.DomainInformation;

public class ConfigurationDelegation {

    private DeviceInformation deviceInformation;

    private DomainInformation domainInformation;

    private ConfigurationModification configurationModification;

    public DeviceInformation getDeviceInformation() {
        return deviceInformation;
    }

    public void setDeviceInformation(DeviceInformation deviceInformation) {
        this.deviceInformation = deviceInformation;
    }

    public DomainInformation getDomainInformation() {
        return domainInformation;
    }

    public void setDomainInformation(DomainInformation domainInformation) {
        this.domainInformation = domainInformation;
    }

    public ConfigurationModification getConfigurationModification() {
        return configurationModification;
    }

    public void setConfigurationModification(ConfigurationModification configurationModification) {
        this.configurationModification = configurationModification;
    }

}
