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
public class TestEsperEngineUC1 extends AbstractTestEP {

    @Test
    public void test1() throws IOException {

        String query1 = "CONDITION name = 'device1'";
        String query2 = "CONDITION name = 'device2'";
        String query3 = "CONDITION name = 'device3'";

        TestListener testListener = new TestListener();

        engine.registerQuery(queryTransformer.transform(query1), testListener);
        engine.registerQuery(queryTransformer.transform(query2), testListener);
        engine.registerQuery(queryTransformer.transform(query3), testListener);

        sendEventAndWait(new DataSource[] { ds1, ds2, ds3 }, new int[] { 1, 2, 3 });

    }

    @Test
    public void test2() throws IOException {

        /**
         * CONDITION id = 5
         */

        String query1 = "CONDITION name = 'device1'";

        TestListener testListener = new TestListener();

        engine.registerQuery(queryTransformer.transform(query1), testListener);

        sendEventAndWait(new DataSource[] { ds1, ds2, ds3 }, new int[] { 1, 1, 1 });

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
