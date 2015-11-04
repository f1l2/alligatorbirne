package event.processing.engine.impl;

import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.core.type.filter.AssignableTypeFilter;
import org.springframework.stereotype.Component;

import com.espertech.esper.client.Configuration;
import com.espertech.esper.client.EPAdministrator;
import com.espertech.esper.client.EPRuntime;
import com.espertech.esper.client.EPServiceProvider;
import com.espertech.esper.client.EPServiceProviderManager;
import com.espertech.esper.client.EPStatement;
import com.espertech.esper.client.UpdateListener;

import common.data.DataSource;
import common.data.model.DataModel;
import common.data.model.DeviceData;
import event.processing.engine.ENGINE_TYPE;
import event.processing.engine.Engine;
import event.processing.engine.EngineListener;
import event.processing.status.STATUS_TYPE;
import event.processing.status.Status;

@Component
public class EsperEngine extends Engine {

    private static final Logger logger = LoggerFactory.getLogger(EsperEngine.class);

    private static EPRuntime EP_RUNTIME = null;

    private static EPServiceProvider EP_SP = null;

    private static EPAdministrator EP_ADMIN = null;

    @Autowired
    private Status status;

    public EsperEngine() {
        super(ENGINE_TYPE.ESPER);
    }

    @Override
    public void initialize() {

        Configuration cepConfig = new Configuration();
        cepConfig.addEventType("DataSource", DataSource.class);
        cepConfig.addEventType("Event", Event.class);

        ClassPathScanningCandidateComponentProvider provider = new ClassPathScanningCandidateComponentProvider(true);
        provider.addIncludeFilter(new AssignableTypeFilter(DataModel.class));

        Set<BeanDefinition> components = provider.findCandidateComponents("common/data");
        for (BeanDefinition component : components) {
            try {
                Class<?> cls = Class.forName(component.getBeanClassName());
                cepConfig.addEventType(cls.getSimpleName(), cls);
                logger.info("Adding event type. EventTypeName: {}, Class: {}", cls.getSimpleName(), cls.getName());
            } catch (Exception e) {
                logger.error("Error adding event type.");
            }
        }

        EP_SP = EPServiceProviderManager.getProvider(getType().getDescription(), cepConfig);

        EP_RUNTIME = EP_SP.getEPRuntime();

        EP_ADMIN = EP_SP.getEPAdministrator();

        if (null == EP_SP) {
            logger.error("Event processing: Service provider wasn't initialized properly");
            status.setCurrent(STATUS_TYPE.ERROR);
        }

        if (null == EP_RUNTIME) {
            logger.error("Event processing: Runtime wasn't initialized properly");
            status.setCurrent(STATUS_TYPE.ERROR);
        }

        if (null == EP_ADMIN) {
            logger.error("Event processing: Administrator wasn't initialized properly");
            status.setCurrent(STATUS_TYPE.ERROR);
        }

    }

    @Override
    public void register(String epl, EngineListener listener) {

        EPStatement cepStatement = EP_ADMIN.createEPL(epl, epl);
        cepStatement.addListener((UpdateListener) listener);
    }

    @Override
    public void register(List<String> query, EngineListener listener) {
        for (int i = 0; i < (query.size() - 1); i++) {
            register(query.get(i));
        }
        register(query.get(query.size() - 1), listener);
    }

    private void register(String epl) {
        EP_ADMIN.createEPL(epl, epl);
    }

    @Override
    public void send(DeviceData deviceData) {
        EP_RUNTIME.sendEvent(deviceData);
    }

    @Override
    public void deregister(List<String> query) {
        query.forEach(item -> deregister(item));
    }

    @Override
    public void deregister(String epl) {
        EP_ADMIN.getStatement(epl).destroy();
    }

    @Override
    public void deregisterAll() {
        EP_ADMIN.destroyAllStatements();
    }

    public EPRuntime getCepRT() {
        return EP_RUNTIME;
    }

    public EPServiceProvider getCep() {
        return EP_SP;
    }
}
