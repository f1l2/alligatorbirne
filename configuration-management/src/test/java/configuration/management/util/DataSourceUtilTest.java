package configuration.management.util;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import common.data.model.DeviceInformation;
import common.data.model.DomainInformation;
import configuration.management.model.Component;
import configuration.management.model.DataSourceRO;
import configuration.management.model.Device;

public class DataSourceUtilTest {

    private Component component;

    private DeviceInformation device;

    private DomainInformation domain;

    @Before
    public void before() {
        DataSourceRO ds = new DataSourceRO();
        ds.setDevice("device");
        ds.setDomain("domain");

        Device item = new Device();
        item.getDataSources().add(ds);

        ds.setComponent(item);

        component = item;

        device = new DeviceInformation();
        device.setName("device");

        domain = new DomainInformation();
        domain.setName("domain");
    }

    @Test
    public void saveAndUpdate1() {

        assertEquals(1, component.getDataSources().size());

        DataSourceUtil.saveAndUpdate(component, device, domain);

        assertEquals(1, component.getDataSources().size());

    }

    @Test
    public void saveAndUpdate2() {

        assertEquals(1, component.getDataSources().size());

        device.setName("device2");

        DataSourceUtil.saveAndUpdate(component, device, domain);

        assertEquals(2, component.getDataSources().size());

    }

    @Test
    public void saveAndUpdate3() {

        assertEquals(1, component.getDataSources().size());

        domain.setName("domain2");

        DataSourceUtil.saveAndUpdate(component, device, domain);

        assertEquals(2, component.getDataSources().size());

    }

    @Test
    public void saveAndUpdate4() {

        assertEquals(1, component.getDataSources().size());

        device.setName("device2");
        domain.setName("domain2");

        DataSourceUtil.saveAndUpdate(component, device, domain);

        assertEquals(2, component.getDataSources().size());

    }

    @Test
    public void delete1() {

        assertEquals(1, component.getDataSources().size());

        DataSourceUtil.delete(component, device, domain);

        assertEquals(0, component.getDataSources().size());
    }

    @Test
    public void delete2() {

        assertEquals(1, component.getDataSources().size());

        device.setName("device2");

        DataSourceUtil.delete(component, device, domain);

        assertEquals(1, component.getDataSources().size());

    }

    @Test
    public void delete3() {

        assertEquals(1, component.getDataSources().size());

        domain.setName("domain2");

        DataSourceUtil.delete(component, device, domain);

        assertEquals(1, component.getDataSources().size());

    }

    @Test
    public void delete4() {

        assertEquals(1, component.getDataSources().size());

        device.setName("device2");
        domain.setName("domain2");

        DataSourceUtil.delete(component, device, domain);

        assertEquals(1, component.getDataSources().size());

    }

}
