package at.prototype.configuration.management;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
public class ApplicationStartup implements ApplicationListener<ContextRefreshedEvent> {
	
	final static Logger logger = Logger.getLogger(ApplicationStartup.class);
	
  @Autowired
  DeviceRepository repository;
	
  @Override
  public void onApplicationEvent(final ContextRefreshedEvent event) {
 
	  
	  Device device = new Device();
	  
	  for (int i = 0; i<10; i++) {
		  device = new Device();
		  device.setName("Device" + (i+1));
		  this.repository.save(device);
	  }
	  	  
	  Iterable<Device> devices = repository.findAll();
	  for (Device d : devices) {	  
		  logger.info(d);
	  }
		
	  
    return;
  }
 
} 
