package event.processing.engine.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.espertech.esper.client.EventBean;
import com.espertech.esper.client.UpdateListener;

import event.processing.engine.EngineListener;

@Component
public class EsperEngineListener extends EngineListener implements UpdateListener {

    private static final Logger logger = LoggerFactory.getLogger(EsperEngineListener.class);

    @Override
    public void update(EventBean[] newData, EventBean[] oldData) {
        logger.info("Event received: " + newData[0].getUnderlying());

        System.out.println("Event received." + newData[0].getUnderlying());

    }

    @Override
    public void update() {
        // TODO Auto-generated method stub

    }
}
