package iot.device.sensor.impl;

import org.springframework.stereotype.Component;

import iot.device.sensor.Sensor;

@Component
public class Pressure extends Sensor<Integer> {

    @Override
    public Integer getValue() {

        return null;
    }

}
