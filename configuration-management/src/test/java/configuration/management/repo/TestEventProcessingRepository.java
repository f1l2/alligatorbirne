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

import configuration.management.Application;
import configuration.management.TestCM;
import configuration.management.model.IoTDeviceRO;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
public class TestEventProcessingRepository extends TestCM {

    @Test
    public void findByName() {

        IoTDeviceRO result = this.deviceRepo.findByName(device1.getName());

        System.out.println(result.getUpdated());

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

        System.out.println(result);

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
    public void findByUpdatedLessThan() {

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
}
