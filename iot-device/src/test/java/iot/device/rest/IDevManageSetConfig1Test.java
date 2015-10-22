package iot.device.rest;

import static com.jayway.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Properties;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.util.LinkedMultiValueMap;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.response.ResponseBodyExtractionOptions;

import common.data.ConfigurationModification;
import common.data.Connection;
import common.data.type.COMPONENT_TYPE;
import common.property.SensorReservedProperty;
import common.property.SystemReservedProperty;
import common.rest.RESOURCE_NAMING;
import common.rest.UrlUtils;
import iot.device.ApplicationTestContext;
import iot.device.status.STATUS_TYPE;
import iot.device.status.Status;
import iot.device.utility.VirtualData;
import iot.device.utility.VirtualEP;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = ApplicationTestContext.class)
@WebAppConfiguration
@IntegrationTest("server.port:0")
@DirtiesContext(classMode = ClassMode.AFTER_CLASS)
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
        dataSink.setUrl(UrlUtils.parseUrl("host:1234"));

        Properties properties = new Properties();
        properties.put(SensorReservedProperty.SUPPLY_REQ.getName(), "Pressure");
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

        assertNotNull(response);
        assertEquals("OK", response.asString());

    }

    @After
    public void after() {

        VirtualEP.setData(new ArrayList<VirtualData>());
        VirtualEP.setMap(new LinkedMultiValueMap<String, VirtualData>());

    }

    @Test
    public void setConfiguration() throws InterruptedException {

        Thread.sleep(1500);

        VirtualData first = VirtualEP.getLast();
        VirtualData second = VirtualEP.getSecondLast();
        Instant between = first.getTimeStamp().minusNanos(second.getTimeStamp().getNano());

        int ms = between.getNano() / (1000 * 1000);

        assertTrue(ms > 400);
        assertTrue(ms < 600);

        Properties properties = new Properties();
        properties.put(SystemReservedProperty.TASK_INTERVAL_MS.name(), 1000);

        cm.setProperties(properties);

        ResponseBodyExtractionOptions response = given().body(cm).contentType(ContentType.JSON).post(RESOURCE_NAMING.IDEV_SET_CONFIGURATION.getPath())
                //
                .then().contentType(ContentType.TEXT)
                //
                .extract().body();

        response.asString();

        assertNotNull(response);
        assertEquals("OK", response.asString());

        Thread.sleep(2000);

        first = VirtualEP.getLast();
        second = VirtualEP.getSecondLast();
        between = first.getTimeStamp().minusSeconds(second.getTimeStamp().getEpochSecond());
        assertEquals(1, between.getEpochSecond());

        assertEquals(1, VirtualEP.getMap().size());
    }

}
