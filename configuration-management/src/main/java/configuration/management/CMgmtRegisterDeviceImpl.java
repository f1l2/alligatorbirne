package configuration.management;

import java.util.ArrayList;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import at.prototype.common.data.concrete.RegisterDevice;

import common.data.DeviceInformation;

import configuration.management.model.Device;
import configuration.management.repo.DeviceRepository;


@RestController
public class CMgmtRegisterDeviceImpl implements CMgmtRegisterDevice {

	@Autowired
	private DeviceRepository repository;
	
	@Override
    @RequestMapping(value = "/registrations", method=RequestMethod.GET)
    public @ResponseBody ArrayList<RegisterDevice> getAllDevices() {
    
		Iterable<Device> devices = repository.findAll();
		
		ArrayList<RegisterDevice> arrayList = new ArrayList<RegisterDevice>();
		
		for (Device device : devices) {
			
			RegisterDevice deviceInformation = new RegisterDevice();
			deviceInformation.setName(device.getName());
			arrayList.add(deviceInformation);
		}
		return arrayList;
		

    }
	
    @Override
    @RequestMapping(value = "/registrations/{id}", method=RequestMethod.GET) 
    public DeviceInformation getDevice(@RequestParam(value = "id") Long id, @RequestBody DeviceInformation deviceInformation) {
    	
    	
    	Device device = repository.findOne(id);
    	
    	RegisterDevice registeredDevice = new RegisterDevice();
		deviceInformation.setName(device.getName());
		
    	return registeredDevice;
    }
	
    @Override
    @RequestMapping(value = "/registrations", method=RequestMethod.POST)
    public void registerDevice(@RequestBody DeviceInformation deviceInformation) {
    	
    	Device device = repository.findByName(deviceInformation.getName());
    	if (null == device) {
  
    	}
    	
    	device = new Device();
    	device.setName(deviceInformation.getName());
    	
    	device = repository.save(device);
    
    }

	@Override
    @RequestMapping(value = "/registrations/{id}", method=RequestMethod.PUT) 
	public void sendHeartBeat(DeviceInformation deviceInformation) {
		
    	Device device = repository.findByName(deviceInformation.getName());
    	if (device != null) {
    		device.setDate(new Date());
    		repository.save(device);
    	}
		
	}

}
