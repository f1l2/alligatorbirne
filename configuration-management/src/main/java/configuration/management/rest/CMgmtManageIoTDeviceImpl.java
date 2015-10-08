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

import common.data.Connection;
import common.data.DataSource;
import common.data.DataSources;
import common.data.type.COMPONENT_TYPE;
import common.rest.RESOURCE_NAMING;
import common.rest.UtilsResource;
import common.transformer.Transformer;
import configuration.management.model.IoTDeviceDataSourceRO;
import configuration.management.model.IoTDeviceRO;
import configuration.management.repo.IoTDeviceDataSourceRepository;
import configuration.management.repo.IoTDeviceRepository;
import configuration.management.repo.IoTDeviceTransformer;
import configuration.management.rest.task.RegisterIoTDevice;
import configuration.management.rest.task.ValidateConnection;

@RestController
public class CMgmtManageIoTDeviceImpl implements CMgmtManageIoTDevice {

    final static Logger logger = LoggerFactory.getLogger(CMgmtManageIoTDeviceImpl.class);

    @Autowired
    private IoTDeviceRepository deviceRepo;

    @Autowired
    private IoTDeviceDataSourceRepository deviceDataSourceRepo;

    @Autowired
    private IoTDeviceTransformer transformer;

    @Autowired
    private ValidateConnection validateConnection;

    @Autowired
    private RegisterIoTDevice registerIoTDevice;

    @Override
    @RequestMapping(value = "/registrations/devices", method = RequestMethod.GET)
    public @ResponseBody ResponseEntity<List<Connection>> getAll() {

        logger.info(UtilsResource.getLogMessage(RESOURCE_NAMING.CMGMT_GET_ALL_DEVICES));

        List<Connection> devices = transformer.toRemote(Transformer.makeCollection(deviceRepo.findAll()));

        return new ResponseEntity<List<Connection>>(devices, HttpStatus.OK);
    }

    @Override
    @RequestMapping(value = "/registrations/devices", method = RequestMethod.POST)
    public ResponseEntity<Connection> register(@RequestBody Connection connection) {
        logger.info(UtilsResource.getLogMessage(RESOURCE_NAMING.CMGMT_REGISTER_DEVICE));

        /**
         * If device with URL already exists, return existing values. Otherwise generate new values.
         */

        validateConnection.setNextTask(registerIoTDevice);
        validateConnection.setCt(COMPONENT_TYPE.IOT_DEVICE);

        return validateConnection.doStep(connection);
    }

    @Override
    @RequestMapping(value = "/registrations/devices/sources/{id}", method = RequestMethod.POST)
    public ResponseEntity<String> registerDataSources(@PathVariable(value = "id") Long id, @RequestBody DataSources data) {

        logger.info(UtilsResource.getLogMessage(RESOURCE_NAMING.CMGMT_REGISTER_DEVICE_SOURCES));

        for (DataSource dataSource : data.getDataSources()) {

            IoTDeviceDataSourceRO item = new IoTDeviceDataSourceRO();
            // TODO Manuel
            // item.setDeviceId(id);
            item.setDevice(dataSource.getDeviceInformation().getName());
            item.setDomain(dataSource.getDomainInformation().getName());

            deviceDataSourceRepo.save(item);
        }

        return new ResponseEntity<String>("", HttpStatus.OK);
    }

    @Override
    @RequestMapping(value = "/registrations/devices/{id}", method = RequestMethod.PUT)
    public ResponseEntity<String> heartBeat(@PathVariable(value = "id") Long id) {

        logger.info(UtilsResource.getLogMessage(RESOURCE_NAMING.CMGMT_HEART_BEAT_DEVICE));

        IoTDeviceRO item = deviceRepo.findOne(id);
        if (item != null) {
            item.setCreated(new Date());
            deviceRepo.save(item);
        }

        return new ResponseEntity<String>("", HttpStatus.OK);
    }
}
