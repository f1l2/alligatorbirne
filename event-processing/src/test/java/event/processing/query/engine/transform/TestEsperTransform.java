package event.processing.query.engine.transform;

import org.junit.Before;
import org.junit.Test;

import common.data.DataSource;
import common.data.DeviceInformation;
import common.data.DomainInformation;

import event.processing.engine.Engine;
import event.processing.engine.EngineListener;
import event.processing.engine.QueryTransformer;
import event.processing.engine.impl.EsperEngineFactory;
import event.processing.engine.impl.EsperEngineListener;
import event.processing.query.Query;

public class TestEsperTransform {

    private EsperEngineFactory factory;

    private Engine engine;

    private QueryTransformer queryTransformer;

    private EngineListener engineListener;

    @Before
    public void before() {
        factory = new EsperEngineFactory();

        engine = factory.getEngine();
        queryTransformer = factory.getQueryTransformer();
        engineListener = factory.getEngineListener();
    }

    @Test
    public void testEsperEngine1() {

        String eql = this.queryTransformer.transform(Query.KEYWORD_CONDITION + " DeviceInformation.id = 5  " + Query.KEYWORD_FROM);

        this.test(eql);
    }

    @Test
    public void testEsperEngine2() {

        String eql = this.queryTransformer.transform(Query.KEYWORD_CONDITION + " DeviceInformation.id = 11 " + Query.KEYWORD_FROM + " Domain");

        this.test(eql);
    }

    @Test
    public void testEsperEngine3() {

        String query = "insert into DeviceInformationAgg select sum(id) as value from DeviceInformation";

        engine.registerQuery(query, new EsperEngineListener());

        query = "select * from DeviceInformationAgg where value > 5";

        engine.registerQuery(query, new EsperEngineListener());

        query = "select * from DeviceInformationAgg, DeviceInformation";

        engine.registerQuery(query, null);

        test("");

    }

    private void test(String eql) {
        System.out.println(eql);

        engine.sendEvent(generateTestDataSource1());

        delay(1000);

        engine.registerQuery(eql, engineListener);
        delay(1000);

        engine.sendEvent(generateTestDataSource1());

        delay(1000);

        engine.sendEvent(generateTestDataSource2());

        delay(1000);

    }

    private void delay(long time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    private static DataSource generateTestDataSource1() {

        DeviceInformation device = new DeviceInformation();
        device.setName("name");
        device.setId(10);

        DomainInformation domain = new DomainInformation();

        return new DataSource(domain, device);
    }

    private static DataSource generateTestDataSource2() {

        DeviceInformation device = new DeviceInformation();
        device.setName("name");
        device.setId(5);

        DomainInformation domain = new DomainInformation();

        return new DataSource(domain, device);
    }

}
