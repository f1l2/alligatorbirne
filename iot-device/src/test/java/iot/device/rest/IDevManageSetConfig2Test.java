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
public class IDevManageSetConfig2Test {

    private ConfigurationModification cm1, cm2;

    @Value("${local.server.port}")
    protected int port;

    @Autowired
    private Status status;

    /**
     * Implicitly test method setConfiguration
     * 
     * @throws InterruptedException
     * 
     */
    @Before
    public void before() throws IOException, InterruptedException {
        RestAssured.port = port;

        status.setCurrent(STATUS_TYPE.TEST);

        Connection dataSink1 = new Connection();
        dataSink1.setComponentType(COMPONENT_TYPE.EVENT_PROCESSING);
        dataSink1.setName("name ep1");
        dataSink1.setUrl(UtilsUrl.parseUrl("host:1234"));

        Connection dataSink2 = new Connection();
        dataSink2.setComponentType(COMPONENT_TYPE.EVENT_PROCESSING);
        dataSink2.setName("name ep2");
        dataSink2.setUrl(UtilsUrl.parseUrl("host:1235"));

        Properties properties = new Properties();
        properties.put(SensorReservedProperty.SUPPLY_REQ, "Pressure");
        properties.put(SystemReservedProperty.TASK_INTERVAL_MS.name(), 500);

        cm1 = new ConfigurationModification();
        cm1.setDataSink(dataSink1);
        cm1.setProperties(properties);

        cm2 = new ConfigurationModification();
        cm2.setDataSink(dataSink2);
        cm2.setProperties(properties);

        ResponseBodyExtractionOptions response = given().body(cm1).contentType(ContentType.JSON).post(RESOURCE_NAMING.IDEV_SET_CONFIGURATION.getPath())
                //
                .then().contentType(ContentType.TEXT)
                //
                .extract().body();

        response.asString();

        assertNotNull(response);
        assertEquals("OK", response.asString());

        Thread.sleep(100);

        response = given().body(cm2).contentType(ContentType.JSON).post(RESOURCE_NAMING.IDEV_SET_CONFIGURATION.getPath())
                //
                .then().contentType(ContentType.TEXT)
                //
                .extract().body();

        response.asString();

        assertNotNull(response);
        assertEquals("OK", response.asString());

    }

    @After
    public void after() {
    }

    @Test
    public void setConfiguration() throws InterruptedException {

        Thread.sleep(100);

        assertEquals(2, VtEP.getData().size());
        assertEquals(2, VtEP.getMap().size());

        Thread.sleep(600);

        assertEquals(4, VtEP.getData().size());
        assertEquals(2, VtEP.getMap().size());

        Thread.sleep(600);

        assertEquals(6, VtEP.getData().size());
        assertEquals(2, VtEP.getMap().size());

    }

}
