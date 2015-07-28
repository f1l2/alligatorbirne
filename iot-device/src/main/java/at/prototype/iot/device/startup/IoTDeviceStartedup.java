package at.prototype.iot.device.startup;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
public class IoTDeviceStartedup implements ApplicationListener<ContextRefreshedEvent> {
	
	final static Logger logger = Logger.getLogger(IoTDeviceStartedup.class);
	
  @Override
  public void onApplicationEvent(final ContextRefreshedEvent event) {
 

	  
  }
} 
