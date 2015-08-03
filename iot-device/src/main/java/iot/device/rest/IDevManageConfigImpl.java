package iot.device.rest;

import java.util.ArrayList;
import java.util.List;

import iot.device.repo.JobJPA;
import iot.device.repo.JobRepository;
import iot.device.repo.JobTransformer;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import common.data.ConfigurationModification;


@RestController
public class IDevManageConfigImpl implements IDevManageConfig {
	
	final static Logger logger = Logger.getLogger(IDevManageConfigImpl.class);

	@Autowired
	private JobRepository repository;
	
	@Autowired
	private JobTransformer transformer;
	
	@RequestMapping(value = "/configurations", method=RequestMethod.GET)
	public List<ConfigurationModification> getAllConfiguration() {
		
		logger.info("GET /configurations is invoked");
		
		List<JobJPA> jobsJPA = new ArrayList<JobJPA>();
		for (JobJPA jobJPA : repository.findAll()) {
			jobsJPA.add(jobJPA);
		}
		
		return transformer.toRemote(jobsJPA);
	}
	
	@RequestMapping(value = "/configurations/{id}", method=RequestMethod.GET) 
	public JobJPA getConfigurationByEP(@RequestParam(value = "id") Long id) {
		
		logger.info("GET /configurations/{id} is invoked");

		return repository.findOne(id);

	}
	
	@RequestMapping(value = "/configurations", method=RequestMethod.POST)
	public void setConfiguration(ConfigurationModification configurationModification) {
		
		logger.info("POST /configurations is invoked");
		
		JobJPA local = transformer.toLocal(configurationModification);
		repository.save(local);
		
	}
}
