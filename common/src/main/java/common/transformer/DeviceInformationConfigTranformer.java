package common.transformer;


import common.data.DeviceInformation;
import common.data.configuration.DeviceInformationConfig;


public class DeviceInformationConfigTranformer extends  Transformer<DeviceInformationConfig, DeviceInformation> {

		@Override
		public DeviceInformationConfig toLocal(DeviceInformation remote) {
			DeviceInformationConfig deviceInformationConfig = new DeviceInformationConfig();
			deviceInformationConfig.setName(remote.getName());
			return deviceInformationConfig;
		}

		@Override
		public DeviceInformation toRemote(DeviceInformationConfig local) {
			DeviceInformation deviceInformation = new DeviceInformation();
			deviceInformation.setName(local.getName());
			return deviceInformation;
		}

	}
