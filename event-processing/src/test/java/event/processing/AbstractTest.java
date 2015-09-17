package event.processing;

import org.junit.Before;

import common.data.DataSource;
import common.data.DeviceInformation;
import common.data.DomainInformation;

import event.processing.engine.Engine;
import event.processing.engine.EngineFactory;
import event.processing.engine.QueryTransformer;
import event.processing.engine.impl.EsperEngineFactory;

public abstract class AbstractTest {

    protected EngineFactory factory;
    protected Engine engine;
    protected QueryTransformer queryTransformer;

    protected DataSource dataSource1, dataSource2, dataSource3;

    @Before
    public void before() {
        dataSource1 = generateTestDataSource(1l, "name1");
        dataSource2 = generateTestDataSource(2l, "name2");
        dataSource3 = generateTestDataSource(3l, "name3");

        factory = new EsperEngineFactory();
        engine = factory.getEngine();
        queryTransformer = factory.getQueryTransformer();

    }

    private DataSource generateTestDataSource(Long deviceId, String deviceName) {

        DeviceInformation device = new DeviceInformation();
        device.setName("name");
        device.setId(10);

        DomainInformation domain = new DomainInformation();

        return new DataSource(domain, device);
    }

    protected void sendEventAndWait(DataSource dataSource, long time) {
        engine.sendEvent(dataSource);
        delay(time);
    }

    protected void delay(long time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
