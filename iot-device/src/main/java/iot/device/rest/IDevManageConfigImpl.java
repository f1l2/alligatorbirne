package iot.device.rest;

import iot.device.repo.DeliveryTaskRO;
import iot.device.repo.DeliveryTask;
import iot.device.repo.DeliveryTaskRepository;
import iot.device.repo.DeliveryTaskTransformer;

import java.util.List;
import java.util.Random;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import common.data.ConfigurationModification;
import common.rest.RESOURCE_NAMING;
import common.rest.UtilsResource;

@RestController
public class IDevManageConfigImpl implements IDevManageConfig {

    final static Logger logger = Logger.getLogger(IDevManageConfigImpl.class);

    @Autowired
    private DeliveryTaskRepository repository;

    @Autowired
    private DeliveryTaskTransformer transformer;

    @Autowired
    private ThreadPoolTaskExecutor taskExecutor;

    @Override
    @RequestMapping(value = "/configurations", method = RequestMethod.GET)
    public List<ConfigurationModification> getAllConfiguration() {

        logger.info(UtilsResource.getLogMessage(RESOURCE_NAMING.IDEV_GET_ALL_CONFIGURATION));

        return transformer.toRemote(repository.findAll());
    }

    @Override
    @RequestMapping(value = "/configurations/{id}", method = RequestMethod.GET)
    public DeliveryTaskRO getConfigurationByEventProcessingId(@RequestParam(value = "id") Long id) {

        logger.info(UtilsResource.getLogMessage(RESOURCE_NAMING.IDEV_GET_CONFIGURATION_BY_EP));

        return repository.findOne(id);

    }

    @Override
    @RequestMapping(value = "/configurations", method = RequestMethod.POST)
    public void setConfiguration(@RequestBody ConfigurationModification configurationModification) {

        logger.info(UtilsResource.getLogMessage(RESOURCE_NAMING.IDEV_SET_CONFIGURATION));

        DeliveryTaskRO local = transformer.toLocal(configurationModification);
        repository.save(local);

        DeliveryTask job = new DeliveryTask(local);

        taskExecutor.execute(job);

    }

    @Override
    @RequestMapping(value = "/configurationtest", method = RequestMethod.GET)
    public void setConfiguration1() {

        logger.info("POST /configurations is invoked");

        Random random = new Random();
        random.nextLong();

        DeliveryTaskRO local = new DeliveryTaskRO();
        local.setEventProcessingId(random.nextLong());
        local.setEventProcessingUrl("Urls");

        DeliveryTask job = new DeliveryTask(local);

        taskExecutor.execute(job);

    }
}
