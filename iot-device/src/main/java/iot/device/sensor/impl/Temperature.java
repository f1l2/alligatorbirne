package iot.device.sensor.impl;

import org.springframework.stereotype.Component;

import iot.device.sensor.Sensor;

@Component
public class Temperature extends Sensor<Integer> {

    private static int value = 20;

    private static Boolean cntUp = true;

    private static int upperBound = 30;

    private static int lowerBound = 10;

    @Override
    public Integer getRawValue() {

        if (cntUp) {

            value++;

            if (value >= upperBound) {
                cntUp = false;
            }

        } else {
            value--;

            if (value <= lowerBound) {
                cntUp = true;
            }

        }

        return value;
    }

    @Override
    public Integer getValue() {

        return getRawValue();
    }
}
