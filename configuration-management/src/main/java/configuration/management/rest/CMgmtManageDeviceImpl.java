package configuration.management.rest;

import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import common.data.ConnectionProperties;
import common.data.MeasurementData;
import common.data.MeasurementPoint;
import common.rest.RESOURCE_NAMING;
import common.transformer.Transformer;

import configuration.management.model.DeviceDataSourceJPA;
import configuration.management.model.DeviceJPA;
import configuration.management.repo.DeviceDataSourceRepository;
import configuration.management.repo.DeviceRepository;
import configuration.management.repo.DeviceTransformer;

@RestController
public class CMgmtManageDeviceImpl implements CMgmtManageDevice {

    final static Logger logger = Logger.getLogger(CMgmtManageDeviceImpl.class);

    @Autowired
    private DeviceRepository deviceRepo;

    @Autowired
    private DeviceDataSourceRepository deviceDataSourceRepo;

    @Autowired
    private DeviceTransformer transformer;

    @Override
    @RequestMapping(value = "/registrations/devices", method = RequestMethod.GET)
    public @ResponseBody
    List<ConnectionProperties> getAllDevices() {

        logger.info(RESOURCE_NAMING.CMGMT_GET_ALL_DEVICES.getLogMessage());

        return transformer.toRemote(Transformer.makeCollection(deviceRepo.findAll()));
    }

    @RequestMapping(value = "/registrations/devices", method = RequestMethod.POST)
    public ConnectionProperties registerDevice(@RequestBody ConnectionProperties connection) {

        logger.info(RESOURCE_NAMING.CMGMT_REGISTER_DEVICE.getRequestMethod() + " " + RESOURCE_NAMING.CMGMT_REGISTER_DEVICE.getPath());

        DeviceJPA item = new DeviceJPA();
        item.setDate(new Date());
        item.setUrl(connection.getUrl());
        item = deviceRepo.save(item);

        connection.setId(item.getId());

        return connection;
    }

    @Override
    @RequestMapping(value = "/registrations/devices/sources/{id}", method = RequestMethod.POST)
    public void registerDeviceSources(@PathVariable(value = "id") Long id, @RequestBody MeasurementData data) {

        logger.info(RESOURCE_NAMING.CMGMT_REGISTER_DEVICE_SOURCES.getLogMessage());

        for (MeasurementPoint point : data.getMeasurementPoints()) {

            DeviceDataSourceJPA item = new DeviceDataSourceJPA();
            item.setDeviceId(id);
            item.setDeviceInformation(point.getDeviceInformation().getName());
            item.setDomain(point.getDomain().getName());

            deviceDataSourceRepo.save(item);
        }

    }

    @Override
    @RequestMapping(value = "/registrations/devices/{id}", method = RequestMethod.PUT)
    public void heartBeat(@PathVariable(value = "id") Long id) {

        logger.info(RESOURCE_NAMING.CMGMT_HEART_BEAT_DEVICE.getLogMessage());

        DeviceJPA item = deviceRepo.findOne(id);
        if (item != null) {
            item.setDate(new Date());
            deviceRepo.save(item);
        }

    }
}
