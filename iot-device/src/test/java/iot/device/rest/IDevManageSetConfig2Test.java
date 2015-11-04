package iot.device.rest;

import static org.junit.Assert.assertEquals;

import java.io.IOException;

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

import common.data.Connection;
import iot.device.ApplicationTestContext;
import iot.device.utility.VirtualEP;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = ApplicationTestContext.class)
@WebAppConfiguration
@IntegrationTest("server.port:0")
@DirtiesContext(classMode = ClassMode.AFTER_CLASS)
public class IDevManageSetConfig2Test extends AbstractRestTest {

    /**
     * Implicitly test method setConfiguration
     * 
     */

    private Connection ep1, ep2;

    @Before
    public void before() throws IOException {

        super.before();

        ep1 = getRandomEP();
        ep2 = getRandomEP();

        /**
         * Start delivery data sink 1
         */

        this.deliveryStart(ep1);

        /**
         * Start delivery data sink 2
         */

        this.deliveryStart(ep2);

    }

    @After
    public void after() {

        this.deliveryStop(dsBuilder, ep1);
        this.deliveryStop(dsBuilder, ep2);

    }

    @Test
    public void setConfiguration() throws InterruptedException {

        Thread.sleep(500);

        assertEquals(2, VirtualEP.getMap().size());

    }

}
