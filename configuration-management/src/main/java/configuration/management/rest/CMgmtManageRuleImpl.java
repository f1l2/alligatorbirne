package configuration.management.rest;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import common.data.dto.RuleDTO;
import common.error.codes.EP_ERROR_CODES;
import common.rest.RESOURCE_NAMING;
import common.rest.ResourceUtils;
import configuration.management.model.Rule;
import configuration.management.repo.QueryRepository;
import configuration.management.repo.RuleRepository;

@RestController
public class CMgmtManageRuleImpl implements CMgmtManageRule {

    private static final Logger logger = LoggerFactory.getLogger(CMgmtManageRuleImpl.class);

    @Autowired
    private QueryRepository queryRepository;

    @Autowired
    private RuleRepository ruleRepository;

    @Override
    @RequestMapping(value = "/registrations/rule/{name}", method = RequestMethod.POST)
    public ResponseEntity<String> registerRule(@PathVariable("name") String name, @RequestBody String rule) {
        logger.info(ResourceUtils.getLogMessage(RESOURCE_NAMING.EPROCESSING_REGISTRATION_RULE));

        /**
         * Make sure that parameter 'name' is not empty or that it isn't already awarded.
         */
        if (StringUtils.isEmpty(name)) {
            return EP_ERROR_CODES.ERROR_MISSING_RULE_NAME.getErrorResponse();
        } else if ((null != ruleRepository.findByName(name))) {
            return EP_ERROR_CODES.ERROR_EXISTING_RULE.getErrorResponse();
        }

        /**
         * Store rule in repository.
         */
        Rule r = new Rule();
        r.setName(name);
        r.setRule(rule);

        /**
         * Find query to name. If query doesn't exist throw exception.
         */

        try {
            Utilities.findQueriesToQueryNames(r, queryRepository);
        } catch (Exception e) {
            return EP_ERROR_CODES.ERROR_NON_EXISTING_QUERY.getErrorResponse();
        }

        ruleRepository.save(r);

        return new ResponseEntity<String>(OK, HttpStatus.OK);
    }

    @Override
    @RequestMapping(value = "/deregistrations/rule/{name}", method = RequestMethod.DELETE)
    public ResponseEntity<String> withdrawRule(@PathVariable("name") String name) {
        logger.info(ResourceUtils.getLogMessage(RESOURCE_NAMING.EPROCESSING_DEREGISTRATION_RULE));

        /**
         * Make sure that parameter 'name' is not empty.
         */
        if (StringUtils.isEmpty(name)) {
            return EP_ERROR_CODES.ERROR_MISSING_RULE_NAME.getErrorResponse();
        }

        /**
         * Make sure that rule with given name exists.
         */
        Rule rule = ruleRepository.findByName(name);
        if (null == rule) {
            return EP_ERROR_CODES.ERROR_NON_EXISTING_RULE.getErrorResponse();
        }

        /**
         * Before deregistration make sure that rule is not active.
         */
        if (rule.getIsActive()) {
            return EP_ERROR_CODES.ERROR_DEREGISTER_ACTIVE.getErrorResponse();
        }

        this.ruleRepository.delete(rule);

        return new ResponseEntity<String>(OK, HttpStatus.OK);
    }

    @Override
    @RequestMapping(value = "/registrations/rules", method = RequestMethod.GET)
    public @ResponseBody List<RuleDTO> getAllRules() {
        logger.info(ResourceUtils.getLogMessage(RESOURCE_NAMING.EPROCESSING_GET_ALL_RULES));

        return null;
    }
}
