package event.processing.query.engine;

import org.junit.Test;

import common.data.DeviceInformation;

import event.processing.engine.Engine;
import event.processing.engine.EngineFactory;
import event.processing.engine.impl.EsperEngineFactory;

public class TestEspEngine {

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

        return device;
    }

    @Test
    public void testEngine() {

        EngineFactory factory = new EsperEngineFactory();

        Engine engine = factory.getEngine();

        engine.sendEvent(TestEspEngine.generateRandomInformation());

        delay(1000);

        engine.registerQuery("select * from DeviceInformationImpl");

        delay(1000);

        engine.sendEvent(TestEspEngine.generateRandomInformation());

        delay(1000);

        engine.sendEvent(TestEspEngine.generateRandomInformation());

    }
}
