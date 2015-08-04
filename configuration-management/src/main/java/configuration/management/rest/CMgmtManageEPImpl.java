package configuration.management.rest;

import java.util.ArrayList;
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
import common.data.MeasurementData;
import common.data.MeasurementPoint;
import common.rest.Url;

import configuration.management.model.DeviceDataSourceJPA;
import configuration.management.repo.EProcRepository;
import configuration.management.repo.MeasurementPointRepository;

public class CMgmtManageEPImpl implements CMgmtManageEP {

    final static Logger logger = Logger.getLogger(CMgmtManageEPImpl.class);

    @Autowired
    private EProcRepository eprocRepository;

    @Autowired
    private MeasurementPointRepository measurementPointRepository;

    @RequestMapping(value = "/subscription", method = RequestMethod.POST)
    public String subscripeEP(@RequestBody MeasurementData data) {

        logger.info("POST /subscription is invoked");

        List<DeviceDataSourceJPA> points = new ArrayList<DeviceDataSourceJPA>();
        for (MeasurementPoint point : data.getMeasurementPoints()) {
            points.addAll(measurementPointRepository.findByDomainAndDeviceInformation(point.getDomain().getName(), point.getDeviceInformation().getName()));
        }

        Set<Long> deviceIds = new HashSet<Long>();
        for (DeviceDataSourceJPA point : points) {
            deviceIds.add(point.getDeviceId());
        }

        for (Long id : deviceIds) {

            String url = Url.IDEV_SET_CONFIGURATION.getUrl("127.0.0.1", "5002");

            ConfigurationModification cm = new ConfigurationModification();

            try {
                RestTemplate restTemplate = new RestTemplate();
                ResponseEntity<String> response = restTemplate.postForEntity(url, cm, String.class);
                HttpStatus status = response.getStatusCode();
                String restCall = response.getBody();
                logger.info("Send delegation to url: " + url + " Status: " + status + " Response body: " + restCall);
            } catch (Exception ex) {
                // TODO
                logger.error("Delegation error.");
            }

        }

        return "OK";
    }

    @RequestMapping(value = "/delegation", method = RequestMethod.POST)
    public String delegateEP(@RequestBody MeasurementData data) {

        logger.info("POST /delegation is invoked");

        List<DeviceDataSourceJPA> points = new ArrayList<DeviceDataSourceJPA>();
        for (MeasurementPoint point : data.getMeasurementPoints()) {
            points.addAll(measurementPointRepository.findByDomainAndDeviceInformation(point.getDomain().getName(), point.getDeviceInformation().getName()));
        }

        Set<Long> deviceIds = new HashSet<Long>();
        for (DeviceDataSourceJPA point : points) {
            deviceIds.add(point.getDeviceId());
        }

        for (Long id : deviceIds) {

            String url = Url.IDEV_SET_CONFIGURATION.getUrl("127.0.0.1", "5002");

            ConfigurationModification cm = new ConfigurationModification();

            try {
                RestTemplate restTemplate = new RestTemplate();
                ResponseEntity<String> response = restTemplate.postForEntity(url, cm, String.class);
                HttpStatus status = response.getStatusCode();
                String restCall = response.getBody();
                logger.info("Send delegation to url: " + url + " Status: " + status + " Response body: " + restCall);
            } catch (Exception ex) {
                // TODO
                logger.error("Delegation error.");
            }

        }

        return "OK";
    }
}
