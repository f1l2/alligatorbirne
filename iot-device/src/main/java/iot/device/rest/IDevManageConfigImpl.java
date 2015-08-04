package iot.device.rest;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import iot.device.repo.Job;
import iot.device.repo.JobJPA;
import iot.device.repo.JobRepository;
import iot.device.repo.JobTransformer;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.bind.annotation.RequestBody;
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
	
	@Autowired
	private ThreadPoolTaskExecutor taskExecutor;
	
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
	public void setConfiguration(@RequestBody ConfigurationModification configurationModification) {
		
		logger.info("POST /configurations is invoked");
		
		JobJPA local = transformer.toLocal(configurationModification);
		repository.save(local);
		
		Job job = new Job(local);
		
		taskExecutor.execute(job);
		
		
	}
	
	@RequestMapping(value = "/configurationtest", method=RequestMethod.GET)
	public void setConfiguration1() {
		
		logger.info("POST /configurations is invoked");
		
		Random random = new Random();
		random.nextLong();
		
		JobJPA local = new JobJPA();
		local.setEpId(random.nextLong());
		local.setEpUrl("Urls");

		
		Job job = new Job(local);
		
		taskExecutor.execute(job);
		
		
	}
}
