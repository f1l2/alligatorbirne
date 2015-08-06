package common.data.type;

public enum DEVICE_INFORMATION_TYPE {

    SENSOR("sensor", "Measured data by sensor");

    private final String value;

    private final String description;

    DEVICE_INFORMATION_TYPE(final String type, final String description) {
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
