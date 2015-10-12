package iot.device.sensor.impl;

import iot.device.sensor.Sensor;

public class PressureSensor extends Sensor<Integer> {

    private static final String NAME = "pressure";

    @Override
    public String getName() {
        return NAME;
    }

    @Override
    public Integer getValue() {

        return null;
    }

    @Override
    public Sensor<Integer> newInstance() {
        return new PressureSensor();
    }

}
