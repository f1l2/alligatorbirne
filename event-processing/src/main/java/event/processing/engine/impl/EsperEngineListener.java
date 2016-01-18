package event.processing.engine.impl;

import java.util.ArrayList;
import java.util.List;

import com.espertech.esper.client.EventBean;
import com.espertech.esper.client.UpdateListener;

import common.lang.rule.GenericListener;

public class EsperEngineListener implements GenericListener, UpdateListener {

    private List<GenericListener> ruleListeners = new ArrayList<GenericListener>();

    // private static final Logger logger = LoggerFactory.getLogger(EsperEngineListener.class);

    @Override
    public void update(EventBean[] newData, EventBean[] oldData) {

        ruleListeners.forEach(item -> item.trigger());
    }

    public void addRuleListener(GenericListener listener) {
        this.ruleListeners.add(listener);
    }

    @Override
    public void trigger() {
        // NOT IMPLEMENTED
    }

}
