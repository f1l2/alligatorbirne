package configuration.management.rest;

import java.util.List;

import common.data.DeviceInformation;
import common.data.MeasurementData;


/**
 * Interface definition
 * 
 * @author Manuel Filz
 *
 */
public interface CMgmtManageDevice { 
	
	public List<DeviceInformation> getAllDevices();
	
	public DeviceInformation getDevice(Long id);
	
	public String registerDevice(MeasurementData data);
	
	public void receiveHeartBeat(DeviceInformation deviceInformation);

}
