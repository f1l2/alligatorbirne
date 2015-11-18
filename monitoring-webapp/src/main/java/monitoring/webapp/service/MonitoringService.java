package monitoring.webapp.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
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
        String url = ResourceUtils.getUrl(RESOURCE_NAMING.CM_GET_ALL_EVENT_PROCESSING, cm);

        try {
            ResponseEntity<Connection[]> response = restTemplate.getForEntity(url, Connection[].class);
            return Arrays.asList(response.getBody());
        } catch (Exception e) {
            return new ArrayList<Connection>();
        }
    }

    public List<Connection> listDev() {

        String url = ResourceUtils.getUrl(RESOURCE_NAMING.CM_GET_ALL_DEVICES, cm);

        try {
            ResponseEntity<Connection[]> response = restTemplate.getForEntity(url, Connection[].class);
            return Arrays.asList(response.getBody());
        } catch (Exception e) {
            return new ArrayList<Connection>();
        }
    }

    public List<QueryDTO> listRegisteredQuery() {
        return listRegisteredQuery(cm, RESOURCE_NAMING.CM_GET_ALL_QUERIES);
    }

    public List<QueryDTO> listRegisteredQuery(Connection connection) {
        return listRegisteredQuery(connection, RESOURCE_NAMING.EP_GET_ALL_QUERIES);
    }

    public List<QueryDTO> listRegisteredQuery(Connection connection, RESOURCE_NAMING resource) {

        String url = ResourceUtils.getUrl(resource, connection);

        try {
            ResponseEntity<QueryDTO[]> response = restTemplate.getForEntity(url, QueryDTO[].class);
            return Arrays.asList(response.getBody());

        } catch (Exception e) {
            return new ArrayList<QueryDTO>();
        }
    }

    public List<RuleDTO> listRegisteredRule() {
        return listRegisteredRule(cm, RESOURCE_NAMING.CM_GET_ALL_RULES);
    }

    public List<RuleDTO> listRegisteredRule(Connection connection) {
        return listRegisteredRule(connection, RESOURCE_NAMING.EP_GET_ALL_RULES);
    }

    public List<RuleDTO> listRegisteredRule(Connection connection, RESOURCE_NAMING resource) {

        String url = ResourceUtils.getUrl(resource, connection);

        System.out.println(url);

        try {

            System.out.println("HERE");

            ResponseEntity<RuleDTO[]> response = restTemplate.getForEntity(url, RuleDTO[].class);

            System.out.println("HERE1");

            return Arrays.asList(response.getBody());

        } catch (Exception e) {

            System.out.println(e);

            return new ArrayList<RuleDTO>();
        }
    }

    public List<DataSource> listDataSourcesById(Connection connection) {

        String url = ResourceUtils.getUrl(RESOURCE_NAMING.CM_GET_DEVICE_DATA_SOURCES, cm, connection.getId());

        try {

            ResponseEntity<DataSource[]> response = restTemplate.getForEntity(url, DataSource[].class);

            return Arrays.asList(response.getBody());

        } catch (Exception e) {

            return new ArrayList<DataSource>();
        }
    }

    public String registerQuery(final String queryName, final String query) {

        String url = ResourceUtils.getUrl(RESOURCE_NAMING.CM_REGISTRATION_QUERY, cm);
        url = StringUtils.replace(url, "{name}", queryName);

        ResponseEntity<String> postForEntity = restTemplate.postForEntity(url, query, String.class);

        return postForEntity.getBody();

    }

    public String registerRule(final String ruleName, final String rule) {

        String url = ResourceUtils.getUrl(RESOURCE_NAMING.CM_REGISTRATION_RULE, cm);
        url = StringUtils.replace(url, "{name}", ruleName);

        ResponseEntity<String> postForEntity = restTemplate.postForEntity(url, rule, String.class);

        return postForEntity.getBody();
    }

    public void deregisterQuery(QueryDTO query) {

        String url = ResourceUtils.getUrl(RESOURCE_NAMING.CM_DEREGISTRATION_QUERY, cm);
        url = StringUtils.replace(url, "{name}", query.getName());

        restTemplate.delete(url);

    }

    public void deregisterRule(RuleDTO rule) {

        String url = ResourceUtils.getUrl(RESOURCE_NAMING.CM_DEREGISTRATION_RULE, cm);
        url = StringUtils.replace(url, "{name}", rule.getName());

        restTemplate.delete(url);
    }

    public String activateRule(RuleDTO rule) {
        String url = ResourceUtils.getUrl(RESOURCE_NAMING.CM_ACTIVATIONS_RULE, cm, rule.getName());

        ResponseEntity<String> result = restTemplate.getForEntity(url, String.class);

        return result.getBody();

    }

    public String deactivateRule(RuleDTO rule) {
        String url = ResourceUtils.getUrl(RESOURCE_NAMING.CM_DEACTIVATIONS_RULE, cm, rule.getName());

        ResponseEntity<String> result = restTemplate.getForEntity(url, String.class);

        return result.getBody();
    }

}
