package event.processing.engine;

import common.data.DeviceInformation;
import common.data.DomainInformation;

public interface EProcManageConfig {
	
	public void sendConfig(DeviceInformation deviceInformation, DomainInformation domain);
	
}
