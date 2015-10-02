package event.processing.rest;

import static com.jayway.restassured.RestAssured.get;
import static com.jayway.restassured.RestAssured.given;
import static com.jayway.restassured.RestAssured.when;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.jayway.restassured.response.Response;

import common.rest.RESOURCE_NAMING;
import event.processing.AbstractTestRestEP;
import event.processing.Application;
import event.processing.query.Query;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest("server.port:0")
public class EProcManageQueryTest extends AbstractTestRestEP {

    @Test
    public void getAllQueries1() {

        Response response = get(RESOURCE_NAMING.EPROCESSING_GET_ALL_QUERIES.getPath());

        List<Query> result = Arrays.asList(response.getBody().as(Query[].class));

        assertEquals(200, response.getStatusCode());
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(query.getCondition().generate(), result.get(0).getCondition().generate());
    }

    @Test
    public void getAllQueries2() {

        when().get(RESOURCE_NAMING.EPROCESSING_GET_ALL_QUERIES.getPath())

        .then().statusCode(200);
    }

    @Test
    public void registerQuery() {

        String query = "CONDITION name = 'device1'";
        String path = new String(RESOURCE_NAMING.EPROCESSING_REGISTRATION_QUERY.getPath());
        path = StringUtils.replace(path, "{name}", "query1");

        given().body(query).when().post(path)

        .then().statusCode(HttpStatus.OK.value());
    }

    @Test
    public void registerQueryParseError() {

        String query = "AVC name = 'device1'";
        String path = new String(RESOURCE_NAMING.EPROCESSING_REGISTRATION_QUERY.getPath());
        path = StringUtils.replace(path, "{name}", "query1");

        given().body(query).when().post(path)

        .then().statusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
    }

    @Test
    public void registerQueryQueryExistsError() {

        String query = "CONDITION name = 'device1'";
        String path = new String(RESOURCE_NAMING.EPROCESSING_REGISTRATION_QUERY.getPath());
        path = StringUtils.replace(path, "{name}", "query2");

        given().body(query).when().post(path)

        .then().statusCode(HttpStatus.OK.value());

        given().body(query).when().post(path)

        .then().statusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
    }

    @Test
    public void registerRule() {

        String rule = "query TRIGGERS device1, domain1, configurationManagement1";
        String path = new String(RESOURCE_NAMING.EPROCESSING_REGISTRATION_RULE.getPath());
        path = StringUtils.replace(path, "{name}", "rule1");

        given().body(rule).when().post(path)

        .then().statusCode(HttpStatus.OK.value());
    }

    @Test
    public void registerRuleExistsError() {

        String rule = "query TRIGGERS device1, domain1, configurationManagement1";
        String path = new String(RESOURCE_NAMING.EPROCESSING_REGISTRATION_RULE.getPath());
        path = StringUtils.replace(path, "{name}", "rule2");

        given().body(rule).when().post(path)

        .then().statusCode(HttpStatus.OK.value());

        given().body(rule).when().post(path)

        .then().statusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());

    }

    @Test
    public void activateRule() {

        String query = "CONDITION name = 'device1'";
        String pathQuery = new String(RESOURCE_NAMING.EPROCESSING_REGISTRATION_QUERY.getPath());
        pathQuery = StringUtils.replace(pathQuery, "{name}", "query3");

        given().body(query).when().post(pathQuery)

        .then().statusCode(HttpStatus.OK.value());

        String rule = "query3 TRIGGERS device1, domain1, configurationManagement1";
        String pathRule = new String(RESOURCE_NAMING.EPROCESSING_REGISTRATION_RULE.getPath());
        pathRule = StringUtils.replace(pathRule, "{name}", "rule3");

        given().body(rule).when().post(pathRule)

        .then().statusCode(HttpStatus.OK.value());

        String pathActivate = new String(RESOURCE_NAMING.EPROCESSING_ACTIVATIONS_RULE.getPath());
        pathActivate = StringUtils.replace(pathActivate, "{name}", "rule3");

        when().post(pathActivate)

        .then().statusCode(HttpStatus.OK.value());

    }

}
