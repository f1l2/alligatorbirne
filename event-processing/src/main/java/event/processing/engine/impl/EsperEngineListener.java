package event.processing.engine.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.espertech.esper.client.EventBean;
import com.espertech.esper.client.UpdateListener;

import event.processing.engine.EngineListener;

@Component()
@Scope("prototype")
public class EsperEngineListener extends EngineListener implements UpdateListener {

    private List<EngineListener> ruleListeners = new ArrayList<EngineListener>();

    private static final Logger logger = LoggerFactory.getLogger(EsperEngineListener.class);

    @Override
    public void update(EventBean[] newData, EventBean[] oldData) {
        logger.info("Event received: " + newData[0].getUnderlying());

        ruleListeners.forEach(item -> item.trigger());
    }

    public void addRuleListener(EngineListener listener) {
        this.ruleListeners.add(listener);
    }

    @Override
    public void trigger() {
        // NOT USED
    }

}
