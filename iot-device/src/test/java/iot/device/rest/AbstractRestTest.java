package iot.device.rest;

import static com.jayway.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.IOException;

import org.junit.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.response.ResponseBodyExtractionOptions;

import common.data.Connection;
import common.data.builder.DSBuilder;
import common.data.type.COMPONENT_TYPE;
import common.rest.RESOURCE_NAMING;
import common.rest.ResourceUtils;
import common.rest.UrlUtils;
import iot.device.status.STATUS_TYPE;
import iot.device.status.Status;

public class AbstractRestTest {

    @Value("${local.server.port}")
    protected int port;

    @Autowired
    protected Status status;

    protected Connection ep;

    protected DSBuilder dsBuilder;

    private static int count = 0;

    @Before
    public void before() throws IOException {

        RestAssured.port = port;

        status.setCurrent(STATUS_TYPE.TEST);

        ep = getRandomEP();

        dsBuilder = new DSBuilder();
        dsBuilder.buildDataSource("pressure", "floor1");
    }

    protected Connection getRandomEP() {

        count++;

        Connection connection = new Connection();
        connection.setComponentType(COMPONENT_TYPE.EVENT_PROCESSING);
        connection.setName("name ep");
        connection.setUrl(UrlUtils.parseUrl("host:123" + count));

        return connection;
    }

    protected void deliveryStart() {
        deliveryStart(dsBuilder, ep);
    }

    protected void deliveryStart(Connection ep) {
        deliveryStart(dsBuilder, ep);
    }

    protected void deliveryStart(DSBuilder dsBuilder) {
        deliveryStart(dsBuilder, ep);
    }

    protected void deliveryStart(DSBuilder dsBuilder, Connection ep) {

        String path = ResourceUtils.getPath(RESOURCE_NAMING.IDEV_START_DELIVERY, ep.getUrl().getAuthority());

        ResponseBodyExtractionOptions response = given().body(dsBuilder.getResult()).contentType(ContentType.JSON).post(path)
                //
                .then().contentType(ContentType.TEXT)
                //
                .extract().body();

        assertNotNull(response);
        assertEquals("OK", response.asString());

    }

    protected void deliveryStop() {
        this.deliveryStop(dsBuilder, ep);
    }

    protected void deliveryStop(DSBuilder dsBuilder, Connection dataSink) {

        String path = ResourceUtils.getPath(RESOURCE_NAMING.IDEV_STOP_DELIVERY, dataSink.getUrl().getAuthority());

        ResponseBodyExtractionOptions response = given().body(dsBuilder.getResult()).contentType(ContentType.JSON).post(path)
                //
                .then().contentType(ContentType.TEXT)
                //
                .extract().body();

        assertNotNull(response);
        assertEquals("OK", response.asString());
    }
}
