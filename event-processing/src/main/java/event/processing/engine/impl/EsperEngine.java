package event.processing.engine.impl;

import org.apache.log4j.Logger;

import com.espertech.esper.client.Configuration;
import com.espertech.esper.client.EPAdministrator;
import com.espertech.esper.client.EPRuntime;
import com.espertech.esper.client.EPServiceProvider;
import com.espertech.esper.client.EPServiceProviderManager;
import com.espertech.esper.client.EPStatement;
import common.data.DeviceInformation;

import event.processing.engine.ENGINE_TYPE;
import event.processing.engine.Engine;
import event.processing.query.Query;

public class EsperEngine extends Engine {

    final static Logger logger = Logger.getLogger(EsperEngine.class);

    private static EPRuntime EP_RUNTIME = null;

    private static EPServiceProvider EP_SP = null;

    public EsperEngine() {
        super(ENGINE_TYPE.ESPER);
    }

    @Override
    public void initialize() {

        Configuration cepConfig = new Configuration();

        cepConfig.addEventType("DeviceInformation", DeviceInformation.class.getName());

        EP_SP = EPServiceProviderManager.getProvider(getType().getDescription(), cepConfig);

        EP_RUNTIME = EP_SP.getEPRuntime();

    }

    @Override
    public void registerQuery(Query query) {

    }

    @Override
    public void registerQuery(String query) {

        EPAdministrator cepAdm = EP_SP.getEPAdministrator();
        EPStatement cepStatement = cepAdm.createEPL(query);

        cepStatement.addListener(new CEPListener());
    }

    @Override
    public void sendEvent(DeviceInformation deviceInformation) {
        if (null != EP_RUNTIME) {
            EP_RUNTIME.sendEvent(deviceInformation);
        } else {
            logger.error("Event couldn't be proccesed due engine wasn't initialized properly");
        }
    }

    @Override
    public void unregisterQuery(String query) {

    }

    public EPRuntime getCepRT() {
        return EP_RUNTIME;
    }

    public EPServiceProvider getCep() {
        return EP_SP;
    }
}
