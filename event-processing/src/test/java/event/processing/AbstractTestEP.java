package event.processing;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import common.data.model.DeviceData;
import common.data.model.DeviceInformation;
import common.data.model.DomainInformation;
import event.processing.engine.Engine;
import event.processing.engine.EngineFactory;
import event.processing.engine.LanguageTransformer;
import event.processing.query.Query;
import event.processing.query.QueryFactory;
import event.processing.query.esper.TestListener;

public abstract class AbstractTestEP {

    @Autowired
    @Qualifier("esper")
    protected EngineFactory factory;

    @Autowired
    protected QueryFactory qf;

    protected Engine engine;

    protected LanguageTransformer eplTransformer;

    protected DeviceData dd1, dd2, dd3;

    protected TestListener testListener;

    protected static final int DEFAULT_DELAY_MS = 100;

    @Before
    public void before() {

        dd1 = generateTestDeviceData(1l, "device1", 1l, "domain1");
        dd2 = generateTestDeviceData(2l, "device2", 2l, "domain2");
        dd3 = generateTestDeviceData(3l, "device3", 1l, "domain1");

        engine = factory.getEngine();

        eplTransformer = factory.getTransformer();

        testListener = new TestListener();
    }

    @After
    public void after() {
        engine.unregisterAll();
    }

    protected void sendEventAndWait(DeviceData deviceData, long time) {
        engine.send(deviceData);
        delay(time);
    }

    protected void delay(long time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {

        }
    }

    protected DeviceData generateTestDeviceData(Long deviceId, String deviceName, Long domainId, String domainName) {

        DeviceInformation device = new DeviceInformation();
        device.setName(deviceName);
        device.setId(deviceId);

        DomainInformation domain = new DomainInformation();
        domain.setName(domainName);
        domain.setId(domainId);

        return new DeviceData(domain, device);
    }

    protected List<String> transformQueryString(String in, String name) throws IOException {

        Query query = qf.parse(in, name);

        return eplTransformer.transformQuery(query);

    }

    protected List<String> transformQueryString(List<String> ins, List<String> names) throws IOException {

        List<String> queries = new ArrayList<String>();

        for (int i = 0; ins.size() < i && names.size() < i; i++) {
            queries.addAll(transformQueryString(ins.get(i), names.get(i)));
        }
        return queries;
    }

    protected void sendEventAndWait(DeviceData[] deviceData, int[] expectedFiredEvents) {

        for (int i = 0; i < deviceData.length; i++) {

            sendEventAndWait(deviceData[i], DEFAULT_DELAY_MS);

            assertEquals(expectedFiredEvents[i], testListener.getFiredEvents());
        }

    }
}
