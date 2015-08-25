package event.processing.engine.impl;

import org.apache.log4j.Logger;

import com.espertech.esper.client.EventBean;
import com.espertech.esper.client.UpdateListener;

import event.processing.engine.EngineListener;

public class EsperEngineListener extends EngineListener implements UpdateListener {

    final static Logger logger = Logger.getLogger(EsperEngineListener.class);

    @Override
    public void update(EventBean[] newData, EventBean[] oldData) {
        logger.info("Event received: " + newData[0].getUnderlying());

        System.out.println("Event received." + newData[0].getUnderlying());

        if (newData[0].getUnderlying() instanceof DeviceInformationAgg) {

            System.out.println("HERE");

            System.out.println(((DeviceInformationAgg) newData[0].getUnderlying()).getValue());
        }

    }

    @Override
    public void update() {
        // TODO Auto-generated method stub

    }
}
