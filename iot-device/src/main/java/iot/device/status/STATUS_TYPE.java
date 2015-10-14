package iot.device.status;

public enum STATUS_TYPE {

    TEST("testing", "Device is under test.", 1),

    BOOTING("booting", "Device is booting.", 1),

    STARTED_UP("s_up", "Device started up.", 1),

    REGISTER_DEVICE("reg_de", "Device registered at CM", 10),

    REGISTER_DATA_SOURCES("reg_ds", "Data sources registered at CM", 10),

    WORKING("wor", "Device is working", 30),

    ERROR("err", "Device is in error state", 30);

    private final String value;

    private final String description;

    private final int delay;

    STATUS_TYPE(final String type, final String description, final int delay) {
        this.value = type;
        this.description = description;
        this.delay = delay;
    }

    public String getValue() {
        return value;
    }

    public String getDescription() {
        return description;
    }

    public int getDelay() {
        return delay;
    }
}
