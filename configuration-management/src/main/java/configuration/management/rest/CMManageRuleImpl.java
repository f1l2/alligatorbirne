package configuration.management.rest;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import common.codes.ERROR_CODES;
import common.codes.SUCCESS_CODES;
import common.data.dto.RuleDTO;
import common.exception.ValidationException;
import common.lang.rule.RuleLang;
import common.rest.RESOURCE_NAMING;
import common.transformer.Transformer;
import configuration.management.model.EventProcessingDLO;
import configuration.management.model.RuleDLO;
import configuration.management.repo.EventProcessingTransformer;
import configuration.management.repo.QueryRepository;
import configuration.management.repo.RuleRepository;
import configuration.management.repo.RuleTransformer;
import configuration.management.rest.activity.AssignRule;
import configuration.management.rest.activity.ExecuteRestTask;
import configuration.management.rest.activity.ValidateAssignRuleItem;
import configuration.management.rest.activity.model.AssignRuleItem;
import configuration.management.selection.MinNumberOfActiveRules;
import configuration.management.statement.RuleLangFactory;

@RestController
public class CMManageRuleImpl extends RestCM implements CMManageRule {

    private static final Logger logger = LoggerFactory.getLogger(CMManageRuleImpl.class);

    @Autowired
    private QueryRepository queryRepository;

    @Autowired
    private RuleRepository ruleRepository;

    @Autowired
    private RuleTransformer ruleTransformer;

    @Autowired
    private RuleLangFactory ruleLangFactory;

    @Autowired
    private MinNumberOfActiveRules minNumberOfActiveRules;

    @Autowired
    private ValidateAssignRuleItem validateAssignRuleItem;

    @Autowired
    private AssignRule assignRule;

    @Autowired
    private EventProcessingTransformer epTransformer;

    @Autowired
    private ThreadPoolTaskExecutor taskExecutor;

    @Override
    @RequestMapping(value = "/registrations/rule/{name}", method = RequestMethod.POST)
    public ResponseEntity<String> registerRule(@PathVariable("name") String name, @RequestBody String rule) {

        logInvokeOfMethod(RESOURCE_NAMING.CM_REGISTRATION_RULE, logger);

        RuleLang ruleLang = null;
        RuleDLO ruleDLO = null;

        try {
            validateIsNotEmpty(name, ERROR_CODES.ERROR_MISSING_RULE_NAME);

            validateNotExists(name, ruleRepository, ERROR_CODES.ERROR_EXISTING_RULE);

            ruleLang = validateRuleSyntax(rule, ruleLangFactory, ERROR_CODES.ERROR_PARSING_RULE);

            ruleDLO = validateAndFindQueriesToQueryNames(ruleLang, ruleDLO, queryRepository);

        } catch (ValidationException e) {
            return e.getErrorCode().getErrorResponse();
        }

        ruleDLO.setName(name);
        ruleDLO.setRule(rule);

        ruleRepository.save(ruleDLO);

        return SUCCESS_CODES.OK.getResponse();
    }

    @Override
    @RequestMapping(value = "/deregistrations/rule/{name}", method = RequestMethod.DELETE)
    public ResponseEntity<String> withdrawRule(@PathVariable("name") String name) {

        logInvokeOfMethod(RESOURCE_NAMING.CM_DEREGISTRATION_RULE, logger);

        RuleDLO ruleDLO;
        try {

            validateIsNotEmpty(name, ERROR_CODES.ERROR_MISSING_RULE_NAME);

            ruleDLO = validateExists(name, ruleRepository, ERROR_CODES.ERROR_NON_EXISTING_RULE);

            validateRuleIsNotActive(ruleDLO, ERROR_CODES.ERROR_DEREGISTER_ACTIVE);

        } catch (ValidationException e) {
            return e.getErrorCode().getErrorResponse();
        }

        this.ruleRepository.delete(ruleDLO);

        return SUCCESS_CODES.OK.getResponse();
    }

    @Override
    @RequestMapping(value = "/registrations/rules", method = RequestMethod.GET)
    public @ResponseBody List<RuleDTO> getAllRules() {

        logInvokeOfMethod(RESOURCE_NAMING.CM_GET_ALL_RULES, logger);

        List<RuleDLO> rulesDLO = Transformer.makeCollection(ruleRepository.findAll());

        return ruleTransformer.toRemote(rulesDLO);
    }

    @Override
    @RequestMapping(value = "/activations/rule/{name}", method = RequestMethod.GET)
    public ResponseEntity<String> activateRule(@PathVariable("name") String name) {

        logInvokeOfMethod(RESOURCE_NAMING.CM_ACTIVATIONS_RULE, logger);

        RuleDLO ruleDLO;

        try {
            validateIsNotEmpty(name, ERROR_CODES.ERROR_MISSING_RULE_NAME);

            ruleDLO = validateExists(name, ruleRepository, ERROR_CODES.ERROR_NON_EXISTING_RULE);

            validateRuleIsNotActive(ruleDLO, ERROR_CODES.ERROR_DEREGISTER_ACTIVE);

        } catch (ValidationException e) {
            return e.getErrorCode().getErrorResponse();
        }

        Optional<EventProcessingDLO> selectedEP = minNumberOfActiveRules.select();

        if (!selectedEP.isPresent()) {

            // TODO delay activation, as soon as one EP is registered.

            return SUCCESS_CODES.OK.getResponse();

        } else {

            AssignRuleItem assignRuleItem = new AssignRuleItem();
            assignRuleItem.setEp(epTransformer.toRemote(selectedEP.get()));
            assignRuleItem.setRule(ruleDLO);

            validateAssignRuleItem.setNextActivity(assignRule);

            taskExecutor.execute(new ExecuteRestTask<String, AssignRuleItem>(validateAssignRuleItem, assignRuleItem));

            return SUCCESS_CODES.OK.getResponse();
        }

    }

    @Override
    @RequestMapping(value = "/deactivations/rule/{name}", method = RequestMethod.GET)
    public ResponseEntity<String> deactivateRule(@PathVariable("name") String name) {

        logInvokeOfMethod(RESOURCE_NAMING.CM_DEACTIVATIONS_RULE, logger);

        RuleDLO ruleDLO;

        try {
            validateIsNotEmpty(name, ERROR_CODES.ERROR_MISSING_RULE_NAME);

            ruleDLO = validateExists(name, ruleRepository, ERROR_CODES.ERROR_NON_EXISTING_RULE);

            validateExistsQuery(ruleDLO, queryRepository, ERROR_CODES.ERROR_NON_EXISTING_QUERY);

        } catch (ValidationException e) {
            return e.getErrorCode().getErrorResponse();
        }

        ruleDLO.setQueries(null);

        ruleRepository.save(ruleDLO);

        return SUCCESS_CODES.OK.getResponse();
    }
}
