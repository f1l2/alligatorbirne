package event.processing.query.esper;

import java.io.IOException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import common.data.DataSource;
import event.processing.AbstractTestEP;
import event.processing.Application;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
public class TestEsperEngineUC2 extends AbstractTestEP {

    @Test
    public void test1() throws IOException {

        String query = "CONDITION id = 1 AND name = 'device1'";

        engine.registerQuery(queryTransformer.transform(query), testListener);

        sendEventAndWait(new DataSource[] { ds1, ds2, ds3 }, new int[] { 1, 1, 1 });

    }

    @Test
    public void test2() throws IOException {

        String query = "CONDITION id = 1 OR name = 'device2'";

        engine.registerQuery(queryTransformer.transform(query), testListener);

        sendEventAndWait(new DataSource[] { ds1, ds2, ds3 }, new int[] { 1, 2, 2 });

    }

    @Test
    public void test3() throws IOException {

        String query = "CONDITION NOT id = 1";

        engine.registerQuery(queryTransformer.transform(query), testListener);

        sendEventAndWait(new DataSource[] { ds1, ds2, ds3 }, new int[] { 0, 1, 2 });

    }
}
