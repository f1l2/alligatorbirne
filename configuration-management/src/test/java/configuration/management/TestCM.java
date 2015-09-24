package configuration.management;

import java.util.Date;

import org.junit.After;
import org.junit.Before;
import org.springframework.beans.factory.annotation.Autowired;

import configuration.management.model.IoTDeviceDataSourceRO;
import configuration.management.model.IoTDeviceRO;
import configuration.management.repo.IoTDeviceRepository;

public class TestCM {

    @Autowired
    protected IoTDeviceRepository deviceRepo;

    protected IoTDeviceRO device1, device2;

    protected IoTDeviceDataSourceRO dataSource1;

    @Before
    public void before() {

        this.deviceRepo.deleteAll();

        device1 = new IoTDeviceRO();

        device1.setCreated(new Date());
        device1.setUpdated(new Date());
        device1.setName("device1");
        device1.setAuthority("http://url1.bla.bla.at");

        device1 = this.deviceRepo.save(device1);

        device2 = new IoTDeviceRO();

        device2.setCreated(new Date());
        device2.setUpdated(new Date());
        device2.setName("device2");
        device2.setAuthority("http://url2.bla.bla.at");

        device2 = this.deviceRepo.save(device2);

        dataSource1 = new IoTDeviceDataSourceRO();
        dataSource1.setDevice("deviceInformation1");
        dataSource1.setDomain("domain1");

    }

    @After
    public void after() {

    }

}
