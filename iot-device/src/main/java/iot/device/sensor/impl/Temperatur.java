package iot.device.sensor.impl;

import org.springframework.stereotype.Component;

import iot.device.sensor.Sensor;

@Component
public class Temperatur extends Sensor<Integer> {

    @Override
    public Integer getRawValue() {

        return 5;
    }

    @Override
    public String getValue() {

        return "5";
    }
}
