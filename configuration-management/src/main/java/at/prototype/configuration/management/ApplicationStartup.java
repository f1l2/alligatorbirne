package at.prototype.configuration.management;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
public class ApplicationStartup implements ApplicationListener<ContextRefreshedEvent> {
	
	@Autowired
	DeviceRepository repository;
	
  @Override
  public void onApplicationEvent(final ContextRefreshedEvent event) {
 
	  Device device1 = new Device();
	  device1.setName("device1");
	  
	  Device device2 = new Device();
	  device2.setName("device1");
	  
	  Device device3 = new Device();
	  device3.setName("device1");
	  
	  Device device4 = new Device();
	  device4.setName("device1");
	  
	  
	  repository.save(device1);
	  repository.save(device2);

	  repository.save(device3);
	  repository.save(device4);
	  
	  Iterable<Device> devices = repository.findAll();
	  
	  for (Device d : devices) {
		  
		  System.out.println(d);
	  }
		
	  
    return;
  }
 
} 
