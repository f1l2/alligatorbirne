package configuration.management.rest;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import common.data.DeviceInformation;
import common.data.MeasurementData;
import common.data.MeasurementPoint;
import configuration.management.model.DeviceDataSourceJPA;
import configuration.management.model.DeviceJPA;
import configuration.management.repo.DeviceRepository;
import configuration.management.repo.DeviceTransformer;
import configuration.management.repo.MeasurementPointRepository;


@RestController
public class CMgmtManageDeviceImpl implements CMgmtManageDevice {

	final static Logger logger = Logger.getLogger(CMgmtManageDeviceImpl.class);
	
	@Autowired
	private DeviceRepository deviceRepository;
	
	@Autowired
	private MeasurementPointRepository measurementPointRepository;
	
	@Autowired
	private DeviceTransformer transformer;
	
	@Override
    @RequestMapping(value = "/registrations", method=RequestMethod.GET)
    public @ResponseBody List<DeviceInformation> getAllDevices() {
	
		logger.info("GET /registrations is invoked");
		
		List<DeviceInformation> devices = new ArrayList<DeviceInformation>();
		
		for (DeviceJPA device : deviceRepository.findAll()) {
			devices.add(transformer.toRemote(device));
		}
		return devices;
    }
	
    @Override
    @RequestMapping(value = "/registrations/{id}", method=RequestMethod.GET) 
    public DeviceInformation getDevice(@RequestParam(value = "id") Long id) {
    	
    	logger.info("GET /registrations/{id} is invoked");
		
    	DeviceJPA device = deviceRepository.findOne(id);	
    	return transformer.toRemote(device);
    }
	
    @Override
    @RequestMapping(value = "/registrations", method=RequestMethod.POST)
    public String registerDevice(@RequestBody MeasurementData data) {
    	
    	logger.info("POST /registrations is invoked");

    	DeviceJPA device = new DeviceJPA();
    	device.setDate(new Date());
    	device.setUrl("new URL");
    	device = deviceRepository.save(device);
    	
    	for (MeasurementPoint point : data.getMeasurementPoints()) {
    		
    		DeviceDataSourceJPA item = new DeviceDataSourceJPA();
    		item.setDeviceId(device.getId());
    		item.setDeviceInformation(point.getDeviceInformation().getName());
    		item.setDomain(point.getDomain().getName());
    		
    		measurementPointRepository.save(item);
    	}
    	
    	return "OK";
    }

	@Override
    @RequestMapping(value = "/registrations/{id}", method=RequestMethod.PUT) 
	public void receiveHeartBeat(DeviceInformation deviceInformation) {

		logger.info("PUT /registrations{id} is invoked");
		
    	DeviceJPA device = deviceRepository.findByName(deviceInformation.getName());
    	if (device != null) {
    		device.setDate(new Date());
    		deviceRepository.save(device);
    	}
		
	}

}
