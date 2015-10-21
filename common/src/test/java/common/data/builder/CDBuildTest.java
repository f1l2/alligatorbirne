package common.data.builder;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.Properties;

import org.junit.Test;

import common.data.ConfigurationDelegation;
import common.data.Connection;

public class CDBuildTest {

    @Test
    public void builder() {
        CDBuilder cDBuilder = new CDBuilder();
        cDBuilder.buildDeviceInformation("device")
                //
                .buildDomainInformation("domain")
                //
                .buildConfigurationModification(new Connection(), new Properties());

        ConfigurationDelegation result = cDBuilder.getResult();

        assertNotNull(result);
        assertEquals("device", result.getDeviceInformation().getName());
        assertEquals("domain", result.getDomainInformation().getName());

    }
}
