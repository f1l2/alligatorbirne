package common.data.model;

import java.io.Serializable;

public class SensorData<T> implements Serializable {

    private static final long serialVersionUID = -7909438674414081684L;

    private T rawValue;

    private Integer value;

    public T getRawValue() {
        return rawValue;
    }

    public void setRawValue(T value) {
        this.rawValue = value;
    }

    public Integer getValue() {
        try {
            value = (Integer) rawValue;
        } catch (Exception e) {
            value = -1;
        }
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "SensorData [rawValue=" + rawValue + ", value=" + value + "]";
    }
}
