package configuration.management;

import at.prototype.common.data.DeviceInformation;


/**
 * Interface definition
 * 
 * @author Manuel Filz
 *
 */
public interface CMgmtRegisterDevice { 
	
	/**
	 * @param deviceInformation
	 */
	public void register(DeviceInformation deviceInformation);
	
	public void heartBeat(DeviceInformation deviceInformation);

}
