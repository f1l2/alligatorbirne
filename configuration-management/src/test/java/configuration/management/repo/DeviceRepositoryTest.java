package configuration.management.repo;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import common.data.type.COMPONENT_TYPE;
import configuration.management.AbstractTestCM;
import configuration.management.Application;
import configuration.management.model.DataSourceDLO;
import configuration.management.model.DeviceDLO;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
public class DeviceRepositoryTest extends AbstractTestCM {

    @Test
    @Transactional
    public void findOne() {

        DeviceDLO result = this.deviceRepo.findOne(device1.getId());
        result.getDataSources().add(dataSource1);

        result = this.deviceRepo.save(result);

        assertNotNull(result);
        assertEquals(device1.getId(), result.getId());
        assertEquals(device1.getName(), result.getName());
        assertEquals(device1.getAuthority(), result.getAuthority());

        result = this.deviceRepo.findOne(device2.getId());

        assertNotNull(result.getDataSources());
        assertEquals(false, !CollectionUtils.isEmpty(result.getDataSources()));

    }

    @Test
    public void findByName() {

        DeviceDLO result = this.deviceRepo.findByName(device1.getName());

        assertNotNull(result);
        assertEquals(device1.getId(), result.getId());
        assertEquals(device1.getName(), result.getName());
        assertEquals(device1.getAuthority(), result.getAuthority());
    }

    @Test
    @Transactional
    public void saveDataSource() {

        DeviceDLO result = this.deviceRepo.findByName(device1.getName());
        result.getDataSources().add(dataSource1);

        result = this.deviceRepo.save(result);

        result = this.deviceRepo.findByName(device1.getName());

        assertNotNull(result);
        assertEquals(device1.getId(), result.getId());
        assertEquals(device1.getName(), result.getName());
        assertEquals(device1.getAuthority(), result.getAuthority());

        assertNotNull(result.getDataSources());
        assertEquals(1, result.getDataSources().size());
        assertEquals(dataSource1.getDevice(), new ArrayList<DataSourceDLO>(result.getDataSources()).get(0).getDevice());
        assertEquals(dataSource1.getDomain(), new ArrayList<DataSourceDLO>(result.getDataSources()).get(0).getDomain());
    }

    @Test
    @Transactional
    public void findByUpdatedLessThan1() {

        Iterable<DeviceDLO> all = this.deviceRepo.findAll();

        assertNotNull(all);
        assertEquals(2, ((Collection<?>) all).size());

        LocalDateTime.now().plusHours(1).toLocalDate();

        List<DeviceDLO> devices = this.deviceRepo.findByUpdatedBefore(new Date(Instant.now().plusSeconds(3600 * 24).toEpochMilli()));

        assertNotNull(devices);
        assertEquals(2, devices.size());

        this.deviceRepo.delete(devices);

        all = this.deviceRepo.findAll();

        assertNotNull(all);
        assertEquals(0, ((Collection<?>) all).size());
    }

    @Test
    @Transactional
    public void findByUpdatedLessThan2() {

        Iterable<DeviceDLO> all = this.deviceRepo.findAll();

        assertNotNull(all);
        assertEquals(2, ((Collection<?>) all).size());

        DeviceDLO result = this.deviceRepo.findByName(device1.getName());
        result.getDataSources().add(dataSource1);

        result = this.deviceRepo.save(result);

        assertNotNull(result.getDataSources());
        assertEquals(1, result.getDataSources().size());
        assertEquals(dataSource1.getDevice(), new ArrayList<DataSourceDLO>(result.getDataSources()).get(0).getDevice());
        assertEquals(dataSource1.getDomain(), new ArrayList<DataSourceDLO>(result.getDataSources()).get(0).getDomain());

        List<DeviceDLO> devices = this.deviceRepo.findByUpdatedBefore(new Date(Instant.now().plusSeconds(3600 * 24).toEpochMilli()));

        assertNotNull(devices);
        assertEquals(2, devices.size());

    }

    @Test
    @Transactional
    public void findByDeviceDataSources1() {

        DeviceDLO device = this.deviceRepo.findByName(device1.getName());
        device.getDataSources().add(dataSource1);
        device = this.deviceRepo.save(device);

        List<DeviceDLO> result = this.deviceRepo.findByDataSources(dataSource1.getDevice(), dataSource1.getDomain());

        assertNotNull(result);
        assertEquals(1, result.size());

        result = this.deviceRepo.findByDataSources("abc", dataSource1.getDomain());

        assertNotNull(result);
        assertEquals(0, result.size());

        result = this.deviceRepo.findByDataSources(dataSource1.getDevice(), "abc");

        assertNotNull(result);
        assertEquals(0, result.size());

    }

    @Test
    @Transactional
    public void findByDeviceDataSources2() {

        DeviceDLO device = this.deviceRepo.findByName(device1.getName());
        device.getDataSources().add(dataSource1);
        device = this.deviceRepo.save(device);

        device = this.deviceRepo.findByName(device2.getName());
        device.getDataSources().add(dataSource1);
        device = this.deviceRepo.save(device);

        List<DeviceDLO> result = this.deviceRepo.findByDataSources(dataSource1.getDevice(), dataSource1.getDomain());

        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals(device1.getAuthority(), result.get(0).getAuthority());
        assertEquals(device2.getAuthority(), result.get(1).getAuthority());

        result = this.deviceRepo.findByDataSources("abc", dataSource1.getDomain());

        assertNotNull(result);
        assertEquals(0, result.size());

        result = this.deviceRepo.findByDataSources(dataSource1.getDevice(), "abc");

        assertNotNull(result);
        assertEquals(0, result.size());

    }

    @Test
    @Transactional
    public void findByDeviceDataSources3() {

        DeviceDLO device = this.deviceRepo.findByName(device1.getName());
        device.getDataSources().add(dataSource1);
        device = this.deviceRepo.save(device);

        device = this.deviceRepo.findByName(device2.getName());
        device.getDataSources().add(dataSource1);
        device = this.deviceRepo.save(device);

        List<DeviceDLO> result = this.deviceRepo.findByDataSources(dataSource1.getDevice(), dataSource1.getDomain());

        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals(device1.getAuthority(), result.get(0).getAuthority());
        assertEquals(device2.getAuthority(), result.get(1).getAuthority());

        device = this.deviceRepo.findByName(device2.getName());
        device.setType(COMPONENT_TYPE.EVENT_PROCESSING);
        device = this.deviceRepo.save(device);

        result = this.deviceRepo.findByDataSources(COMPONENT_TYPE.DEVICE, dataSource1.getDevice(), dataSource1.getDomain());

        assertNotNull(result);
        assertEquals(1, result.size());

    }
}
