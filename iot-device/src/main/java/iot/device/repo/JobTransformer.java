package iot.device.repo;


import common.data.ConfigurationModification;
import common.transformer.Transformer;

public class JobTransformer extends Transformer<JobJPA, ConfigurationModification> {
	

	@Override
	public JobJPA toLocal(ConfigurationModification remote) {
		
		JobJPA job = new JobJPA();
		job.setEpId(remote.getEpId());
		job.setProperties(remote.getProperties());
		job.setEpUrl(remote.getEpUrl());
		
		return job;
	}
	
	@Override
	public ConfigurationModification toRemote(JobJPA local) {
		
		ConfigurationModification cm = new ConfigurationModification();
		cm.setEpId(local.getEpId());
		cm.setProperties(local.getProperties());
		cm.setEpUrl(local.getEpUrl());
		
		return null;
	}
}
