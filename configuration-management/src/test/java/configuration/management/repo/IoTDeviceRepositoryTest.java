package configuration.management.repo;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import configuration.management.AbstractTestCM;
import configuration.management.Application;
import configuration.management.model.IoTDeviceRO;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
public class IoTDeviceRepositoryTest extends AbstractTestCM {

    @Test
    public void findByName() {

        IoTDeviceRO result = this.deviceRepo.findByName(device1.getName());

        assertNotNull(result);
        assertEquals(device1.getId(), result.getId());
        assertEquals(device1.getName(), result.getName());
        assertEquals(device1.getAuthority(), result.getAuthority());
    }

    @Test
    @Transactional
    public void saveDataSource() {

        IoTDeviceRO result = this.deviceRepo.findByName(device1.getName());
        result.getIoTDeviceDataSources().add(dataSource1);

        result = this.deviceRepo.save(result);

        result = this.deviceRepo.findByName(device1.getName());

        assertNotNull(result);
        assertEquals(device1.getId(), result.getId());
        assertEquals(device1.getName(), result.getName());
        assertEquals(device1.getAuthority(), result.getAuthority());

        assertNotNull(result.getIoTDeviceDataSources());
        assertEquals(1, result.getIoTDeviceDataSources().size());
        assertEquals(dataSource1.getDevice(), result.getIoTDeviceDataSources().get(0).getDevice());
        assertEquals(dataSource1.getDomain(), result.getIoTDeviceDataSources().get(0).getDomain());
    }

    @Test
    @Transactional
    public void findByUpdatedLessThan1() {

        Iterable<IoTDeviceRO> all = this.deviceRepo.findAll();

        assertNotNull(all);
        assertEquals(2, ((Collection<?>) all).size());

        List<IoTDeviceRO> devices = this.deviceRepo.findByUpdatedBefore(new Date());

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

        Iterable<IoTDeviceRO> all = this.deviceRepo.findAll();

        assertNotNull(all);
        assertEquals(2, ((Collection<?>) all).size());

        IoTDeviceRO result = this.deviceRepo.findByName(device1.getName());
        result.getIoTDeviceDataSources().add(dataSource1);

        result = this.deviceRepo.save(result);

        assertNotNull(result.getIoTDeviceDataSources());
        assertEquals(1, result.getIoTDeviceDataSources().size());
        assertEquals(dataSource1.getDevice(), result.getIoTDeviceDataSources().get(0).getDevice());
        assertEquals(dataSource1.getDomain(), result.getIoTDeviceDataSources().get(0).getDomain());

        List<IoTDeviceRO> devices = this.deviceRepo.findByUpdatedBefore(new Date());

        assertNotNull(devices);
        assertEquals(2, devices.size());

    }

    @Test
    @Transactional
    public void findByIoTDeviceDataSources1() {

        IoTDeviceRO device = this.deviceRepo.findByName(device1.getName());
        device.getIoTDeviceDataSources().add(dataSource1);
        device = this.deviceRepo.save(device);

        List<IoTDeviceRO> result = this.deviceRepo.findByIoTDeviceDataSources(dataSource1.getDevice(), dataSource1.getDomain());

        assertNotNull(result);
        assertEquals(1, result.size());

        result = this.deviceRepo.findByIoTDeviceDataSources("abc", dataSource1.getDomain());

        assertNotNull(result);
        assertEquals(0, result.size());

        result = this.deviceRepo.findByIoTDeviceDataSources(dataSource1.getDevice(), "abc");

        assertNotNull(result);
        assertEquals(0, result.size());

    }

    @Test
    @Transactional
    public void findByIoTDeviceDataSources2() {

        IoTDeviceRO device = this.deviceRepo.findByName(device1.getName());
        device.getIoTDeviceDataSources().add(dataSource1);
        device = this.deviceRepo.save(device);

        device = this.deviceRepo.findByName(device2.getName());
        device.getIoTDeviceDataSources().add(dataSource1);
        device = this.deviceRepo.save(device);

        List<IoTDeviceRO> result = this.deviceRepo.findByIoTDeviceDataSources(dataSource1.getDevice(), dataSource1.getDomain());

        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals(device1.getAuthority(), result.get(0).getAuthority());
        assertEquals(device2.getAuthority(), result.get(1).getAuthority());

        result = this.deviceRepo.findByIoTDeviceDataSources("abc", dataSource1.getDomain());

        assertNotNull(result);
        assertEquals(0, result.size());

        result = this.deviceRepo.findByIoTDeviceDataSources(dataSource1.getDevice(), "abc");

        assertNotNull(result);
        assertEquals(0, result.size());

    }
}
