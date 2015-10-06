package common.data;

public class ConfigurationDelegation {

    private long eventProcessingId;

    private Connection epURL;

    private DeviceInformation deviceInformation;

    private DomainInformation domainInformation;

    private ConfigurationModification configurationModification;

    public Connection getEpURL() {
        return epURL;
    }

    public void setEpURL(Connection epURL) {
        this.epURL = epURL;
    }

    public long getEventProcessingId() {
        return eventProcessingId;
    }

    public void setEventProcessingId(long eventProcessingId) {
        this.eventProcessingId = eventProcessingId;
    }

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
