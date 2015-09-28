package event.processing.engine.impl;

import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

import common.data.DataModel;
import common.data.DataSource;
import event.processing.engine.ENGINE_TYPE;
import event.processing.engine.Engine;
import event.processing.engine.EngineListener;

@Component
public class EsperEngine extends Engine {

    private static final Logger logger = LoggerFactory.getLogger(EsperEngine.class);

    private static EPRuntime EP_RUNTIME = null;

    private static EPServiceProvider EP_SP = null;

    public EsperEngine() {
        super(ENGINE_TYPE.ESPER);
    }

    @Override
    public void initialize() {

        Configuration cepConfig = new Configuration();
        cepConfig.addEventType("DataSource", DataSource.class.getName());

        ClassPathScanningCandidateComponentProvider provider = new ClassPathScanningCandidateComponentProvider(true);
        provider.addIncludeFilter(new AssignableTypeFilter(DataModel.class));

        cepConfig.addEventType("DataSource", DataSource.class);

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

    }

    @Override
    public void registerQuery(String query, EngineListener listener) {

        EPAdministrator cepAdm = EP_SP.getEPAdministrator();
        EPStatement cepStatement = cepAdm.createEPL(query);

        cepStatement.addListener((UpdateListener) listener);
    }

    @Override
    public void sendEvent(DataSource dataSource) {
        if (null != EP_RUNTIME) {
            EP_RUNTIME.sendEvent(dataSource);
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
