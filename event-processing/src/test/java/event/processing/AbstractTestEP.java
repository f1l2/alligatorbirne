package event.processing;

import org.junit.Before;

import common.data.DataSource;
import common.data.DeviceInformation;
import common.data.DomainInformation;

import event.processing.engine.Engine;
import event.processing.engine.EngineFactory;
import event.processing.engine.QueryTransformer;
import event.processing.engine.impl.EsperEngineFactory;

public abstract class AbstractTestEP {

    protected EngineFactory factory;
    protected Engine engine;
    protected QueryTransformer queryTransformer;

    protected DataSource dataSource1, dataSource2, dataSource3;

    @Before
    public void before() {
        dataSource1 = generateTestDataSource("device1", "domain1");
        dataSource2 = generateTestDataSource("device2", "domain2");
        dataSource3 = generateTestDataSource("device3", "domain3");

        factory = new EsperEngineFactory();
        engine = factory.getEngine();
        queryTransformer = factory.getQueryTransformer();

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

    private DataSource generateTestDataSource(String deviceName, String domainName) {

        DeviceInformation device = new DeviceInformation();
        device.setName(deviceName);

        DomainInformation domain = new DomainInformation();
        domain.setName(domainName);

        return new DataSource(domain, device);
    }
}
