package event.processing.rest;

import static com.jayway.restassured.RestAssured.get;
import static com.jayway.restassured.RestAssured.when;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
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
public class TestEProcManageQuery extends AbstractTestRestEP {

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

        when()
                //
                .get(RESOURCE_NAMING.EPROCESSING_GET_ALL_QUERIES.getPath())

        .then()
                //
                .statusCode(200);
    }

}
