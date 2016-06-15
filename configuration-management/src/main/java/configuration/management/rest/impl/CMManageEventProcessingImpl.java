package configuration.management.rest.impl;

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

import common.data.ConfigurationDelegation;
import common.data.Connection;
import common.data.model.DataSource;
import common.data.type.COMPONENT_TYPE;
import common.rest.RESOURCE_NAMING;
import common.rest.ResourceUtils;
import common.transformer.Transformer;
import configuration.management.model.EventProcessingDLO;
import configuration.management.repo.DataSourceTransformer;
import configuration.management.repo.EventProcessingRepository;
import configuration.management.repo.EventProcessingTransformer;
import configuration.management.rest.CMManageEventProcessing;
import configuration.management.rest.activity.DelegateConfigChange;
import configuration.management.rest.activity.DelegateDeliveryChange;
import configuration.management.rest.activity.receive.HeartbeatEP;
import configuration.management.rest.activity.receive.RegisterDataSourcesEP;
import configuration.management.rest.activity.receive.RegisterEP;
import configuration.management.rest.activity.validate.ValidateConfigDelegation;
import configuration.management.rest.activity.validate.ValidateConnection;

@RestController
public class CMManageEventProcessingImpl implements CMManageEventProcessing {

    final static Logger logger = LoggerFactory.getLogger(CMManageEventProcessingImpl.class);

    @Autowired
    private EventProcessingRepository repository;

    @Autowired
    private EventProcessingTransformer transformer;

    @Autowired
    private DataSourceTransformer dataSourceTransformer;

    @Autowired
    private ValidateConnection validateConnection;

    @Autowired
    private ValidateConfigDelegation validateConfigDelegation;

    @Autowired
    private RegisterEP register;

    @Autowired
    private HeartbeatEP heartBeat;

    @Autowired
    private RegisterDataSourcesEP registerDataSources;

    @Autowired
    private DelegateConfigChange delegateConfigChange;

    @Autowired
    private DelegateDeliveryChange delegateDeliveryChange;

    @Override
    @RequestMapping(value = "/registrations/eventprocessing", method = RequestMethod.GET)
    public @ResponseBody ResponseEntity<List<Connection>> getAll() {

        logger.info(ResourceUtils.getLogMessage(RESOURCE_NAMING.CM_GET_ALL_EVENT_PROCESSING));

        List<Connection> eps = transformer.toRemote(Transformer.makeCollection(repository.findAll()));

        return new ResponseEntity<List<Connection>>(eps, HttpStatus.OK);
    }

    @Override
    @RequestMapping(value = "/registrations/eventprocessing", method = RequestMethod.POST)
    public ResponseEntity<Connection> register(@RequestBody Connection connection) {

        logger.info(ResourceUtils.getLogMessage(RESOURCE_NAMING.CM_REGISTER_EVENT_PROCESSING));

        validateConnection.setNextActivity(register);
        validateConnection.setCt(COMPONENT_TYPE.EVENT_PROCESSING);

        return validateConnection.doStep(connection);
    }

    @Override
    @RequestMapping(value = "/registrations/eventprocessing/{id}/{value1}/{value2}", method = RequestMethod.PUT)
    public ResponseEntity<String> heartbeat(@PathVariable Long id, @PathVariable Integer value1, @PathVariable Integer value2) {

        logger.info(ResourceUtils.getLogMessage(RESOURCE_NAMING.CM_HEART_BEAT_EVENT_PROCESSING));

        heartBeat.setValue1(value1);
        heartBeat.setValue2(value2);

        return heartBeat.doStep(id);

    }

    @Override
    @RequestMapping(value = "/registrations/devices/eventprocessing/{id}", method = RequestMethod.GET)
    public ResponseEntity<List<DataSource>> getDataSources(@PathVariable(value = "id") Long id) {

        logger.info(ResourceUtils.getLogMessage(RESOURCE_NAMING.CM_GET_EVENT_PROCESSING_DATA_SOURCES));

        EventProcessingDLO item = repository.findOne(id);

        if (null == item) {
            return null;
        } else {
            List<DataSource> dataSource = dataSourceTransformer.toRemote(item.getDataSources());
            return new ResponseEntity<List<DataSource>>(dataSource, HttpStatus.OK);
        }
    }

    @Override
    @RequestMapping(value = "/delegation", method = RequestMethod.POST)
    public ResponseEntity<String> delegate(@RequestBody ConfigurationDelegation data) {

        logger.info(ResourceUtils.getLogMessage(RESOURCE_NAMING.CM_DELEGATION));

        validateConfigDelegation.setNextActivity(delegateConfigChange);

        return validateConfigDelegation.doStep(data);

    }

    @Override
    @RequestMapping(value = "/registrations/eventprocessing/sources", method = RequestMethod.POST)
    public ResponseEntity<String> registerDataSources(@RequestBody ConfigurationDelegation body) {

        logger.info(ResourceUtils.getLogMessage(RESOURCE_NAMING.CM_REGISTER_EVENT_PROCESSING_SOURCES));

        validateConfigDelegation.setNextActivity(registerDataSources);
        registerDataSources.setNextActivity(delegateDeliveryChange);
        delegateDeliveryChange.setNextActivity(null);

        registerDataSources.setDeregiser(false);

        delegateDeliveryChange.setStop(false);

        return validateConfigDelegation.doStep(body);
    }

    @Override
    @RequestMapping(value = "/deregistrations/eventprocessing/sources", method = RequestMethod.POST)
    public ResponseEntity<String> deregisterDataSources(@RequestBody ConfigurationDelegation body) {

        logger.info(ResourceUtils.getLogMessage(RESOURCE_NAMING.CM_DEREGISTER_EVENT_PROCESSING_SOURCES));

        validateConfigDelegation.setNextActivity(registerDataSources);
        registerDataSources.setNextActivity(delegateDeliveryChange);
        delegateDeliveryChange.setNextActivity(null);

        registerDataSources.setDeregiser(true);

        delegateDeliveryChange.setStop(true);

        return validateConfigDelegation.doStep(body);
    }

}
