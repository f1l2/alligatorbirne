package monitoring.webapp.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import common.data.Connection;
import common.data.DataSource;
import common.data.dto.QueryDTO;
import common.data.dto.RuleDTO;
import common.data.type.COMPONENT_TYPE;
import common.rest.RESOURCE_NAMING;
import common.rest.ResourceUtils;
import common.rest.UrlUtils;

@Component
public class MonitoringService {

    private RestTemplate restTemplate;

    private Connection cm;

    // @Value("${cm.server.host}")
    private String host = "localhost";

    // @Value("${cm.server.port}")
    private int port = 5000;

    @PostConstruct
    public void postConstruct() {
        restTemplate = new RestTemplate();

        cm = new Connection();
        cm.setComponentType(COMPONENT_TYPE.CONFIGURATION_MANAGEMENT);
        cm.setName("CM");
        cm.setUrl(UrlUtils.parseUrl(host + ":" + Integer.toString(port)));
    }

    public List<Connection> listEP() {
        String url = ResourceUtils.getUrl(RESOURCE_NAMING.CMGMT_GET_ALL_EVENT_PROCESSING, cm);

        try {
            ResponseEntity<Connection[]> response = restTemplate.getForEntity(url, Connection[].class);
            return Arrays.asList(response.getBody());
        } catch (Exception e) {
            return new ArrayList<Connection>();
        }
    }

    public List<Connection> listDev() {

        String url = ResourceUtils.getUrl(RESOURCE_NAMING.CMGMT_GET_ALL_DEVICES, cm);

        try {
            ResponseEntity<Connection[]> response = restTemplate.getForEntity(url, Connection[].class);
            return Arrays.asList(response.getBody());
        } catch (Exception e) {
            return new ArrayList<Connection>();
        }
    }

    public List<QueryDTO> listRegisteredQuery(Connection connection) {

        String url = ResourceUtils.getUrl(RESOURCE_NAMING.EPROCESSING_GET_ALL_QUERIES, connection);

        try {
            ResponseEntity<QueryDTO[]> response = restTemplate.getForEntity(url, QueryDTO[].class);
            return Arrays.asList(response.getBody());

        } catch (Exception e) {
            return new ArrayList<QueryDTO>();
        }
    }

    public List<RuleDTO> listRegisteredRule(Connection connection) {

        String url = ResourceUtils.getUrl(RESOURCE_NAMING.EPROCESSING_GET_ALL_RULES, connection);

        try {
            ResponseEntity<RuleDTO[]> response = restTemplate.getForEntity(url, RuleDTO[].class);
            return Arrays.asList(response.getBody());

        } catch (Exception e) {
            return new ArrayList<RuleDTO>();
        }
    }

    public List<DataSource> listDataSourcesById(Connection connection) {

        String url = ResourceUtils.getUrl(RESOURCE_NAMING.CMGMT_GET_DEVICE_DATA_SOURCES, cm, connection.getId());

        try {

            ResponseEntity<DataSource[]> response = restTemplate.getForEntity(url, DataSource[].class);

            return Arrays.asList(response.getBody());

        } catch (Exception e) {

            System.out.println(e);

            return new ArrayList<DataSource>();
        }
    }
}
