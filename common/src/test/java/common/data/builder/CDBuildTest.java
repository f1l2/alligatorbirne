package common.data.builder;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;

import org.junit.Test;

import common.data.ConfigurationDelegation;
import common.data.DataSource;

public class CDBuildTest {

    @Test
    public void builder() {
        CDBuilder cDBuilder = new CDBuilder();
        cDBuilder.addDataSource("device", "domain");

        ConfigurationDelegation result = cDBuilder.getResult();

        assertNotNull(result);
        assertEquals("device", new ArrayList<DataSource>(result.getDataSources()).get(0).getDeviceInformation().getName());
        assertEquals("domain", new ArrayList<DataSource>(result.getDataSources()).get(0).getDomainInformation().getName());

    }
}
