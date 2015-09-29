package event.processing.query.esper;

import java.io.IOException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.espertech.esper.client.EventBean;
import com.espertech.esper.client.UpdateListener;

import common.data.DataSource;
import event.processing.AbstractTestEP;
import event.processing.Application;
import event.processing.engine.EngineListener;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
public class TestEsperEngineUC4 extends AbstractTestEP {

    @Test
    public void test1() throws IOException {

        String query = "CONDITION id >= 1 FROM domain1";

        engine.registerQuery(queryTransformer.transform(query), new TestListener());

        sendEventAndWait(new DataSource[] { ds1, ds2, ds3 }, new int[] { 1, 1, 2 });

    }

    @Test
    public void test2() throws IOException {

        String query = "CONDITION id >= 2 FROM domain1";

        engine.registerQuery(queryTransformer.transform(query), new TestListener());

        sendEventAndWait(new DataSource[] { ds1, ds2, ds3 }, new int[] { 0, 0, 1 });
    }

    @Test
    public void test3() throws IOException {
        String query = "CONDITION id >= 0 FROM domain";

        engine.registerQuery(queryTransformer.transform(query), new TestListener());

        sendEventAndWait(new DataSource[] { ds1, ds2, ds3 }, new int[] { 0, 0, 0 });
    }

    private class TestListener extends EngineListener implements UpdateListener {

        @Override
        public void update(EventBean[] newEvents, EventBean[] oldEvents) {
            cntFiredEvents++;
        }

        @Override
        public void update() {

        }
    }

}
