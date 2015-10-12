package iot.device.sensor;

public abstract class Sensor<T> {

    private Sensor<T> instance = null;

    public Sensor<T> getInstance() {
        if (instance == null) {
            instance = newInstance();
        }
        return instance;
    }

    public abstract String getName();

    public abstract T getValue();

    public abstract Sensor<T> newInstance();

}
