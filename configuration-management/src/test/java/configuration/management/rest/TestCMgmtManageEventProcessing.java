package configuration.management.rest;

import static com.jayway.restassured.RestAssured.when;

import org.apache.http.HttpStatus;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import configuration.management.Application;
import configuration.management.AbstractTestCM;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest("server.port:0")
public class TestCMgmtManageEventProcessing extends AbstractTestCM {

    @Test
    public void getAll() {

        Long id = this.device1.getId();

        when().get("/registrations/eventprocessing").then().statusCode(HttpStatus.SC_OK).body("name", Matchers.is("Mickey Mouse")).body("id", Matchers.is(id));
    }
}
