package configuration.management.rest;

import static com.jayway.restassured.RestAssured.get;
import static com.jayway.restassured.RestAssured.given;
import static com.jayway.restassured.RestAssured.when;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.jayway.restassured.response.Response;

import common.data.Connection;
import common.data.builder.CDBuilder;
import common.data.type.COMPONENT_TYPE;
import common.property.SensorReservedProperty;
import common.rest.RESOURCE_NAMING;
import common.rest.ResourceUtils;
import common.rest.UrlUtils;
import configuration.management.AbstractTestRestCM;
import configuration.management.Application;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest("server.port:0")
public class CMManageEventProcessingTest extends AbstractTestRestCM {

    @Test
    public void delegate() {

        // register ep

        URL url = UrlUtils.parseUrl(authority);

        Connection connection = new Connection();
        connection.setComponentType(COMPONENT_TYPE.EVENT_PROCESSING);
        connection.setUrl(url);

        connection = register(connection);

        // create configuration delegation

        Properties properties = new Properties();
        properties.put(SensorReservedProperty.REQUEST_FOR_DELIVERY.getName(), "temperature");

        CDBuilder cDBuilder = new CDBuilder();
        cDBuilder.addDataSource("device", "domain")//
                .buildDataSink(connection)//
                .buildProperties(properties);

        // send delegation

        given().body(cDBuilder.getResult()).contentType("application/json")
                //
                .when().post(ResourceUtils.getPath(RESOURCE_NAMING.CM_DELEGATION))
                //
                .then().statusCode(HttpStatus.OK.value());

    }

    @Test
    public void getAll1() {

        when().get(ResourceUtils.getPath(RESOURCE_NAMING.CM_GET_ALL_EVENT_PROCESSING))

                .then().statusCode(HttpStatus.OK.value());

    }

    @Test
    @Transactional
    public void getAll2() {

        register(null);

        Response response = get(ResourceUtils.getPath(RESOURCE_NAMING.CM_GET_ALL_EVENT_PROCESSING));

        List<Connection> result = Arrays.asList(response.getBody().as(Connection[].class));

        assertEquals(200, response.getStatusCode());
        assertNotNull(result);
        assertEquals(1, result.size());
        assertNotNull(result.get(0).getId());

    }

    @Test
    public void registerFailMissingAuthority() {

        Connection connection = new Connection();
        connection.setComponentType(COMPONENT_TYPE.EVENT_PROCESSING);
        connection.setName("EP_NAME");

        given().body(connection).contentType("application/json")
                //
                .when().post(RESOURCE_NAMING.CM_REGISTER_EVENT_PROCESSING.getPath())
                //
                .then().statusCode(HttpStatus.BAD_REQUEST.value());

    }

    @Test
    public void heartbeat() {

        URL url = UrlUtils.parseUrl(authority);

        Connection connection = new Connection();
        connection.setComponentType(COMPONENT_TYPE.EVENT_PROCESSING);
        connection.setName("EP_NAME5");
        connection.setUrl(url);

        // first register ep

        connection = register(connection);

        // send heart beat

        Response response = given()
                //
                .when().put(ResourceUtils.getPath(RESOURCE_NAMING.CM_HEART_BEAT_EVENT_PROCESSING, Long.toString(connection.getId()), "12", "13"));

        assertEquals(HttpStatus.OK.value(), response.getStatusCode());

    }

    @Test
    public void heartbeatFailMissingRegistration() {

        URL url = UrlUtils.parseUrl(authority);

        Connection connection = new Connection();
        connection.setComponentType(COMPONENT_TYPE.EVENT_PROCESSING);
        connection.setName("EP_NAME6");
        connection.setUrl(url);

        // send heart beat
        Response response = given()
                //
                .when().put(ResourceUtils.getPath(RESOURCE_NAMING.CM_HEART_BEAT_EVENT_PROCESSING, "123", "12", "13"));

        assertEquals(HttpStatus.BAD_REQUEST.value(), response.getStatusCode());

    }

    private Connection register(Connection connection) {

        if (null == connection) {
            URL url = UrlUtils.parseUrl(authority);

            connection = new Connection();
            connection.setComponentType(COMPONENT_TYPE.EVENT_PROCESSING);
            connection.setName("EP_NAME");
            connection.setUrl(url);
        }

        Response response = given().body(connection).contentType("application/json")
                //
                .when().post(RESOURCE_NAMING.CM_REGISTER_EVENT_PROCESSING.getPath());

        assertEquals(HttpStatus.OK.value(), response.getStatusCode());

        return response.getBody().as(Connection.class);
    }

}
