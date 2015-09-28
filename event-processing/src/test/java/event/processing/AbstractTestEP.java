package event.processing;

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

    protected DataSource dataSource1, dataSource2, dataSource3;

    @Before
    public void before() {
        dataSource1 = generateTestDataSource("device1", "domain1");
        dataSource2 = generateTestDataSource("device2", "domain2");
        dataSource3 = generateTestDataSource("device3", "domain3");

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

    private DataSource generateTestDataSource(String deviceName, String domainName) {

        DeviceInformation device = new DeviceInformation();
        device.setName(deviceName);

        DomainInformation domain = new DomainInformation();
        domain.setName(domainName);

        return new DataSource(domain, device);
    }
}
