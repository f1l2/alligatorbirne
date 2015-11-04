package common.property;

import java.util.HashMap;
import java.util.Map;

public enum SystemReservedProperty {
    TASK_INTERVAL_MS("task_interval_ms", true, 2 * 1000, 5 * 100, 5000 * 1000);

    private String name;

    private Boolean isMutable;

    private int defaultValue;

    private int min;

    private int max;

    SystemReservedProperty(String name, Boolean isMutable, int defaultValue, int min, int max) {
        this.name = name;
        this.isMutable = isMutable;
        this.defaultValue = defaultValue;
        this.max = max;
        this.min = min;
    }

    public static SystemReservedProperty getSystemReservedPropertyByName(String name) {

        for (SystemReservedProperty property : SystemReservedProperty.values()) {
            if (property.getName().equals(name)) {
                return property;
            }
        }
        return null;
    }

    public static Map<String, Integer> getSystemReservedProperty() {

        Map<String, Integer> srps = new HashMap<String, Integer>();

        for (SystemReservedProperty srp : SystemReservedProperty.values()) {
            srps.put(srp.getName(), srp.getDefaultValue());
        }
        return srps;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getIsMutable() {
        return isMutable;
    }

    public void setIsMutable(Boolean isMutable) {
        this.isMutable = isMutable;
    }

    public int getDefaultValue() {
        return defaultValue;
    }

    public void setDefaultValue(int defaultValue) {
        this.defaultValue = defaultValue;
    }

    public int getMin() {
        return min;
    }

    public void setMin(int min) {
        this.min = min;
    }

    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        this.max = max;
    }

}
