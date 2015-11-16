package monitoring.webapp.service;

import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import common.data.Connection;
import common.data.type.COMPONENT_TYPE;
import common.rest.RESOURCE_NAMING;
import common.rest.ResourceUtils;
import common.rest.UrlUtils;

@Component
public class MonitoringService {

    private RestTemplate restTemplate;

    private Connection cm;

    // @Value("${cm.server.host}")
    private String host = "127.0.0.1";

    // @Value("${cm.server.port}")
    private int port = 5000;

    @PostConstruct
    public void postConstruct() {
        restTemplate = new RestTemplate();

        UrlUtils.parseUrl(host + ":" + Integer.toString(port));

        cm = new Connection();
        cm.setComponentType(COMPONENT_TYPE.CONFIGURATION_MANAGEMENT);
        cm.setName("CM");
        cm.setUrl(UrlUtils.parseUrl(host + ":" + Integer.toString(port)));
    }

    public List<Connection> listEP() {
        String url = ResourceUtils.getUrl(RESOURCE_NAMING.CMGMT_GET_ALL_EVENT_PROCESSING, cm);

        ResponseEntity<Connection[]> response = restTemplate.getForEntity(url, Connection[].class);

        return Arrays.asList(response.getBody());
    }

}
