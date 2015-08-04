package event.processing.engine.impl;

import org.apache.log4j.Logger;

import com.espertech.esper.client.EventBean;
import com.espertech.esper.client.UpdateListener;

import event.processing.startup.Startup;

public class CEPListener implements UpdateListener {
	
	final static Logger logger = Logger.getLogger(Startup.class);

	public void update(EventBean[] newData, EventBean[] oldData) {
		logger.info("Event received: "
	                            + newData[0].getUnderlying());
	}
}
