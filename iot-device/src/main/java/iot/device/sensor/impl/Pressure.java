package iot.device.sensor.impl;

import org.springframework.stereotype.Component;

import iot.device.sensor.Sensor;

@Component
public class Pressure extends Sensor<Integer> {

    @Override
    public String getValue() {

        return "5";
    }

    @Override
    public Integer getRawValue() {

        return 5;
    }

}
