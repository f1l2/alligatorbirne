package event.processing.query.esper;

import com.espertech.esper.client.EventBean;
import com.espertech.esper.client.UpdateListener;

import event.processing.engine.EngineListener;

public class TestListener extends EngineListener implements UpdateListener {

    int cntFiredEvents = 0;

    @Override
    public void update(EventBean[] newEvents, EventBean[] oldEvents) {
        cntFiredEvents++;
    }

    @Override
    public void trigger() {

    }

    public int getFiredEvents() {
        return cntFiredEvents;
    }
}
