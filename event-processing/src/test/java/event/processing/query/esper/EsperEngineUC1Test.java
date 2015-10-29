package event.processing.query.esper;

import java.io.IOException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import common.data.model.DeviceData;
import event.processing.AbstractTestEP;
import event.processing.Application;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
public class EsperEngineUC1Test extends AbstractTestEP {

    @Test
    public void test1() throws IOException {

        String query1 = "CONDITION name = 'device1'";
        String query2 = "CONDITION name = 'device2'";
        String query3 = "CONDITION name = 'device3'";

        engine.register(transformQueryString(query1, "query1"), testListener);
        engine.register(transformQueryString(query2, "query2"), testListener);
        engine.register(transformQueryString(query3, "query3"), testListener);

        sendEventAndWait(new DeviceData[] { dd1, dd2, dd3 }, new int[] { 1, 2, 3 });

    }

    @Test
    public void test2() throws IOException {

        /**
         * CONDITION id = 5
         */

        String query1 = "CONDITION name = 'device1'";

        engine.register(transformQueryString(query1, "query1"), testListener);

        sendEventAndWait(new DeviceData[] { dd1, dd2, dd3 }, new int[] { 1, 1, 1 });

    }
}
