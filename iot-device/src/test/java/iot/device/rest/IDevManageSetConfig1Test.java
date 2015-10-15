package iot.device.rest;

import static com.jayway.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.IOException;
import java.util.Properties;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.response.ResponseBodyExtractionOptions;

import common.data.ConfigurationModification;
import common.data.Connection;
import common.data.type.COMPONENT_TYPE;
import common.rest.RESOURCE_NAMING;
import common.rest.UtilsUrl;
import iot.device.ApplicationTestContext1;
import iot.device.property.SensorReservedProperty;
import iot.device.property.SystemReservedProperty;
import iot.device.status.STATUS_TYPE;
import iot.device.status.Status;
import iot.device.vt.VtEP;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = ApplicationTestContext1.class)
@WebAppConfiguration
@IntegrationTest("server.port:0")
public class IDevManageSetConfig1Test {

    private ConfigurationModification cm;

    @Value("${local.server.port}")
    protected int port;

    @Autowired
    private Status status;

    /**
     * Implicitly test method setConfiguration
     * 
     */
    @Before
    public void before() throws IOException {
        RestAssured.port = port;

        status.setCurrent(STATUS_TYPE.TEST);

        Connection dataSink = new Connection();
        dataSink.setComponentType(COMPONENT_TYPE.EVENT_PROCESSING);
        dataSink.setName("name ep");
        dataSink.setUrl(UtilsUrl.parseUrl("host:1234"));

        Properties properties = new Properties();
        properties.put(SensorReservedProperty.SUPPLY_REQ, "Pressure");
        properties.put(SystemReservedProperty.TASK_INTERVAL_MS.name(), 500);

        cm = new ConfigurationModification();
        cm.setDataSink(dataSink);
        cm.setProperties(properties);

        ResponseBodyExtractionOptions response = given().body(cm).contentType(ContentType.JSON).post(RESOURCE_NAMING.IDEV_SET_CONFIGURATION.getPath())
                //
                .then().contentType(ContentType.TEXT)
                //
                .extract().body();

        response.asString();

        // String result = response.getBody().as(String.class, ObjectMapper.JAXB);

        assertNotNull(response);
        assertEquals("OK", response.asString());

    }

    @After
    public void after() {
    }

    @Test
    public void setConfiguration() throws InterruptedException {

        assertEquals(1, VtEP.getData().size());
        assertEquals(1, VtEP.getMap().size());

        Thread.sleep(600);

        assertEquals(2, VtEP.getData().size());
        assertEquals(1, VtEP.getMap().size());

        Thread.sleep(600);

        assertEquals(3, VtEP.getData().size());
        assertEquals(1, VtEP.getMap().size());

        Properties properties = new Properties();
        properties.put(SystemReservedProperty.TASK_INTERVAL_MS.name(), 1200);

        cm.setProperties(properties);

        ResponseBodyExtractionOptions response = given().body(cm).contentType(ContentType.JSON).post(RESOURCE_NAMING.IDEV_SET_CONFIGURATION.getPath())
                //
                .then().contentType(ContentType.TEXT)
                //
                .extract().body();

        response.asString();

        assertNotNull(response);
        assertEquals("OK", response.asString());

        Thread.sleep(600);

        assertEquals(4, VtEP.getData().size());
        assertEquals(1, VtEP.getMap().size());

        Thread.sleep(600);

        assertEquals(4, VtEP.getData().size());
        assertEquals(1, VtEP.getMap().size());

        Thread.sleep(600);

        assertEquals(5, VtEP.getData().size());
        assertEquals(1, VtEP.getMap().size());

    }

}
