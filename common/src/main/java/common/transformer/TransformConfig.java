package common.transformer;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import common.data.DeviceInformation;
import common.data.Domain;
import common.data.MeasurementData;
import common.data.MeasurementPoint;
import common.data.configuration.DeviceInformationConfig;
import common.data.configuration.DomainConfig;
import common.data.configuration.MeasurementPointConfig;

@Component
public class TransformConfig extends Transformer<MeasurementPointConfig, MeasurementPoint> {

	@Autowired
	private TransformerDeviceInformationConfig transformerDeviceInformation;
	
	@Autowired
	private TransformerDomainConfig transformerDomain;
	
	
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
		
		Domain remoteDomain = transformerDomain.toRemote(local.getDomainConfig());
		
		measurementPoint.setDomain(remoteDomain);
		
		return measurementPoint;
	}


}
