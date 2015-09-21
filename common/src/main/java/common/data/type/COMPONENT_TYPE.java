package common.data.type;

import common.data.configuration.XMLComponent;

public enum COMPONENT_TYPE {

    IOT_DEVICE("iot device", "Device"),
    //
    CONFIGURATION_MANAGEMENT("configuration management", ""),
    //
    EVENT_PROCESSING("event processing", "");

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
