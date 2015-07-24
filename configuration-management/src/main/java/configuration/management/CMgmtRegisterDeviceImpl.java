package configuration.management;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import at.prototype.common.data.DeviceInformation;
import configuration.management.model.Device;
import configuration.management.repo.DeviceRepository;


@RestController
public class CMgmtRegisterDeviceImpl implements CMgmtRegisterDevice {

	@Autowired
	private DeviceRepository repository;
	
    @RequestMapping(value = "/register", method=RequestMethod.POST)
    public void register(@RequestBody DeviceInformation deviceInformation) {
    	
    	Device device = repository.findByName(deviceInformation.getName());
    	if (null == device) {
  
    	}
    	
    	device = new Device();
    	device.setName(deviceInformation.getName());
    	
    	device = repository.save(device);
    

    }
    
    @RequestMapping(value = "register/{id}", method=RequestMethod.PUT) 
    public void register(@RequestParam(value = "id") Long id, @RequestBody DeviceInformation deviceInformation) {
    	
    	Device device = repository.findOne(id);
    	
    	if (null == device) {
    		
    	}
    	
    	device.setName(deviceInformation.getName());
    	
    	device = repository.save(device);
    }
    
	@Override
	public void heartBeat(DeviceInformation deviceInformation) {
		// TODO Auto-generated method stub
		
	}
}
