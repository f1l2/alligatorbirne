package configuration.management.rest;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import common.data.ConfigurationDelegation;
import common.data.Connection;
import common.rest.RESOURCE_NAMING;
import common.rest.UtilsResource;
import common.transformer.Transformer;
import configuration.management.model.EventProcessingRO;
import configuration.management.model.IoTDeviceRO;
import configuration.management.repo.EventProcessingRepository;
import configuration.management.repo.EventProcessingTransformer;
import configuration.management.repo.IoTDeviceRepository;
import configuration.management.repo.IoTDeviceTransformer;

@RestController
public class CMgmtManageEventProcessingImpl implements CMgmtManageEventProcessing {

    final static Logger logger = LoggerFactory.getLogger(CMgmtManageEventProcessingImpl.class);

    @Autowired
    private EventProcessingRepository eventProcessingRepo;

    @Autowired
    private IoTDeviceRepository deviceRepository;

    @Autowired
    private EventProcessingTransformer transformer;

    @Autowired
    private IoTDeviceTransformer iotTransformer;

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

        EventProcessingRO item = eventProcessingRepo.findByAuthority(connection.getUrl().getAuthority());
        if (null != item) {
            connection.setId(item.getId());
            item.setUpdated(new Date());
        } else {
            item = new EventProcessingRO();
            item.setCreated(new Date());
            item.setUpdated(new Date());
            item.setAuthority(connection.getUrl().getAuthority());
            item = eventProcessingRepo.save(item);
        }

        item = eventProcessingRepo.save(item);
        connection.setId(item.getId());

        return connection;
    }

    @Override
    @RequestMapping(value = "/registrations/eventprocessing/{id}", method = RequestMethod.PUT)
    public ResponseEntity<String> heartBeat(@PathVariable(value = "id") Long id) {

        logger.info(UtilsResource.getLogMessage(RESOURCE_NAMING.CMGMT_HEART_BEAT_EVENT_PROCESSING));

        EventProcessingRO item = eventProcessingRepo.findOne(id);
        if (item != null) {
            eventProcessingRepo.save(item);
            // TODO
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    @RequestMapping(value = "/delegation/{id}", method = RequestMethod.POST)
    public ResponseEntity<String> delegate(@PathVariable(value = "id") Long id, @RequestBody ConfigurationDelegation data) {

        logger.info(UtilsResource.getLogMessage(RESOURCE_NAMING.CMGMT_DELEGATION));

        List<IoTDeviceRO> devicesToBeContacted = deviceRepository.findByIoTDeviceDataSources(data.getDeviceInformation().getName(), data.getDomainInformation().getName());

        List<Connection> connectionsToBeContacted = iotTransformer.toRemote(devicesToBeContacted);

        for (Connection connection : connectionsToBeContacted) {
            try {
                RestTemplate restTemplate = new RestTemplate();
                String url = UtilsResource.getUrl(RESOURCE_NAMING.IDEV_SET_CONFIGURATION, connection.getUrl().getAuthority());
                ResponseEntity<String> response = restTemplate.postForEntity(url, data.getConfigurationModification(), String.class);
                logger.info("Device notification - " + url + " Status: " + response.getStatusCode() + " Response body: " + response.getBody());
            } catch (Exception e) {
                logger.error("{}", e);
            }
        }

        return new ResponseEntity<>(HttpStatus.OK);

    }
}
