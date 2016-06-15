package configuration.management.rest.impl;

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
import common.selection.DISTRIBUTION_STRATEGY;
import common.transformer.Transformer;
import common.utilities.NormalizeString;
import configuration.management.model.EventProcessingDLO;
import configuration.management.model.RuleDLO;
import configuration.management.repo.EventProcessingTransformer;
import configuration.management.repo.QueryRepository;
import configuration.management.repo.RuleRepository;
import configuration.management.repo.RuleTransformer;
import configuration.management.rest.CMCommon;
import configuration.management.rest.CMManageRule;
import configuration.management.rest.activity.ExecuteRestActivity;
import configuration.management.rest.activity.call.AssignRule;
import configuration.management.rest.activity.call.RevokeRule;
import configuration.management.rest.activity.model.AssignRuleItem;
import configuration.management.rest.activity.model.RevokeRuleItem;
import configuration.management.rest.activity.validate.ValidateAssignRuleItem;
import configuration.management.selection.SelectionFacade;
import configuration.management.statement.RuleLangFactory;

@RestController
public class CMManageRuleImpl extends CMCommon implements CMManageRule {

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
    private SelectionFacade selectionFacade;

    @Autowired
    private ValidateAssignRuleItem validateAssignRuleItem;

    @Autowired
    private AssignRule assignRule;

    @Autowired
    private RevokeRule revokeRule;

    @Autowired
    private EventProcessingTransformer epTransformer;

    @Autowired
    private ThreadPoolTaskExecutor taskExecutor;

    @Override
    @RequestMapping(value = "/registrations/rule/{name}", method = RequestMethod.POST)
    public ResponseEntity<String> registerRule(@PathVariable("name") String name, @RequestBody String rule) {
        logInvokeOfMethod(RESOURCE_NAMING.CM_REGISTRATION_RULE, logger);

        name = NormalizeString.normalize(name);
        rule = NormalizeString.normalize(rule);
        RuleDLO ruleDLO = new RuleDLO(name, rule);

        try {
            validateIsNotEmpty(name, ERROR_CODES.ERROR_MISSING_RULE_NAME);
            validateNotExists(name, ruleRepository, ERROR_CODES.ERROR_EXISTING_RULE);
            RuleLang ruleLang = validateRuleSyntax(rule, ruleLangFactory, ERROR_CODES.ERROR_PARSING_RULE);
            ruleDLO = validateAndFindQueriesToQueryNames(ruleLang, ruleDLO, queryRepository, ERROR_CODES.ERROR_NON_EXISTING_QUERY);
        } catch (ValidationException e) {
            return e.getErrorCode().getErrorResponse();
        }

        ruleRepository.save(ruleDLO);
        return SUCCESS_CODES.OK.getResponse();
    }

    @Override
    @RequestMapping(value = "/deregistrations/rule/{name}", method = RequestMethod.DELETE)
    public ResponseEntity<String> withdrawRule(@PathVariable("name") String name) {
        logInvokeOfMethod(RESOURCE_NAMING.CM_DEREGISTRATION_RULE, logger);

        name = NormalizeString.normalize(name);

        RuleDLO ruleDLO;
        try {
            validateIsNotEmpty(name, ERROR_CODES.ERROR_MISSING_RULE_NAME);
            ruleDLO = validateExists(name, ruleRepository, ERROR_CODES.ERROR_NON_EXISTING_RULE);
            validateRuleIsNotActive(ruleDLO, ERROR_CODES.ERROR_DEREGISTER_ACTIVE);
        } catch (ValidationException e) {
            return e.getErrorCode().getErrorResponse();
        }

        ruleRepository.delete(ruleDLO);
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
    @RequestMapping(value = "/activations/rule/{name}/{strategy}", method = RequestMethod.GET)
    public ResponseEntity<String> activateRule(@PathVariable("name") String name, @PathVariable("strategy") int strategy) {
        logInvokeOfMethod(RESOURCE_NAMING.CM_ACTIVATIONS_RULE_STRATEGY, logger);
        return activateRule(name, DISTRIBUTION_STRATEGY.getByNumber(strategy));
    }

    @Override
    @RequestMapping(value = "/activations/rule/{name}", method = RequestMethod.GET)
    public ResponseEntity<String> activateRule(@PathVariable("name") String name) {
        logInvokeOfMethod(RESOURCE_NAMING.CM_ACTIVATIONS_RULE, logger);
        return activateRule(name, DISTRIBUTION_STRATEGY.MIN_CPU_USAGE);
    }

    private ResponseEntity<String> activateRule(String name, DISTRIBUTION_STRATEGY strategy) {

        name = NormalizeString.normalize(name);

        RuleDLO ruleDLO;
        try {
            validateIsNotEmpty(name, ERROR_CODES.ERROR_MISSING_RULE_NAME);
            ruleDLO = validateExists(name, ruleRepository, ERROR_CODES.ERROR_NON_EXISTING_RULE);
            validateRuleIsNotActive(ruleDLO, ERROR_CODES.ERROR_DEREGISTER_ACTIVE);
        } catch (ValidationException e) {
            return e.getErrorCode().getErrorResponse();
        }

        Optional<EventProcessingDLO> selectedEP = selectionFacade.select(strategy);
        if (!selectedEP.isPresent()) {
            logger.info("No EP was selected.");
            // TODO delay activation, as soon as one EP is registered.
            return SUCCESS_CODES.OK.getResponse();
        } else {
            logger.info("EP selected: {}", selectedEP.get().getAuthority());

            AssignRuleItem assignRuleItem = new AssignRuleItem(ruleDLO, epTransformer.toRemote(selectedEP.get()));
            validateAssignRuleItem.setNextActivity(assignRule);
            taskExecutor.execute(new ExecuteRestActivity<String, AssignRuleItem>(validateAssignRuleItem, assignRuleItem));
            return SUCCESS_CODES.OK.getResponse();
        }
    }

    @Override
    @RequestMapping(value = "/deactivations/rule/{name}", method = RequestMethod.GET)
    public ResponseEntity<String> deactivateRule(@PathVariable("name") String name) {

        logInvokeOfMethod(RESOURCE_NAMING.CM_DEACTIVATIONS_RULE, logger);
        name = NormalizeString.normalize(name);

        RuleDLO ruleDLO;
        try {
            validateIsNotEmpty(name, ERROR_CODES.ERROR_MISSING_RULE_NAME);
            ruleDLO = validateExists(name, ruleRepository, ERROR_CODES.ERROR_NON_EXISTING_RULE);
            validateRuleIsActive(ruleDLO, ERROR_CODES.ERROR_NOT_ACTIVE_RULE);
            validateExistsQuery(ruleDLO, queryRepository, ERROR_CODES.ERROR_NON_EXISTING_QUERY);
        } catch (ValidationException e) {
            return e.getErrorCode().getErrorResponse();
        }

        RevokeRuleItem revokeRuleItem = new RevokeRuleItem(ruleDLO, epTransformer.toRemote(ruleDLO.getEpDLO()));
        taskExecutor.execute(new ExecuteRestActivity<String, RevokeRuleItem>(revokeRule, revokeRuleItem));
        return SUCCESS_CODES.OK.getResponse();
    }
}
