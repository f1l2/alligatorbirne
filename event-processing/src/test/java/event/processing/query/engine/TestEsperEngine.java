package event.processing.query.engine;

import org.junit.Test;

import event.processing.AbstractTestEP;

public class TestEsperEngine extends AbstractTestEP {

    @Test
    public void testEngine() {

        sendEventAndWait(dataSource1, 1000);

        engine.registerQuery("select * from DataSource", factory.getEngineListener());

        delay(1000);

        sendEventAndWait(dataSource2, 1000);

        sendEventAndWait(dataSource3, 1000);

    }
}
