package event.processing.engine;

import common.data.DeviceInformation;
import common.data.Domain;

public interface EProcManageConfig {
	
	public void sendConfig(DeviceInformation deviceInformation, Domain domain);
	
}
