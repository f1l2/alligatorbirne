package event.processing.engine.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.espertech.esper.client.EventBean;
import com.espertech.esper.client.UpdateListener;

import event.processing.engine.EngineListener;

public class EsperEngineListener extends EngineListener implements UpdateListener {

    private List<EngineListener> listeners = new ArrayList<EngineListener>();

    private static final Logger logger = LoggerFactory.getLogger(EsperEngineListener.class);

    @Override
    public void update(EventBean[] newData, EventBean[] oldData) {
        logger.info("Event received: " + newData[0].getUnderlying());
        listeners.forEach(item -> item.update());
    }

    @Override
    public void update() {
        // DO NOTHING
    }

    public void addListener(EngineListener listener) {
        listeners.add(listener);
    }

}
