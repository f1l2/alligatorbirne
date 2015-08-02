package iot.device.rest;

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
