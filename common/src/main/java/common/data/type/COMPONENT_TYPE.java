package common.data.type;

import common.data.configuration.XMLComponent;

public enum COMPONENT_TYPE {

    /*
     * Component belongs to one IOT instance
     */
    IOT_DEVICE("iot device", "Device"),
    /*
     * Component belongs to one CM instance
     */
    CONFIGURATION_MANAGEMENT("configuration management", ""),
    /*
     * Component belongs to one EP instance
     */
    EVENT_PROCESSING("event processing", ""),
    /*
     * Component belongs to hosting component. Special marked for various reason (faster access ...)
     */
    LOCAL("local", "stores own connection data.");

    private final String value;

    private final String description;

    COMPONENT_TYPE(final String type, final String description) {
        this.value = type;
        this.description = description;
    }

    public boolean equals(XMLComponent component) {
        if (component.name().equals(this.name()))
            return true;
        return false;
    }

    public String getValue() {
        return value;
    }

    public String getDescription() {
        return description;
    }
}
