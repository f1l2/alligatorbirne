package configuration.management.rest.activity.call;

import java.net.URL;
import java.util.Iterator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import common.rest.RESOURCE_NAMING;
import common.rest.ResourceUtils;
import configuration.management.model.QueryDLO;
import configuration.management.model.RuleDLO;
import configuration.management.repo.RuleRepository;
import configuration.management.rest.activity.model.RevokeRuleItem;

@Component
public class RevokeRule extends CallActivity<String, RevokeRuleItem> {

    final static Logger logger = LoggerFactory.getLogger(RevokeRule.class);

    @Autowired
    private RuleRepository ruleRepository;

    @Override
    public ResponseEntity<String> doStep(RevokeRuleItem item) {

        URL urlEP = item.getEp().getUrl();

        logger.info("Deactivate rule '{}' at ep '{}'", item.getRule().getName(), urlEP.getAuthority());

        ResponseEntity<String> response = deactivateRule(item.getRule(), urlEP.getAuthority());
        if (response.getStatusCode() != HttpStatus.OK) {
            logger.error("Deactivation of rule '{}' to '{}' failed.", item.getRule().getName(), urlEP.getAuthority());
            return new ResponseEntity<String>("Deactivation of ", HttpStatus.OK);
        }

        deregisterRule(item.getRule(), urlEP.getAuthority());

        Iterator<QueryDLO> iterator = item.getRule().getQueries().iterator();
        while (iterator.hasNext()) {
            QueryDLO queryDLO = iterator.next();
            deregisterQuery(queryDLO, urlEP.getAuthority());
        }

        item.getRule().setQueries(null);
        ruleRepository.save(item.getRule());

        return next("OK", item);
    }

    private void deregisterQuery(QueryDLO queryDLO, String authority) {
        String url = ResourceUtils.getUrl(RESOURCE_NAMING.EP_DEREGISTRATION_QUERY, authority, queryDLO.getName());
        restTemplate.delete(url);
    }

    private void deregisterRule(RuleDLO ruleDLO, String authority) {
        String url = ResourceUtils.getUrl(RESOURCE_NAMING.EP_DEREGISTRATION_RULE, authority, ruleDLO.getName());
        restTemplate.delete(url);
    }

    private ResponseEntity<String> deactivateRule(RuleDLO ruleDLO, String authority) {
        String url = ResourceUtils.getUrl(RESOURCE_NAMING.EP_DEACTIVATIONS_RULE, authority, ruleDLO.getName());
        return restTemplate.getForEntity(url, String.class);
    }
}
