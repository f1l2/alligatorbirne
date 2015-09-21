package iot.device.status;

public enum STATUS_TYPE {

    BOOTING("booting", "Device is booting."),

    STARTED_UP("s_up", "Device started up."),

    REGISTER_DEVICE("reg_de", "Device registered at CM"),

    REGISTER_DATA_SOURCES("reg_ds", "Data sources registered at CM"),

    WORKING("wor", "Device is working"),

    ERROR("err", "Device is in error state");

    private final String value;

    private final String description;

    STATUS_TYPE(final String type, final String description) {
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
