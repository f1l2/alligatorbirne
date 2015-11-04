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

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = ApplicationTestContext.class)
public class PropertyTest {

    @Autowired
    private Configuration configuration;

    @Test
    public void mergeProperties() {

        // Assert.assertEquals(5 * 1000, configuration.getProperties().get(SystemReservedProperty.TASK_INTERVAL_MS.getName()));

        Properties newProperties = new Properties();
        newProperties.put(SystemReservedProperty.TASK_INTERVAL_MS.getName(), 5 * 1000);

        configuration.setAndUpdateProperties(newProperties);

        Assert.assertEquals(5 * 1000, configuration.getProperties().get(SystemReservedProperty.TASK_INTERVAL_MS.getName()));

        /**
         * Below defined minimum
         */

        newProperties = new Properties();
        newProperties.put(SystemReservedProperty.TASK_INTERVAL_MS.getName(), 4 * 100);

        configuration.setAndUpdateProperties(newProperties);

        Assert.assertEquals(5 * 1000, configuration.getProperties().get(SystemReservedProperty.TASK_INTERVAL_MS.getName()));

        /**
         * Above defined maximum
         */
        newProperties = new Properties();
        newProperties.put(SystemReservedProperty.TASK_INTERVAL_MS.getName(), 5001 * 1000);

        configuration.setAndUpdateProperties(newProperties);

        Assert.assertEquals(5 * 1000, configuration.getProperties().get(SystemReservedProperty.TASK_INTERVAL_MS.getName()));

    }

    @Test
    public void getSensorReservedProperties() {

        Properties newProperties = new Properties();
        newProperties.put(SensorReservedProperty.REQUEST_FOR_DELIVERY.getName(), "TEMPERATURE");

        configuration.setAndUpdateProperties(newProperties);

        Assert.assertEquals(1, configuration.getSupplyingSensor().size());

    }
}
