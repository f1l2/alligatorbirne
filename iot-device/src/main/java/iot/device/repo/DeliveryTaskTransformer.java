package iot.device.repo;


import org.springframework.stereotype.Component;

import common.data.ConfigurationModification;
import common.transformer.Transformer;

@Component
public class DeliveryTaskTransformer extends Transformer<DeliveryTaskRO, ConfigurationModification> {
	

	@Override
	public DeliveryTaskRO toLocal(ConfigurationModification remote) {
		
		DeliveryTaskRO deliveryTask = new DeliveryTaskRO();
		deliveryTask.setEventProcessingId(remote.getEventProcessingId());
		deliveryTask.setProperties(remote.getProperties());
		deliveryTask.setEventProcessingUrl(remote.getEventProcessingUrl());
		
		return deliveryTask;
	}
	
	@Override
	public ConfigurationModification toRemote(DeliveryTaskRO local) {
		
		ConfigurationModification cm = new ConfigurationModification();
		cm.setEventProcessingId(local.getEventProcessingId());
		cm.setProperties(local.getProperties());
		cm.setEpUrl(local.getEventProcessingUrl());
		
		return null;
	}
}
