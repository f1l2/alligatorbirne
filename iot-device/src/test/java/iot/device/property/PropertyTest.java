package iot.device.property;

import java.util.Properties;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import iot.device.ApplicationTestContext1;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = ApplicationTestContext1.class)
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
        newProperties.put(SystemReservedProperty.TASK_INTERVAL_MS.name(), 4 * 1000);

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

}
