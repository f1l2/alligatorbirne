package iot.device.property;

import java.util.Properties;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import common.property.SensorReservedProperty;
import common.property.SystemReservedProperty;
import iot.device.ApplicationTestContext;
import iot.device.sensor.SensorUtils;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = ApplicationTestContext.class)
public class PropertyTest {

    @Autowired
    private Configuration configuration;

    @Test
    public void mergeProperties() {

        Assert.assertEquals(10 * 1000, configuration.getProperties().get(SystemReservedProperty.TASK_INTERVAL_MS.name()));

        Properties newProperties = new Properties();
        newProperties.put(SystemReservedProperty.TASK_INTERVAL_MS.name(), 5 * 1000);

        configuration.setAndUpdateProperties(newProperties);

        Assert.assertEquals(5 * 1000, configuration.getProperties().get(SystemReservedProperty.TASK_INTERVAL_MS.name()));

        /**
         * Below defined minimum
         */

        newProperties = new Properties();
        newProperties.put(SystemReservedProperty.TASK_INTERVAL_MS.name(), 4 * 100);

        configuration.setAndUpdateProperties(newProperties);

        Assert.assertEquals(5 * 1000, configuration.getProperties().get(SystemReservedProperty.TASK_INTERVAL_MS.name()));

        /**
         * Over defined maximum
         */
        newProperties = new Properties();
        newProperties.put(SystemReservedProperty.TASK_INTERVAL_MS.name(), 5001 * 1000);

        configuration.setAndUpdateProperties(newProperties);

        Assert.assertEquals(5 * 1000, configuration.getProperties().get(SystemReservedProperty.TASK_INTERVAL_MS.name()));

    }

    @Test
    public void getSensorReservedProperties() {

        Properties newProperties = new Properties();
        newProperties.put(SensorReservedProperty.SUPPLY_REQ.getName(), "TEMPERATURE");

        configuration.setAndUpdateProperties(newProperties);

        Assert.assertEquals(1, configuration.getSupplyingSensor().size());

    }

    @Test
    public void convertToClassName() {

        Assert.assertEquals("Sensor", SensorUtils.convertToClassName("SENSOR"));
        Assert.assertEquals("Sensor", SensorUtils.convertToClassName("seNSOR"));
        Assert.assertEquals("S", SensorUtils.convertToClassName("s"));

    }

}
