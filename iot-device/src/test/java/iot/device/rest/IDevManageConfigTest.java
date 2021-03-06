package iot.device.rest;

import static com.jayway.restassured.RestAssured.get;
import static com.jayway.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
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
import com.jayway.restassured.response.Response;
import com.jayway.restassured.response.ResponseBodyExtractionOptions;

import common.data.ConfigurationDelegation;
import common.rest.RESOURCE_NAMING;
import iot.device.ApplicationTestContext;
import iot.device.repo.DeliveryTaskRO;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = ApplicationTestContext.class)
@WebAppConfiguration
@IntegrationTest("server.port:0")
@DirtiesContext(classMode = ClassMode.AFTER_EACH_TEST_METHOD)
public class IDevManageConfigTest extends AbstractRestTest {

    private ConfigurationDelegation cd;

    /**
     * Implicitly test method setConfiguration
     * 
     */
    @Before
    public void before() throws IOException {

        super.before();

        this.deliveryStart();

        Properties properties = new Properties();
        properties.put("key", "value");

        cd = new ConfigurationDelegation();
        cd.setDataSink(ep);
        cd.setProperties(properties);

        ResponseBodyExtractionOptions response = given().body(cd).contentType(ContentType.JSON).post(RESOURCE_NAMING.DEV_SET_CONFIGURATION.getPath())
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
        this.deliveryStop();
    }

    @Test
    public void getAllConfiguration() {

        Response response = get(RESOURCE_NAMING.DEV_GET_ALL_CONFIGURATION.getPath());

        List<DeliveryTaskRO> result = Arrays.asList(response.getBody().as(DeliveryTaskRO[].class));

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(cd.getDataSink().getUrl(), result.get(0).getUrlDataSink());

    }

    @Test
    public void getConfigurationByEPAuthority() {

        String path = RESOURCE_NAMING.DEV_GET_CONFIGURATION_BY_EP.getPath();
        path = path.replace("{authority}", cd.getDataSink().getUrl().getAuthority());

        Response response = get(path);

        DeliveryTaskRO result = response.getBody().as(DeliveryTaskRO.class);

        assertNotNull(result);
        assertEquals(cd.getDataSink().getUrl(), result.getUrlDataSink());

    }
}
