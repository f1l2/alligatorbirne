package configuration.management.rest;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import common.data.ConfigurationModification;
import common.data.Connection;
import common.data.DataSource;
import common.data.DataSources;
import common.rest.RESOURCE_NAMING;
import common.rest.UtilsResource;
import common.transformer.Transformer;
import configuration.management.model.EventProcessingDataSourceRO;
import configuration.management.model.EventProcessingRO;
import configuration.management.model.IoTDeviceDataSourceRO;
import configuration.management.model.IoTDeviceRO;
import configuration.management.repo.EventProcessingDataSourceRepository;
import configuration.management.repo.EventProcessingRepository;
import configuration.management.repo.EventProcessingTransformer;
import configuration.management.repo.IoTDeviceDataSourceRepository;
import configuration.management.repo.IoTDeviceRepository;

@RestController
public class CMgmtManageEventProcessingImpl implements CMgmtManageEventProcessing {

    final static Logger logger = LoggerFactory.getLogger(CMgmtManageEventProcessingImpl.class);

    @Autowired
    private EventProcessingRepository eventProcessingRepo;

    @Autowired
    private EventProcessingDataSourceRepository eventProcessingDataSourceRepo;

    @Autowired
    private IoTDeviceRepository deviceRepository;

    @Autowired
    private IoTDeviceDataSourceRepository deviceDataSourceRepository;

    @Autowired
    private EventProcessingTransformer transformer;

    @Override
    @RequestMapping(value = "/registrations/eventprocessing", method = RequestMethod.GET)
    public @ResponseBody List<Connection> getAll() {

        logger.info(UtilsResource.getLogMessage(RESOURCE_NAMING.CMGMT_GET_ALL_EVENT_PROCESSING));
        return transformer.toRemote(Transformer.makeCollection(eventProcessingRepo.findAll()));
    }

    @Override
    @RequestMapping(value = "/registrations/eventprocessing", method = RequestMethod.POST)
    public Connection register(@RequestBody Connection connection) {

        logger.info(UtilsResource.getLogMessage(RESOURCE_NAMING.CMGMT_REGISTER_EVENT_PROCESSING));

        EventProcessingRO item = new EventProcessingRO();
        item.setUrl(connection.getUrl());
        item = eventProcessingRepo.save(item);

        connection.setId(item.getId());

        return connection;
    }

    @Override
    @RequestMapping(value = "/registrations/eventprocessing/{id}", method = RequestMethod.PUT)
    public void heartBeat(@PathVariable(value = "id") Long id) {

        logger.info(UtilsResource.getLogMessage(RESOURCE_NAMING.CMGMT_HEART_BEAT_EVENT_PROCESSING));

        EventProcessingRO item = eventProcessingRepo.findOne(id);
        if (item != null) {
            eventProcessingRepo.save(item);
            // TODO
        }
    }

    @Override
    @RequestMapping(value = "/delegation/{id}", method = RequestMethod.POST)
    public void delegate(@PathVariable(value = "id") Long id, @RequestBody DataSources data) {

        logger.info(UtilsResource.getLogMessage(RESOURCE_NAMING.CMGMT_DELEGATION));

        EventProcessingRO ep = eventProcessingRepo.findOne(id);

        for (DataSource point : data.getDataSources()) {

            EventProcessingDataSourceRO item = new EventProcessingDataSourceRO();
            // TODO Manuel
            // item.setEventProcessingId(id);
            item.setDomain(point.getDomain().getName());
            item.setDevice(point.getDevice().getName());

            eventProcessingDataSourceRepo.save(item);
        }

        logger.info("Get device to notify ...");

        Set<IoTDeviceDataSourceRO> devicesToNotify = getDevicesToNotify(ep);

        logger.info("Number of devices to notify: " + devicesToNotify.size());

        logger.info("Notify devices ... ");

        notifyDevices(devicesToNotify, ep);

    }

    private void notifyDevices(Set<IoTDeviceDataSourceRO> devicesToNotify, EventProcessingRO ep) {

        for (IoTDeviceDataSourceRO deviceJPA : devicesToNotify) {

            ConfigurationModification cm = new ConfigurationModification();
            cm.setEventProcessingId(ep.getId());
            cm.setEpUrl(ep.getUrl());

            IoTDeviceRO device = deviceRepository.findOne(deviceJPA.getIoTDevice().getId());

            String url = UtilsResource.getUrl(RESOURCE_NAMING.IDEV_SET_CONFIGURATION, device.getUrl());

            try {
                RestTemplate restTemplate = new RestTemplate();
                ResponseEntity<String> response = restTemplate.postForEntity(url, cm, String.class);
                logger.info("Device notification - " + url + " Status: " + response.getStatusCode() + " Response body: " + response.getBody());
            } catch (Exception ex) {
                // TODO
                logger.error("Register error.");
            }

        }

    }

    private Set<IoTDeviceDataSourceRO> getDevicesToNotify(EventProcessingRO ep) {

        List<EventProcessingDataSourceRO> eProcDataSources = eventProcessingDataSourceRepo.findByEventProcessingId(ep.getId());

        Set<IoTDeviceDataSourceRO> deviceDataSources = new HashSet<IoTDeviceDataSourceRO>();

        for (EventProcessingDataSourceRO eProcDataSource : eProcDataSources) {
            deviceDataSources.addAll(deviceDataSourceRepository.findByDomainAndDevice(eProcDataSource.getDomain(), eProcDataSource.getDevice()));
        }

        return deviceDataSources;

    }
}
