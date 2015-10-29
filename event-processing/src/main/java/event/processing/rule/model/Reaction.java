package event.processing.rule.model;

import java.util.Properties;

public class Reaction {

    private String deviceInformation;

    private String domainInformation;

    private Properties configurationModification;

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

    public Properties getConfigurationModification() {
        return configurationModification;
    }

    public void setConfigurationModification(Properties configurationModification) {
        this.configurationModification = configurationModification;
    }

    public void addConfigurationModification(Object key, Object value) {
        if (null == this.configurationModification) {
            this.configurationModification = new Properties();
        }
        this.configurationModification.put(key, value);
    }

    @Override
    public String toString() {
        return "Reaction [deviceInformation=" + deviceInformation + ", domainInformation=" + domainInformation + ", configurationModification=" + configurationModification + "]";
    }

}
