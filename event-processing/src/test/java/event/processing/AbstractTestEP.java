package event.processing;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import common.data.DataSource;
import common.data.DeviceInformation;
import common.data.DomainInformation;
import event.processing.engine.Engine;
import event.processing.engine.EngineFactory;
import event.processing.engine.EngineListener;
import event.processing.engine.QueryTransformer;

public abstract class AbstractTestEP {

    @Autowired
    @Qualifier("esper")
    protected EngineFactory factory;

    protected Engine engine;

    protected EngineListener listener;

    protected QueryTransformer queryTransformer;

    protected DataSource ds1, ds2, ds3;

    protected int cntFiredEvents;

    protected static final int DEFAULT_DELAY_MS = 100;

    @Before
    public void before() {
        ds1 = generateTestDataSource(1l, "device1", 1l, "domain1");
        ds2 = generateTestDataSource(2l, "device2", 2l, "domain2");
        ds3 = generateTestDataSource(3l, "device3", 1l, "domain1");

        cntFiredEvents = 0;

        engine = factory.getEngine();
        queryTransformer = factory.getQueryTransformer();
        listener = factory.getEngineListener();

    }

    protected void sendEventAndWait(DataSource dataSource, long time) {
        engine.sendEvent(dataSource);
        delay(time);
    }

    protected void delay(long time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {

        }
    }

    private DataSource generateTestDataSource(Long deviceId, String deviceName, Long domainId, String domainName) {

        DeviceInformation device = new DeviceInformation();
        device.setName(deviceName);
        device.setId(deviceId);

        DomainInformation domain = new DomainInformation();
        domain.setName(domainName);
        domain.setId(domainId);

        return new DataSource(domain, device);
    }

    protected void sendEventAndWait(DataSource[] dataSources, int[] expectedFiredEvents) {

        for (int i = 0; i < dataSources.length; i++) {
            sendEventAndWait(dataSources[i], DEFAULT_DELAY_MS);

            assertEquals(expectedFiredEvents[i], cntFiredEvents);
        }

    }
}
