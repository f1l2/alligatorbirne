package configuration.management.rest;

import static com.jayway.restassured.RestAssured.get;
import static com.jayway.restassured.RestAssured.given;
import static com.jayway.restassured.RestAssured.when;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.net.URL;
import java.util.Arrays;
import java.util.List;

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
import common.data.type.COMPONENT_TYPE;
import common.rest.RESOURCE_NAMING;
import common.rest.UtilsUrl;
import configuration.management.AbstractTestRestCM;
import configuration.management.Application;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest("server.port:0")
public class CMgmtManageEventProcessingTest extends AbstractTestRestCM {

    @Test
    public void getAll1() {

        when().get(RESOURCE_NAMING.CMGMT_GET_ALL_EVENT_PROCESSING.getPath())

        .then().statusCode(HttpStatus.OK.value());

    }

    @Test
    @Transactional
    public void getAll2() {

        register(null);

        Response response = get(RESOURCE_NAMING.CMGMT_GET_ALL_EVENT_PROCESSING.getPath());

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
                .when().post(RESOURCE_NAMING.CMGMT_REGISTER_EVENT_PROCESSING.getPath())
                //
                .then().statusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());

    }

    @Test
    public void heartbeat() {

        URL url = UtilsUrl.parseUrl("localhost:4005");

        Connection connection = new Connection();
        connection.setComponentType(COMPONENT_TYPE.EVENT_PROCESSING);
        connection.setName("EP_NAME5");
        connection.setUrl(url);

        // first register ep

        register(connection);

        // send heart beat

        Response response = given().body(connection).contentType("application/json")
                //
                .when().put(RESOURCE_NAMING.CMGMT_HEART_BEAT_EVENT_PROCESSING.getPath());

        response.getBody().as(Connection.class);

        assertEquals(HttpStatus.OK.value(), response.getStatusCode());

    }

    @Test
    public void heartbeatFailMissingRegistration() {

        URL url = UtilsUrl.parseUrl("localhost:4006");

        Connection connection = new Connection();
        connection.setComponentType(COMPONENT_TYPE.EVENT_PROCESSING);
        connection.setName("EP_NAME6");
        connection.setUrl(url);

        // send heart beat

        Response response = given().body(connection).contentType("application/json")
                //
                .when().put(RESOURCE_NAMING.CMGMT_HEART_BEAT_EVENT_PROCESSING.getPath());

        response.getBody().as(Connection.class);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR.value(), response.getStatusCode());

    }

    private void register(Connection connection) {

        if (null == connection) {
            URL url = UtilsUrl.parseUrl("localhost:3999");

            connection = new Connection();
            connection.setComponentType(COMPONENT_TYPE.EVENT_PROCESSING);
            connection.setName("EP_NAME");
            connection.setUrl(url);
        }

        given().body(connection).contentType("application/json")
                //
                .when().post(RESOURCE_NAMING.CMGMT_REGISTER_EVENT_PROCESSING.getPath())
                //
                .then().statusCode(HttpStatus.OK.value());

    }

    @Test
    public void delegate() {
        // TODO
    }

}
