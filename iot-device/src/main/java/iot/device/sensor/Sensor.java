package iot.device.sensor;

import org.springframework.stereotype.Component;

@Component("sensor")
public abstract class Sensor<T> {

    public abstract T getRawValue();

    public abstract Integer getValue();

    @Override
    public String toString() {
        return this.getClass().getSimpleName();
    }
}
