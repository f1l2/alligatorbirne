package iot.device.sensor;

import java.util.HashMap;
import java.util.Map;

public class SensorFactory {

    private static SensorFactory instance;

    private Map<String, Sensor<?>> sensors = new HashMap<String, Sensor<?>>();

    public Map<String, Sensor<?>> getSensors() {
        return sensors;
    }

    public void setSensors(Map<String, Sensor<?>> sensors) {
        this.sensors = sensors;
    }

    public static SensorFactory getInstance() {
        if (null == instance) {
            SensorFactory sf = new SensorFactory();
            instance = sf;
        }
        return instance;
    }

}
