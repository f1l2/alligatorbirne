package iot.device.sensor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import iot.device.property.Configuration;

@Component("sensor")
public abstract class Sensor<T> {

    @Autowired
    private Configuration property;

    public abstract T getValue();

    public Configuration getProperty() {
        return property;
    }

    public void setProperty(Configuration property) {
        this.property = property;
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName();
    }
}
