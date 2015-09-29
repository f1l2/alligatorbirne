package event.processing.rule;

public class Reaction {

    private String deviceInformation;

    private String domainInformation;

    private String configurationModification;

    public String getDeviceInformation() {
        return deviceInformation;
    }

    public void setDeviceInformation(String deviceInformation) {
        this.deviceInformation = deviceInformation;
    }

    public String getDomainInformation() {
        return domainInformation;
    }

    public void setDomainInformation(String domainInformation) {
        this.domainInformation = domainInformation;
    }

    public String getConfigurationModification() {
        return configurationModification;
    }

    public void setConfigurationModification(String configurationModification) {
        this.configurationModification = configurationModification;
    }

}
