package configuration.management.rest;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import configuration.management.AbstractTestRestCM;
import configuration.management.Application;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest("server.port:0")
public class CMgmtManageIoTDeviceTest extends AbstractTestRestCM {

    @Test
    public void register() {
        // TODO
    }

    @Test
    public void getAll() {
        // TODO
    }

    @Test
    public void registerDataSources() {
        // TODO
    }
}
