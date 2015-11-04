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
import common.data.builder.CDBuilder;
import common.data.type.COMPONENT_TYPE;
import common.rest.RESOURCE_NAMING;
import common.rest.ResourceUtils;
import common.rest.UrlUtils;
import iot.device.status.STATUS_TYPE;
import iot.device.status.Status;
import iot.device.utility.VirtualEP;

public class AbstractRestTest {

    @Value("${local.server.port}")
    protected int port;

    @Autowired
    protected Status status;

    protected Connection ep;

    protected CDBuilder cdBuilder;

    private static int count = 0;

    @Before
    public void before() throws IOException {

        VirtualEP.reset();

        RestAssured.port = port;

        status.setCurrent(STATUS_TYPE.TEST);

        ep = getRandomEP();

        cdBuilder = new CDBuilder();
        cdBuilder.addDataSource("pressure", "floor1");
        cdBuilder.buildDataSink(ep);

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
        deliveryStart(cdBuilder);
    }

    protected void deliveryStart(Connection ep) {
        cdBuilder.buildDataSink(ep);
        deliveryStart(cdBuilder);
    }

    protected void deliveryStart(CDBuilder cdBuilder) {

        String path = ResourceUtils.getPath(RESOURCE_NAMING.IDEV_START_DELIVERY);

        ResponseBodyExtractionOptions response = given().body(cdBuilder.getResult()).contentType(ContentType.JSON).post(path)
                //
                .then().contentType(ContentType.TEXT)
                //
                .extract().body();

        assertNotNull(response);
        assertEquals("OK", response.asString());

    }

    protected void deliveryStop() {
        this.deliveryStop(cdBuilder, ep);
    }

    protected void deliveryStop(CDBuilder cdBuilder, Connection dataSink) {

        String path = ResourceUtils.getPath(RESOURCE_NAMING.IDEV_STOP_DELIVERY);

        ResponseBodyExtractionOptions response = given().body(cdBuilder.getResult()).contentType(ContentType.JSON).post(path)
                //
                .then().contentType(ContentType.TEXT)
                //
                .extract().body();

        assertNotNull(response);
        assertEquals("OK", response.asString());
    }
}
