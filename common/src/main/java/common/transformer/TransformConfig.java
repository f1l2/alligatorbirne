package common.transformer;

import common.data.DeviceInformation;
import common.data.DomainInformation;
import common.data.MeasurementPoint;
import common.data.configuration.DeviceInformationConfig;
import common.data.configuration.DomainConfig;
import common.data.configuration.MeasurementPointConfig;

public class TransformConfig extends Transformer<MeasurementPointConfig, MeasurementPoint> {

	private TransformerDeviceInformationConfig transformerDeviceInformation = new TransformerDeviceInformationConfig();
	
	private TransformerDomainConfig transformerDomain = new TransformerDomainConfig();
	
	
	@Override
	public MeasurementPointConfig toLocal(MeasurementPoint remote) {
		
		MeasurementPointConfig measurementPointConfig = new MeasurementPointConfig();
		
		DeviceInformationConfig localDev = transformerDeviceInformation.toLocal(remote.getDeviceInformation());
		
		measurementPointConfig.setDeviceInformationConfig(localDev);

		DomainConfig localDomain = transformerDomain.toLocal(remote.getDomain());
		
		measurementPointConfig.setDomainConfig(localDomain);
		
		return measurementPointConfig;
	}

	@Override
	public MeasurementPoint toRemote(MeasurementPointConfig local) {

		MeasurementPoint measurementPoint = new MeasurementPoint();
		
		DeviceInformation remoteDev = transformerDeviceInformation.toRemote(local.getDeviceInformationConfig());
		
		measurementPoint.setDeviceInformation(remoteDev);
		
		DomainInformation remoteDomain = transformerDomain.toRemote(local.getDomainConfig());
		
		measurementPoint.setDomain(remoteDomain);
		
		return measurementPoint;
	}


}
