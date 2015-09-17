package configuration.management.rest;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import common.data.Connection;
import common.data.DataSource;
import common.data.DataSources;
import common.rest.RESOURCE_NAMING;
import common.rest.UtilsResource;
import common.transformer.Transformer;
import configuration.management.model.IoTDeviceDataSourceRO;
import configuration.management.model.IoTDeviceRO;
import configuration.management.repo.IoTDeviceDataSourceRepository;
import configuration.management.repo.IoTDeviceRepository;
import configuration.management.repo.IoTDeviceTransformer;

@RestController
public class CMgmtManageIoTDeviceImpl implements CMgmtManageIoTDevice {

    final static Logger logger = LoggerFactory.getLogger(CMgmtManageIoTDeviceImpl.class);

    @Autowired
    private IoTDeviceRepository deviceRepo;

    @Autowired
    private IoTDeviceDataSourceRepository deviceDataSourceRepo;

    @Autowired
    private IoTDeviceTransformer transformer;

    @Override
    @RequestMapping(value = "/registrations/devices", method = RequestMethod.GET)
    public @ResponseBody List<Connection> getAll() {

        logger.info(UtilsResource.getLogMessage(RESOURCE_NAMING.CMGMT_GET_ALL_DEVICES));

        return transformer.toRemote(Transformer.makeCollection(deviceRepo.findAll()));
    }

    @Override
    @RequestMapping(value = "/registrations/devices", method = RequestMethod.POST)
    public Connection register(@RequestBody Connection connection) {
        logger.info(UtilsResource.getLogMessage(RESOURCE_NAMING.CMGMT_REGISTER_DEVICE));

        IoTDeviceRO item = new IoTDeviceRO();
        item.setDate(new Date());
        item.setUrl(connection.getUrl());
        item = deviceRepo.save(item);

        connection.setId(item.getId());

        return connection;
    }

    @Override
    @RequestMapping(value = "/registrations/devices/sources/{id}", method = RequestMethod.POST)
    public void registerDataSources(@PathVariable(value = "id") Long id, @RequestBody DataSources data) {

        logger.info(UtilsResource.getLogMessage(RESOURCE_NAMING.CMGMT_REGISTER_DEVICE_SOURCES));

        for (DataSource dataSource : data.getDataSources()) {

            IoTDeviceDataSourceRO item = new IoTDeviceDataSourceRO();
            // TODO Manuel
            // item.setDeviceId(id);
            item.setDevice(dataSource.getDevice().getName());
            item.setDomain(dataSource.getDomain().getName());

            deviceDataSourceRepo.save(item);
        }

    }

    @Override
    @RequestMapping(value = "/registrations/devices/{id}", method = RequestMethod.PUT)
    public void heartBeat(@PathVariable(value = "id") Long id) {

        logger.info(UtilsResource.getLogMessage(RESOURCE_NAMING.CMGMT_HEART_BEAT_DEVICE));

        IoTDeviceRO item = deviceRepo.findOne(id);
        if (item != null) {
            item.setDate(new Date());
            deviceRepo.save(item);
        }

    }
}
