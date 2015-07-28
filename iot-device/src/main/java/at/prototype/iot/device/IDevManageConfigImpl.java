package at.prototype.iot.device;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import common.model.DevConfiguration;

@RestController
public class IDevManageConfigImpl implements IDevManageConfig {
	

	@RequestMapping(value = "/configuration", method=RequestMethod.POST)
	public void receiveConfiguration(DevConfiguration devConfiguration) {
			
	}
	
	@RequestMapping(value = "/configuration", method=RequestMethod.GET)
	public @ResponseBody DevConfiguration getConfiguration() {

		
		DevConfiguration deviceConfiguration = new DevConfiguration();
		deviceConfiguration.setName("Name of configuration");
		
		return deviceConfiguration;

	}
}
