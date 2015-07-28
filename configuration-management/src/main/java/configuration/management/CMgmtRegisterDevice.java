package configuration.management;

import java.util.ArrayList;

import at.prototype.common.data.concrete.RegisterDevice;

import common.data.DeviceInformation;


/**
 * Interface definition
 * 
 * @author Manuel Filz
 *
 */
public interface CMgmtRegisterDevice { 
	
	public ArrayList<RegisterDevice> getAllDevices();

	public DeviceInformation getDevice(Long id, DeviceInformation deviceInformation);
	
	public void registerDevice(DeviceInformation deviceInformation);

	public void sendHeartBeat(DeviceInformation deviceInformation);

}
