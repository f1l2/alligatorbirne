package at.prototype.iot.device;

import common.model.DevConfiguration;

/**
 * Interface definition
 * 
 * @author Manuel Filz
 *
 */
public interface IDevManageConfig {
	
	public void receiveConfiguration(DevConfiguration devConfiguration);
	
	public DevConfiguration getConfiguration();
}
