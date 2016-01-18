package event.processing.query.esper;

import com.espertech.esper.client.EventBean;
import com.espertech.esper.client.UpdateListener;

import common.lang.rule.GenericListener;

public class TestListener implements GenericListener, UpdateListener {

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
