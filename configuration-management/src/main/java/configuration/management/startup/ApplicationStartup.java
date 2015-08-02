package configuration.management.startup;

import java.util.Date;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import configuration.management.model.DeviceJPA;
import configuration.management.repo.DeviceRepository;

@Component
public class ApplicationStartup implements ApplicationListener<ContextRefreshedEvent> {
	
	final static Logger logger = Logger.getLogger(ApplicationStartup.class);
	
  @Autowired
  DeviceRepository repository;
	
  @Override
  public void onApplicationEvent(final ContextRefreshedEvent event) {
 
	  
	  DeviceJPA device = new DeviceJPA();
	  
	  for (int i = 0; i<10; i++) {
		  device = new DeviceJPA();
		  device.setName("Device" + (i+1));
		  device.setDate(new Date());
		  this.repository.save(device);
	  }
	  	  
	  Iterable<DeviceJPA> devices = repository.findAll();
	  for (DeviceJPA d : devices) {	  
		  logger.info(d);
	  }
    return;
  }
 
} 
