package configuration.management;

import java.io.IOException;
import java.net.URL;

import org.junit.After;
import org.junit.Before;
import org.springframework.beans.factory.annotation.Value;

import com.jayway.restassured.RestAssured;

import common.data.Connection;
import common.data.type.COMPONENT_TYPE;
import common.rest.UtilsUrl;

public abstract class AbstractTestRestCM {

    @Value("${local.server.port}")
    protected int port;

    protected Connection connection;

    @Before
    public void before() throws IOException {
        RestAssured.port = port;

        URL url = UtilsUrl.parseUrl("localhost:4000");

        connection = new Connection();
        connection.setComponentType(COMPONENT_TYPE.EVENT_PROCESSING);
        connection.setName("EP_NAME");
        connection.setUrl(url);

    }

    @After
    public void after() {
    }
}
