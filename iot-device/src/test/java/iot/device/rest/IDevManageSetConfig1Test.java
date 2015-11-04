package iot.device.rest;

import static com.jayway.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.time.Instant;
import java.util.Properties;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.response.ResponseBodyExtractionOptions;

import common.data.Connection;
import common.property.SystemReservedProperty;
import common.rest.RESOURCE_NAMING;
import iot.device.ApplicationTestContext;
import iot.device.utility.VirtualData;
import iot.device.utility.VirtualEP;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = ApplicationTestContext.class)
@WebAppConfiguration
@IntegrationTest("server.port:0")
@DirtiesContext(classMode = ClassMode.AFTER_CLASS)
public class IDevManageSetConfig1Test extends AbstractRestTest {

    private Connection ep;

    /**
     * Implicitly test method setConfiguration
     * 
     */
    @Before
    public void before() throws IOException {

        super.before();

        ep = getRandomEP();

        this.deliveryStart(ep);

        /**
         * Send change of configuration
         */
        Properties properties = new Properties();
        properties.put(SystemReservedProperty.TASK_INTERVAL_MS.getName(), 500);

        cdBuilder.buildProperties(properties);

        ResponseBodyExtractionOptions response2 = given().body(cdBuilder.getResult()).contentType(ContentType.JSON).post(RESOURCE_NAMING.IDEV_SET_CONFIGURATION.getPath())
                //
                .then().contentType(ContentType.TEXT)
                //
                .extract().body();

        assertNotNull(response2);
        assertEquals("OK", response2.asString());

    }

    @After
    public void after() {

        this.deliveryStop();

    }

    @Test
    public void setConfiguration() throws InterruptedException {

        Thread.sleep(7000);

        VirtualData first = VirtualEP.getLast();
        VirtualData second = VirtualEP.getSecondLast();
        Instant between = first.getTimeStamp().minusNanos(second.getTimeStamp().getNano());

        int ms = between.getNano() / (1000 * 1000);

        assertTrue(ms > 400);
        assertTrue(ms < 600);

        Properties properties = new Properties();
        properties.put(SystemReservedProperty.TASK_INTERVAL_MS.name(), 1000);

        cdBuilder.buildProperties(properties);

        ResponseBodyExtractionOptions response = given().body(cdBuilder.getResult()).contentType(ContentType.JSON).post(RESOURCE_NAMING.IDEV_SET_CONFIGURATION.getPath())
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
