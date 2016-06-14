package configuration.management.rest.impl;

import java.util.ArrayList;
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

import common.data.Connection;
import common.data.dto.DataSourcesDTO;
import common.data.model.DataSource;
import common.data.type.COMPONENT_TYPE;
import common.rest.RESOURCE_NAMING;
import common.rest.ResourceUtils;
import common.transformer.Transformer;
import configuration.management.model.DeviceDLO;
import configuration.management.repo.DataSourceTransformer;
import configuration.management.repo.DeviceRepository;
import configuration.management.repo.DeviceTransformer;
import configuration.management.rest.CMManageDevice;
import configuration.management.rest.activity.HeartbeatDevice;
import configuration.management.rest.activity.RegisterDataSourcesDevice;
import configuration.management.rest.activity.RegisterDevice;
import configuration.management.rest.activity.ValidateConnection;

@RestController
public class CMManageDeviceImpl implements CMManageDevice {

    final static Logger logger = LoggerFactory.getLogger(CMManageDeviceImpl.class);

    @Autowired
    private DeviceRepository deviceRepo;

    @Autowired
    private DeviceTransformer transformer;

    @Autowired
    private DataSourceTransformer transformerDataSource;

    @Autowired
    private ValidateConnection validateConnection;

    @Autowired
    private RegisterDevice register;

    @Autowired
    private RegisterDataSourcesDevice registerDataSources;

    @Autowired
    private HeartbeatDevice heartbeat;

    @Override
    @RequestMapping(value = "/registrations/devices", method = RequestMethod.GET)
    public @ResponseBody ResponseEntity<List<Connection>> getAll() {

        logger.info(ResourceUtils.getLogMessage(RESOURCE_NAMING.CM_GET_ALL_DEVICES));

        List<Connection> devices = transformer.toRemote(Transformer.makeCollection(deviceRepo.findAll()));

        return new ResponseEntity<List<Connection>>(devices, HttpStatus.OK);
    }

    @Override
    @RequestMapping(value = "/registrations/devices", method = RequestMethod.POST)
    public ResponseEntity<Connection> register(@RequestBody Connection connection) {
        logger.info(ResourceUtils.getLogMessage(RESOURCE_NAMING.CM_REGISTER_DEVICE));

        /**
         * If device with URL already exists, return existing values. Otherwise generate new values.
         */

        validateConnection.setNextActivity(register);
        validateConnection.setCt(COMPONENT_TYPE.DEVICE);

        return validateConnection.doStep(connection);
    }

    @Override
    @RequestMapping(value = "/registrations/devices/sources/{id}", method = RequestMethod.POST)
    public ResponseEntity<String> registerDataSources(@PathVariable(value = "id") Long id, @RequestBody DataSourcesDTO data) {

        logger.info(ResourceUtils.getLogMessage(RESOURCE_NAMING.CM_REGISTER_DEVICE_SOURCES));

        registerDataSources.setId(id);

        return registerDataSources.doStep(data);
    }

    @Override
    @RequestMapping(value = "/registrations/devices/{id}", method = RequestMethod.PUT)
    public ResponseEntity<String> heartbeat(@PathVariable Long id) {

        logger.info(ResourceUtils.getLogMessage(RESOURCE_NAMING.CM_HEART_BEAT_DEVICE));
        return heartbeat.doStep(id);
    }

    @Override
    @RequestMapping(value = "/registrations/devices/sources/{id}", method = RequestMethod.GET)
    public ResponseEntity<List<DataSource>> getDataSources(@PathVariable(value = "id") Long id) {

        logger.info(ResourceUtils.getLogMessage(RESOURCE_NAMING.CM_GET_DEVICE_DATA_SOURCES));

        DeviceDLO device = deviceRepo.findOne(id);

        if (null == device) {
            return new ResponseEntity<List<DataSource>>(new ArrayList<DataSource>(), HttpStatus.BAD_REQUEST);
        } else {
            List<DataSource> dataSource = transformerDataSource.toRemote(device.getDataSources());
            return new ResponseEntity<List<DataSource>>(dataSource, HttpStatus.OK);
        }
    }

    @Override
    @RequestMapping(value = "/registrations/devices/sources/{devInfo}/{domainInfo}", method = RequestMethod.GET)
    public ResponseEntity<List<Connection>> getDeviceByDataSource(@PathVariable(value = "devInfo") String devInfo, @PathVariable(value = "domainInfo") String domainInfo) {
        logger.info(ResourceUtils.getLogMessage(RESOURCE_NAMING.CM_GET_DEVICE_BY_DATA_SOURCES));

        List<DeviceDLO> devices = deviceRepo.findByDataSources(devInfo, domainInfo);

        List<Connection> connections = transformer.toRemote(devices);

        return new ResponseEntity<List<Connection>>(connections, HttpStatus.OK);
    }
}
