package event.processing.rest;

import static com.jayway.restassured.RestAssured.given;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import common.data.dto.DeviceDataDTO;
import common.data.model.DeviceInformation;
import common.data.model.DomainInformation;
import common.data.type.DEVICE_INFORMATION_TYPE;
import common.data.type.DOMAIN_INFORMATION_TYPE;
import common.rest.RESOURCE_NAMING;
import event.processing.Application;
import event.processing.message.AbstractTestMessageEP;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest("server.port:0")
public class EProcManageDataTest extends AbstractTestMessageEP {

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

        DeviceDataDTO dd = new DeviceDataDTO();
        dd.setDevice(devInfo);
        dd.addDomain(domainInfo);

        given().contentType("application/json").body(dd).when().post(RESOURCE_NAMING.EP_SEND.getPath())

        .then().statusCode(200);
    }

}
