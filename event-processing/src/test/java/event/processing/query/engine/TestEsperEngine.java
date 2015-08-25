package event.processing.query.engine;

import org.junit.Test;

import common.data.DataSource;
import common.data.DeviceInformation;
import common.data.DomainInformation;

import event.processing.engine.Engine;
import event.processing.engine.EngineFactory;
import event.processing.engine.impl.EsperEngineFactory;

public class TestEsperEngine {

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

    @Test
    public void testEngine() {

        EngineFactory factory = new EsperEngineFactory();

        Engine engine = factory.getEngine();

        engine.sendEvent(generateTestDataSource1());

        delay(1000);

        engine.registerQuery("select * from DeviceInformationImpl", factory.getEngineListener());

        delay(1000);

        engine.sendEvent(generateTestDataSource1());

        delay(1000);

        engine.sendEvent(generateTestDataSource1());

    }
}
