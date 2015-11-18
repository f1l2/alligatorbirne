package configuration.management;

import java.util.Date;

import org.junit.After;
import org.junit.Before;
import org.springframework.beans.factory.annotation.Autowired;

import configuration.management.model.DataSourceDLO;
import configuration.management.model.DeviceDLO;
import configuration.management.repo.DeviceRepository;

public class AbstractTestCM {

    @Autowired
    protected DeviceRepository deviceRepo;

    protected DeviceDLO device1, device2;

    protected DataSourceDLO dataSource1;

    @Before
    public void before() {

        this.deviceRepo.deleteAll();

        device1 = new DeviceDLO();

        device1.setCreated(new Date());
        device1.setUpdated(new Date());
        device1.setName("device1");
        device1.setAuthority("http://url1.bla.bla.at");

        device1 = this.deviceRepo.save(device1);

        device2 = new DeviceDLO();

        device2.setCreated(new Date());
        device2.setUpdated(new Date());
        device2.setName("device2");
        device2.setAuthority("http://url2.bla.bla.at");

        device2 = this.deviceRepo.save(device2);

        dataSource1 = new DataSourceDLO();
        dataSource1.setDevice("deviceInformation1");
        dataSource1.setDomain("domain1");

    }

    @After
    public void after() {

    }

}
