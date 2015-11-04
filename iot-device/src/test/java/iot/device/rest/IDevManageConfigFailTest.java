package iot.device.rest;

import static com.jayway.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.List;

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
import common.data.builder.DSBuilder;
import common.rest.RESOURCE_NAMING;
import common.rest.ResourceUtils;
import iot.device.ApplicationConfig;
import iot.device.ApplicationTestContext;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = ApplicationTestContext.class)
@WebAppConfiguration
@IntegrationTest("server.port:0")
@DirtiesContext(classMode = ClassMode.AFTER_CLASS)
public class IDevManageConfigFailTest extends AbstractRestTest {

    @Test
    public void setConfigurationFailedDueExceedMax() {

        DSBuilder dsBuilder = new DSBuilder();
        dsBuilder.buildDataSource("pressure", "floor1");

        List<Connection> eps = new ArrayList<Connection>();

        for (int i = 0; i < ApplicationConfig.MAX_TASKS; i++) {

            Connection ep = getRandomEP();
            eps.add(ep);

            this.deliveryStart(dsBuilder, ep);
        }

        String path = ResourceUtils.getPath(RESOURCE_NAMING.IDEV_START_DELIVERY, getRandomEP().getUrl().getAuthority());

        ResponseBodyExtractionOptions responseTooMuch = given().body(dsBuilder.getResult()).contentType(ContentType.JSON).post(path)
                //
                .then().contentType(ContentType.TEXT)
                //
                .extract().body();

        assertNotNull(responseTooMuch);
        assertEquals("Device has no spare capacity.", responseTooMuch.asString());

        for (Connection ep : eps) {
            this.deliveryStop(dsBuilder, ep);
        }

    }
}
