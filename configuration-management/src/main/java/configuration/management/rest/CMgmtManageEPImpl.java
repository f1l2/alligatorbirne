package configuration.management.rest;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;

import common.data.ConfigurationModification;
import common.data.EProcInformation;
import common.data.MeasurementData;
import common.data.MeasurementPoint;
import common.rest.Url;

import configuration.management.model.DeviceDataSourceJPA;
import configuration.management.model.DeviceJPA;
import configuration.management.model.EProcDataSourceJPA;
import configuration.management.model.EProcJPA;
import configuration.management.repo.DeviceDataSourceRepository;
import configuration.management.repo.DeviceRepository;
import configuration.management.repo.EProcDataSourceRepository;
import configuration.management.repo.EProcRepository;
import configuration.management.repo.EProcTransformer;

public class CMgmtManageEPImpl implements CMgmtManageEP {

    final static Logger logger = Logger.getLogger(CMgmtManageEPImpl.class);

    @Autowired
    private EProcRepository eProcRepository;

    @Autowired
    private EProcDataSourceRepository eProcDataSourceRepository;

    @Autowired
    private DeviceRepository deviceRepository;

    @Autowired
    private DeviceDataSourceRepository deviceDataSourceRepository;

    @Autowired
    private EProcTransformer eProcTransformer;

    @RequestMapping(value = "/subscription", method = RequestMethod.POST)
    public String subscripe(@RequestBody EProcInformation eproc) {

        logger.info("POST /subscription is invoked");

        EProcJPA local = eProcTransformer.toLocal(eproc);

        eProcRepository.save(local);

        return "OK";
    }

    @RequestMapping(value = "/delegation", method = RequestMethod.POST)
    public String delegate(@RequestBody EProcInformation eProc, @RequestBody MeasurementData data) {

        logger.info("POST /delegation is invoked");

        for (MeasurementPoint point : data.getMeasurementPoints()) {

            EProcDataSourceJPA item = new EProcDataSourceJPA();
            item.setEProcId(eProc.getId());
            item.setDomain(point.getDomain().getName());
            item.setDeviceInformation(point.getDeviceInformation().getName());

            eProcDataSourceRepository.save(item);
        }

        Set<DeviceDataSourceJPA> devicesToNotify = getDevicesToNotify(eProc);

        notifyDevices(devicesToNotify, eProc);

        return "OK";
    }

    private void notifyDevices(Set<DeviceDataSourceJPA> devicesToNotify, EProcInformation eProc) {

        for (DeviceDataSourceJPA deviceDataSource : devicesToNotify) {

            ConfigurationModification cm = new ConfigurationModification();
            cm.setEpId(eProc.getId());
            cm.setEpUrl(eProc.getUrl());

            DeviceJPA device = deviceRepository.findOne(deviceDataSource.getDeviceId());

            String url = Url.IDEV_SET_CONFIGURATION.getUrl("127.0.0.1", "5002");
            // TODO
            // String url = device.getUrl() + Url.IDEV_SET_CONFIGURATION.getPath();

            try {
                RestTemplate restTemplate = new RestTemplate();
                ResponseEntity<String> response = restTemplate.postForEntity(url, cm, String.class);
                HttpStatus status = response.getStatusCode();
                String restCall = response.getBody();
                logger.info("Device registered. Status: " + status + " Response body: " + restCall);
            } catch (Exception ex) {
                // TODO
                logger.error("Register error.");
            }

        }

    }

    private Set<DeviceDataSourceJPA> getDevicesToNotify(EProcInformation eProc) {

        List<EProcDataSourceJPA> eProcDataSources = eProcDataSourceRepository.findByEProcId(eProc.getId());

        Set<DeviceDataSourceJPA> deviceDataSources = new HashSet<DeviceDataSourceJPA>();

        for (EProcDataSourceJPA eProcDataSource : eProcDataSources) {
            deviceDataSources.addAll(deviceDataSourceRepository.findByDomainAndDeviceInformation(eProcDataSource.getDomain(), eProcDataSource.getDeviceInformation()));
        }

        return deviceDataSources;

    }
}
