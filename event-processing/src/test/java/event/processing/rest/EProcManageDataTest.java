package event.processing.rest;

import static com.jayway.restassured.RestAssured.given;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import common.data.DataSource;
import common.data.DeviceInformation;
import common.data.DomainInformation;
import common.data.type.DEVICE_INFORMATION_TYPE;
import common.data.type.DOMAIN_INFORMATION_TYPE;
import common.rest.RESOURCE_NAMING;
import event.processing.AbstractTestRestEP;
import event.processing.Application;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest("server.port:0")
public class EProcManageDataTest extends AbstractTestRestEP {

    @Test
    public void send() {

        DeviceInformation devInfo = new DeviceInformation();
        devInfo.setId(1l);
        devInfo.setName("deviceInfoName");
        devInfo.setType(DEVICE_INFORMATION_TYPE.SENSOR);

        DomainInformation domainInfo = new DomainInformation();
        domainInfo.setId(2l);
        domainInfo.setName("domainInfoName");
        domainInfo.setType(DOMAIN_INFORMATION_TYPE.FIRST_FLOOR);

        DataSource d = new DataSource();
        d.setDeviceInformation(devInfo);
        d.setDomainInformation(domainInfo);

        given().contentType("application/json").body(d).when().post(RESOURCE_NAMING.EPROCESSING_SEND.getPath())

        .then().statusCode(200);
    }

}
