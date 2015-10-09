package configuration.management;

import java.io.IOException;

import org.junit.After;
import org.junit.Before;
import org.springframework.beans.factory.annotation.Value;

import com.jayway.restassured.RestAssured;

public abstract class AbstractTestRestCM {

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
