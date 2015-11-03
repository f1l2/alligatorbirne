package common.property;

public enum SensorReservedProperty {
    REQUEST_FOR_DELIVERY("request_for_delivery"),
    //
    STOP_DELIVERY("stop_delivery");

    private String name;

    SensorReservedProperty(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static SensorReservedProperty getSensorReservedPropertyByName(String name) {
        for (SensorReservedProperty property : SensorReservedProperty.values()) {
            if (property.getName().equals(name)) {
                return property;
            }
        }
        return null;
    }

}
