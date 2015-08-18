package configuration.management.rest;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;
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

import configuration.management.model.DeviceDataSourceJPA;
import configuration.management.model.DeviceJPA;
import configuration.management.model.EventProcessingDataSourceJPA;
import configuration.management.model.EventProcessingJPA;
import configuration.management.repo.DeviceDataSourceRepository;
import configuration.management.repo.DeviceRepository;
import configuration.management.repo.EProcDataSourceRepository;
import configuration.management.repo.EventProcessingRepository;
import configuration.management.repo.EventProcessingTransformer;

@RestController
public class CMgmtManageEPImpl implements CMgmtManageEP {

    final static Logger logger = Logger.getLogger(CMgmtManageEPImpl.class);

    final static String TEIG = "TING";

    @Autowired
    private EventProcessingRepository eventProcessingRepo;

    @Autowired
    private EProcDataSourceRepository eventProcessingDataSourceRepo;

    @Autowired
    private DeviceRepository deviceRepository;

    @Autowired
    private DeviceDataSourceRepository deviceDataSourceRepository;

    @Autowired
    private EventProcessingTransformer transformer;

    @RequestMapping(value = "/registrations/eventprocessing", method = RequestMethod.GET)
    public @ResponseBody
    List<Connection> getAllEventProcessingInstances() {

        logger.info(UtilsResource.getLogMessage(RESOURCE_NAMING.CMGMT_GET_ALL_EVENT_PROCESSING));
        return transformer.toRemote(Transformer.makeCollection(eventProcessingRepo.findAll()));
    }

    @RequestMapping(value = "/registrations/eventprocessing", method = RequestMethod.POST)
    public Connection registerEventProcessingInstance(@RequestBody Connection connection) {

        logger.info(UtilsResource.getLogMessage(RESOURCE_NAMING.CMGMT_REGISTER_EVENT_PROCESSING));

        EventProcessingJPA item = new EventProcessingJPA();
        item.setUrl(connection.getUrl());
        item = eventProcessingRepo.save(item);

        connection.setId(item.getId());

        return connection;
    }

    @RequestMapping(value = "/registrations/eventprocessing/{id}", method = RequestMethod.PUT)
    public void heartBeat(@PathVariable(value = "id") Long id) {

        logger.info(UtilsResource.getLogMessage(RESOURCE_NAMING.CMGMT_HEART_BEAT_EVENT_PROCESSING));

        EventProcessingJPA item = eventProcessingRepo.findOne(id);
        if (item != null) {
            eventProcessingRepo.save(item);
            // TODO
        }
    }

    @RequestMapping(value = "/delegation/{id}", method = RequestMethod.POST)
    public void delegate(@PathVariable(value = "id") Long id, @RequestBody DataSources data) {

        logger.info(UtilsResource.getLogMessage(RESOURCE_NAMING.CMGMT_DELEGATION));

        EventProcessingJPA ep = eventProcessingRepo.findOne(id);

        for (DataSource point : data.getDataSources()) {

            EventProcessingDataSourceJPA item = new EventProcessingDataSourceJPA();
            item.setEProcId(id);
            item.setDomain(point.getDomain().getName());
            item.setDeviceInformation(point.getDeviceInformation().getName());

            eventProcessingDataSourceRepo.save(item);
        }

        logger.info("Get device to notify ...");

        Set<DeviceDataSourceJPA> devicesToNotify = getDevicesToNotify(ep);

        logger.info("Number of devices to notify: " + devicesToNotify.size());

        logger.info("Notify devices ... ");

        notifyDevices(devicesToNotify, ep);

    }

    private void notifyDevices(Set<DeviceDataSourceJPA> devicesToNotify, EventProcessingJPA ep) {

        for (DeviceDataSourceJPA deviceJPA : devicesToNotify) {

            ConfigurationModification cm = new ConfigurationModification();
            cm.setEpId(ep.getId());
            cm.setEpUrl(ep.getUrl());

            DeviceJPA device = deviceRepository.findOne(deviceJPA.getDeviceId());

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

    private Set<DeviceDataSourceJPA> getDevicesToNotify(EventProcessingJPA ep) {

        List<EventProcessingDataSourceJPA> eProcDataSources = eventProcessingDataSourceRepo.findByEProcId(ep.getId());

        Set<DeviceDataSourceJPA> deviceDataSources = new HashSet<DeviceDataSourceJPA>();

        for (EventProcessingDataSourceJPA eProcDataSource : eProcDataSources) {
            deviceDataSources.addAll(deviceDataSourceRepository.findByDomainAndDeviceInformation(eProcDataSource.getDomain(), eProcDataSource.getDeviceInformation()));
        }

        return deviceDataSources;

    }
}
