package event.processing.query.engine.transform;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import event.processing.AbstractTest;
import event.processing.engine.EngineListener;
import event.processing.engine.impl.EsperEngineListener;
import event.processing.query.Query;

public class TestEsperTransform extends AbstractTest {

    final static Logger logger = LoggerFactory.getLogger(TestEsperTransform.class);

    private EngineListener engineListener;

    @Test
    public void testEsperEngine1() {

        String query = Query.KEYWORD_CONDITION + " DeviceInformation.id = 5  " + Query.KEYWORD_FROM;

        transformAndRegister(query);

    }

    @Test
    public void testEsperEngine2() {

        String query1 = "select d.device as device from DataSource as d where device.id = 5";

        register(query1);

        String query2 = "select d.device as device from DataSource as d where device.id = 5 and device.id = 20";

        register(query2);

        String query31 = "insert into DataSourceAgg select sum(d.device.id) as value from DataSource as d";

        String query32 = "select * from DataSourceAgg where value > 5";

        register(query31);

        register(query32);

        String query4 = "select d.device as device, d.domain as domain from DataSource d where device.id > 10 AND domain.name = 'name'";

        register(query4);

        String query51 = "insert into DataSourceAgg select sum(d.device.id) as value from DataSource.win:time(30) as d";

        String query52 = "select * from DataSourceAgg where value > 5";

        register(query51);

        register(query52);
    }

    @Test
    public void testEsperEngine3() {

        String query = "insert into DeviceInformationAgg select sum(DeviceInformation.id) as value from DeviceInformation";

        engine.registerQuery(query, new EsperEngineListener());

        query = "select * from DeviceInformationAgg where value > 5";

        engine.registerQuery(query, new EsperEngineListener());

        query = "select * from DeviceInformationAgg, DeviceInformation";

        engine.registerQuery(query, null);

        test("");

    }

    private void register(String eql) {
        engine.registerQuery(eql, factory.getEngineListener());
    }

    private void transformAndRegister(String query) {

        logger.info(String.format("Transform query: %s", query));

        String eql = queryTransformer.transform(query);

        logger.info(String.format("Generated EQL: %s", eql));

        // engine.registerQuery(eql, null);
    }

    private void test(String eql) {

        engine.registerQuery(eql, engineListener);
        delay(1000);

        sendEventAndWait(dataSource1, 1000);

        sendEventAndWait(dataSource2, 1000);

        sendEventAndWait(dataSource3, 1000);
    }
}
