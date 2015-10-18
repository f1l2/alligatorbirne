package event.processing.status;

public enum STATUS_TYPE {

    BOOTING("booting", "EP is booting.", 1),

    STARTED_UP("s_up", "EP started up.", 1),

    REGISTER_EP("reg_de", "EP registered at CM", 10),

    WORKING("wor", "EP is working", 300),

    ERROR("err", "EP is in error state", 30),

    TEST("test", "EP is in test state", 100);

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
