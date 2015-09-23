package configuration.management.repo;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import configuration.management.ConfigurationManagement;
import configuration.management.TestCM;
import configuration.management.model.IoTDeviceRO;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = ConfigurationManagement.class)
public class TestEventProcessingRepository extends TestCM {

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
}
