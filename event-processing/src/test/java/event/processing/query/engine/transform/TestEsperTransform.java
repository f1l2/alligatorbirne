package event.processing.query.engine.transform;

import org.junit.Before;
import org.junit.Test;

import common.data.DeviceInformation;

import event.processing.engine.Engine;
import event.processing.engine.EngineListener;
import event.processing.engine.QueryTransformer;
import event.processing.engine.impl.EsperEngineFactory;
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

        String eql = this.queryTransformer.transform(Query.KEYWORD_CONDITION + " 10 = 10  " + Query.KEYWORD_FROM + " Domain");

        this.test(eql);
    }

    @Test
    public void testEsperEngine2() {

        String eql = this.queryTransformer.transform(Query.KEYWORD_CONDITION + " DeviceInformation.id = 11 " + Query.KEYWORD_FROM + " Domain");

        this.test(eql);
    }

    private void test(String eql) {
        System.out.println(eql);

        engine.sendEvent(generateRandomInformation());

        delay(1000);

        engine.registerQuery(eql, engineListener);
        delay(1000);

        engine.sendEvent(generateRandomInformation());

        delay(1000);

        engine.sendEvent(generateRandomInformation());

    }

    private void delay(long time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    private static DeviceInformation generateRandomInformation() {

        DeviceInformation device = new DeviceInformation();
        device.setName("name");
        device.setId(10);
        return device;
    }

}
