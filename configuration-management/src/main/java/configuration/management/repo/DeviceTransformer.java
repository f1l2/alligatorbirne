package configuration.management.repo;

import org.springframework.stereotype.Component;

import common.data.DeviceInformation;
import common.transformer.Transformer;

import configuration.management.model.DeviceJPA;


@Component
public class DeviceTransformer extends Transformer<DeviceJPA, DeviceInformation> {

	@Override
	public DeviceJPA toLocal(DeviceInformation remote) {
		
		if (null == remote) {
			return null;
		}
		
		DeviceJPA device = new DeviceJPA();
		device.setId(remote.getId());
		device.setName(remote.getName());
		
		return device;
	}

	@Override
	public DeviceInformation toRemote(DeviceJPA local) {
		
		if (null == local) {
			return null;
		}
		
		DeviceInformation deviceInformation = new DeviceInformation();
		deviceInformation.setId(local.getId());
		deviceInformation.setName(local.getName());
		
		return deviceInformation;
	}

	

	
	

}
