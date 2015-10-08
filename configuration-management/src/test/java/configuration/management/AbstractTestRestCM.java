package configuration.management;

import java.io.IOException;

import org.junit.After;
import org.junit.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import com.jayway.restassured.RestAssured;

import configuration.management.model.IoTDeviceRO;
import configuration.management.repo.IoTDeviceRepository;

public abstract class AbstractTestRestCM {

    @Autowired
    protected IoTDeviceRepository deviceRepo;

    @Value("${local.server.port}")
    protected int port;

    protected IoTDeviceRO device;

    @Before
    public void before() throws IOException {
        RestAssured.port = port;

        device = new IoTDeviceRO();
        device.setName("deviceName");
        device.setAuthority("authority");

        this.deviceRepo.save(device);

    }

    @After
    public void after() {
        this.deviceRepo.delete(device);
    }
}
