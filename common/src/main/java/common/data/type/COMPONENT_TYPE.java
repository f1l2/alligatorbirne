package common.data.type;

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

    public String getValue() {
        return value;
    }

    public String getDescription() {
        return description;
    }
}
