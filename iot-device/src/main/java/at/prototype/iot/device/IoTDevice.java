package at.prototype.iot.device;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;

import at.prototype.common.data.concrete.RegisterDevice;

import common.model.DevConfiguration;

@SpringBootApplication
public class IoTDevice 
{
	public static void main(String[] args) {
		SpringApplication.run(IoTDevice.class, args);
		run();
	}
	
	    private static void run()  {
	    	
	    	
	    	DevConfiguration devConfiguration = new DevConfiguration();
	    	devConfiguration.setName("First registered device");
	    	
	    	
	        RestTemplate restTemplate = new RestTemplate();

	        RegisterDevice[] forObject = restTemplate.getForObject("http://127.0.0.1:5000/registrations", RegisterDevice[].class);
	        
	        System.out.println(forObject[0].getName());
	        System.out.println(forObject[0].getAbitrary());
	        
	    }
}

