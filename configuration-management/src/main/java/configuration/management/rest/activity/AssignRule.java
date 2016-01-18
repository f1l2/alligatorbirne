package configuration.management.rest.activity;

import java.net.URL;
import java.util.Iterator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import common.rest.RESOURCE_NAMING;
import common.rest.ResourceUtils;
import configuration.management.model.QueryDLO;
import configuration.management.model.RuleDLO;
import configuration.management.rest.activity.model.AssignRuleItem;

@Component
public class AssignRule extends Activity<String, AssignRuleItem> {

    final static Logger logger = LoggerFactory.getLogger(AssignRule.class);

    private RestTemplate restTemplate = new RestTemplate();

    @Override
    public ResponseEntity<String> doStep(AssignRuleItem item) {

        ResponseEntity<String> response = null;
        URL urlEP = item.getEp().getUrl();

        logger.info("Assign rule to ep: {}", urlEP.getAuthority());

        Iterator<QueryDLO> iterator = item.getRule().getQueries().iterator();

        while (iterator.hasNext()) {

            QueryDLO queryDLO = iterator.next();
            response = assignQuery(queryDLO, urlEP.getAuthority());

            if (response.getStatusCode() != HttpStatus.OK) {
                logger.error("Registration of query '{}' to '{}' failed.", queryDLO.getName(), urlEP.getAuthority());
            }
        }

        response = assignRule(item.getRule(), urlEP.getAuthority());
        if (response.getStatusCode() != HttpStatus.OK) {
            logger.error("Registration of rule '{}' to '{}' failed.", item.getRule().getName(), urlEP.getAuthority());
        }

        response = activateRule(item.getRule(), urlEP.getAuthority());
        if (response.getStatusCode() != HttpStatus.OK) {
            logger.error("Activation of rule '{}' to '{}' failed.", item.getRule().getName(), urlEP.getAuthority());
        }

        return next("OK", item);
    }

    private ResponseEntity<String> assignQuery(QueryDLO queryDLO, String authority) {
        String url = ResourceUtils.getUrl(RESOURCE_NAMING.EP_REGISTRATION_QUERY, authority, queryDLO.getName());

        return restTemplate.postForEntity(url, queryDLO.getQuery(), String.class);
    }

    private ResponseEntity<String> assignRule(RuleDLO ruleDLO, String authority) {

        String url = ResourceUtils.getUrl(RESOURCE_NAMING.EP_REGISTRATION_RULE, authority, ruleDLO.getName());
        return restTemplate.postForEntity(url, ruleDLO.getRule(), String.class);
    }

    private ResponseEntity<String> activateRule(RuleDLO ruleDLO, String authority) {

        String url = ResourceUtils.getUrl(RESOURCE_NAMING.EP_ACTIVATIONS_RULE, authority, ruleDLO.getName());
        return restTemplate.postForEntity(url, ruleDLO.getRule(), String.class);
    }

}
