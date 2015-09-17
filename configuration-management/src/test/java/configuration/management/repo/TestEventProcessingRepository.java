package configuration.management.repo;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import configuration.management.ConfigurationManagement;
import configuration.management.model.IoTDeviceDataSourceRO;
import configuration.management.model.IoTDeviceRO;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = ConfigurationManagement.class)
@DirtiesContext(classMode = ClassMode.AFTER_EACH_TEST_METHOD)
public class TestEventProcessingRepository {

    @Autowired
    private IoTDeviceRepository deviceRepo;

    private IoTDeviceRO device1, device2;

    private IoTDeviceDataSourceRO dataSource1;

    @Before
    public void before() {

        this.deviceRepo.deleteAll();

        device1 = new IoTDeviceRO();

        device1.setDate(new Date());
        device1.setName("device1");
        device1.setUrl("http://url1.bla.bla.at");

        device1 = this.deviceRepo.save(device1);

        device2 = new IoTDeviceRO();

        device2.setDate(new Date());
        device2.setName("device2");
        device2.setUrl("http://url2.bla.bla.at");

        device2 = this.deviceRepo.save(device2);

        dataSource1 = new IoTDeviceDataSourceRO();
        dataSource1.setDevice("deviceInformation1");
        dataSource1.setDomain("domain1");

    }

    @Test
    public void findByName() {

        IoTDeviceRO result = this.deviceRepo.findByName(device1.getName());

        assertNotNull(result);
        assertEquals(device1.getId(), result.getId());
        assertEquals(device1.getName(), result.getName());
        assertEquals(device1.getUrl(), result.getUrl());
    }

    @Test
    public void saveDataSource() {

        IoTDeviceRO result = this.deviceRepo.findByName(device1.getName());
        result.getIoTDeviceDataSources().add(dataSource1);

        result = this.deviceRepo.save(result);

        result = this.deviceRepo.findByName(device1.getName());

        System.out.println(result);

        assertNotNull(result);
        assertEquals(device1.getId(), result.getId());
        assertEquals(device1.getName(), result.getName());
        assertEquals(device1.getUrl(), result.getUrl());

        assertNotNull(result.getIoTDeviceDataSources());
        assertEquals(1, result.getIoTDeviceDataSources().size());
        assertEquals(dataSource1.getDevice(), result.getIoTDeviceDataSources().get(0).getDevice());
        assertEquals(dataSource1.getDomain(), result.getIoTDeviceDataSources().get(0).getDomain());
    }
}
