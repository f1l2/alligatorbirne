package common.data.type;

import common.data.setting.XMLComponent;

public enum COMPONENT_TYPE {

    /**
     * Component belongs to exactly one IOT instance
     */
    DEVICE("device", "Device"),

    /**
     * Component belongs to exactly one CM instance
     */
    CONFIGURATION_MANAGEMENT("configuration management", ""),

    /**
     * Component belongs to exactly one EP instance
     */
    EVENT_PROCESSING("event processing", ""),

    /**
     * Component belongs to exactly one MB instance
     */
    MESSAGE_BROKER("message_broker", ""),

    /**
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
