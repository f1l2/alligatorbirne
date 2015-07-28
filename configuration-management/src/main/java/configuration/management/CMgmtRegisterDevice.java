package configuration.management;

import java.util.List;

import common.data.DeviceInformation;


/**
 * Interface definition
 * 
 * @author Manuel Filz
 *
 */
public interface CMgmtRegisterDevice { 
	
	public List<DeviceInformation> getAllDevices();

	public DeviceInformation getDevice(Long id, DeviceInformation deviceInformation);
	
	public void registerDevice(DeviceInformation deviceInformation);

	public void sendHeartBeat(DeviceInformation deviceInformation);

}
