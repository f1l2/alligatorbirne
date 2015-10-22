package iot.device.rest;

import static com.jayway.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.IOException;
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
        dataSink1.setUrl(UrlUtils.parseUrl("host:1234"));

        Connection dataSink2 = new Connection();
        dataSink2.setComponentType(COMPONENT_TYPE.EVENT_PROCESSING);
        dataSink2.setName("name ep2");
        dataSink2.setUrl(UrlUtils.parseUrl("host:1235"));

        Properties properties = new Properties();
        properties.put(SensorReservedProperty.SUPPLY_REQ.getName(), "pressure");
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
        VirtualEP.setData(new ArrayList<VirtualData>());
        VirtualEP.setMap(new LinkedMultiValueMap<String, VirtualData>());
    }

    @Test
    public void setConfiguration() throws InterruptedException {

        Thread.sleep(500);

        assertEquals(2, VirtualEP.getMap().size());

    }

}
