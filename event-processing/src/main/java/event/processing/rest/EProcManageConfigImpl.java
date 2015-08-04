package event.processing.rest;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import common.data.DeviceInformation;
import common.data.DomainInformation;


@RestController
public class EProcManageConfigImpl implements EProcManageConfig {
	
	@RequestMapping(value = "/send", method=RequestMethod.POST)
	@Override
	public void sendConfig(@RequestBody DeviceInformation deviceInformation, @RequestBody DomainInformation domain)  {
		
	}
	
	

}
