package configuration.management;

import java.io.IOException;

import org.junit.After;
import org.junit.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import com.jayway.restassured.RestAssured;

import configuration.management.repo.IoTDeviceRepository;

public abstract class AbstractTestRestCM {

    @Autowired
    protected IoTDeviceRepository deviceRepo;

    @Value("${local.server.port}")
    protected int port;

    @Before
    public void before() throws IOException {

        RestAssured.port = port;
    }

    @After
    public void after() {
    }
}
