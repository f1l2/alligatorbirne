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

import common.data.Connection;
import common.data.MeasurementData;
import common.data.MeasurementPoint;
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
    private DeviceRepository deviceRepository;

    @Autowired
    private DeviceDataSourceRepository measurementPointRepository;

    @Autowired
    private DeviceTransformer transformer;

    @Override
    @RequestMapping(value = "/registrations", method = RequestMethod.GET)
    public @ResponseBody List<Connection> getAllDevices() {

        logger.info("GET /registrations is invoked");

        return transformer.toRemote(Transformer.makeCollection(deviceRepository.findAll()));
    }

    @RequestMapping(value = "/registrations", method = RequestMethod.POST)
    public Connection registerDevice(@RequestBody Connection connection) {

        logger.info("POST /registrations is invoked");

        DeviceJPA device = new DeviceJPA();
        device.setDate(new Date());
        device.setUrl(connection.getUrl());
        device = deviceRepository.save(device);

        connection.setId(device.getId());

        return connection;
    }

    @Override
    @RequestMapping(value = "/registrations/sources/{id}", method = RequestMethod.POST)
    public void registerDeviceSources(@PathVariable(value = "id") Long id, @RequestBody MeasurementData data) {

        logger.info("POST /registrations/sources{id} is invoked");

        for (MeasurementPoint point : data.getMeasurementPoints()) {

            DeviceDataSourceJPA item = new DeviceDataSourceJPA();
            item.setDeviceId(id);
            item.setDeviceInformation(point.getDeviceInformation().getName());
            item.setDomain(point.getDomain().getName());

            measurementPointRepository.save(item);
        }

    }

    @Override
    @RequestMapping(value = "/registrations/{id}", method = RequestMethod.PUT)
    public void heartBeat(@PathVariable(value = "id") Long id) {

        logger.info("PUT /registrations{id} is invoked");

        DeviceJPA device = deviceRepository.findOne(id);
        if (device != null) {
            device.setDate(new Date());
            deviceRepository.save(device);
        }

    }
}
