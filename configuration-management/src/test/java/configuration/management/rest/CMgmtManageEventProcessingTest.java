package configuration.management.rest;

import static com.jayway.restassured.RestAssured.get;
import static com.jayway.restassured.RestAssured.given;
import static com.jayway.restassured.RestAssured.when;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

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
import common.rest.RESOURCE_NAMING;
import common.transformer.Transformer;
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

    private static Integer i;

    @Test
    @Transactional
    public void getAll2() {

        Response response = get(RESOURCE_NAMING.CMGMT_GET_ALL_EVENT_PROCESSING.getPath());

        List<Connection> result = Arrays.asList(response.getBody().as(Connection[].class));

        assertEquals(200, response.getStatusCode());
        assertNotNull(result);
        assertEquals(1, Transformer.makeCollection(this.deviceRepo.findAll()).size());
        assertEquals(1, result.size());
        assertEquals(device.getName(), result.get(0).getName());
    }

    @Test
    public void register() {
        given().body(" ").when().post(" ")

        .then().statusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());

    }

    @Test
    public void delegate() {
        // TODO
    }

}
