package configuration.management.rest.activity.call;

import java.net.URL;
import java.util.Iterator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;

import common.rest.RESOURCE_NAMING;
import common.rest.ResourceUtils;
import configuration.management.model.EventProcessingDLO;
import configuration.management.model.QueryDLO;
import configuration.management.model.RuleDLO;
import configuration.management.rest.activity.model.AssignRuleItem;

@Component
public class AssignRule extends CallActivity<String, AssignRuleItem> {

    final static Logger logger = LoggerFactory.getLogger(AssignRule.class);

    @Override
    public ResponseEntity<String> doStep(AssignRuleItem item) {

        URL urlEP = item.getEp().getUrl();

        logger.info("Assign rule to ep: {}", urlEP.getAuthority());

        Iterator<QueryDLO> iterator = item.getRule().getQueries().iterator();

        while (iterator.hasNext()) {
            QueryDLO queryDLO = iterator.next();
            assignQuery(queryDLO, urlEP.getAuthority());
        }

        assignRule(item.getRule(), urlEP.getAuthority());
        activateRule(item.getRule(), urlEP.getAuthority());

        EventProcessingDLO eventProcessingDLO = eventProcessingRepository.findByAuthority(urlEP.getAuthority());
        item.getRule().setEpDLO(eventProcessingDLO);
        ruleRepository.save(item.getRule());

        return next("OK", item);
    }

    private void assignQuery(QueryDLO queryDLO, String authority) {

        try {
            String url = ResourceUtils.getUrl(RESOURCE_NAMING.EP_REGISTRATION_QUERY, authority, queryDLO.getName());
            restTemplate.postForEntity(url, queryDLO.getQuery(), String.class);
        } catch (HttpClientErrorException e) {
            logger.error("Registration of query '{}' to '{}' failed.", queryDLO.getName(), authority);
        }
    }

    private void assignRule(RuleDLO ruleDLO, String authority) {

        try {
            String url = ResourceUtils.getUrl(RESOURCE_NAMING.EP_REGISTRATION_RULE, authority, ruleDLO.getName());
            restTemplate.postForEntity(url, ruleDLO.getRule(), String.class);
        } catch (HttpClientErrorException e) {
            logger.error("Registration of rule '{}' to '{}' failed.", ruleDLO.getName(), authority);
        }
    }

    private void activateRule(RuleDLO ruleDLO, String authority) {

        try {
            String url = ResourceUtils.getUrl(RESOURCE_NAMING.EP_ACTIVATIONS_RULE, authority, ruleDLO.getName());
            restTemplate.getForEntity(url, String.class);
        } catch (HttpClientErrorException e) {
            logger.error("Activation of rule '{}' to '{}' failed.", ruleDLO.getName(), authority);
        }
    }
}
