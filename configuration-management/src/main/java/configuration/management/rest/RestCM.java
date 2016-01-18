package configuration.management.rest;

import java.util.HashSet;
import java.util.Set;

import org.springframework.util.CollectionUtils;

import common.codes.ERROR_CODES;
import common.exception.ValidationException;
import common.lang.query.QueryLang;
import common.lang.rule.RuleLang;
import common.rest.RestCommon;
import configuration.management.model.QueryDLO;
import configuration.management.model.RuleDLO;
import configuration.management.repo.QueryRepository;
import configuration.management.repo.RuleRepository;
import configuration.management.statement.QueryLangFactory;
import configuration.management.statement.RuleLangFactory;

/**
 * @author Manuel
 *
 */
public class RestCM extends RestCommon {

    /**
     * Method checks if rule is active.
     * 
     * @param rule
     * @param errorCode
     * @throws ValidationException
     */
    protected void validateRuleIsNotActive(RuleDLO rule, ERROR_CODES errorCode) throws ValidationException {

        if (null != rule.getEpDLO()) {
            throw new ValidationException(errorCode);
        }
    }

    protected void validateExistsQuery(RuleDLO rule, QueryRepository qr, ERROR_CODES errorCode) throws ValidationException {

        for (QueryDLO queryDLO : rule.getQueries()) {
            validateExists(queryDLO.getName(), qr, errorCode);
        }
    }

    protected QueryLang validateQuerySyntax(String query, String name, QueryLangFactory queryLangFactory, ERROR_CODES errorCode) throws ValidationException {
        try {
            return queryLangFactory.parse(query, name);

        } catch (Exception e) {
            throw new ValidationException(errorCode);
        }
    }

    protected RuleLang validateRuleSyntax(String rule, RuleLangFactory ruleLangFactory, ERROR_CODES errorCode) throws ValidationException {
        try {
            return ruleLangFactory.parse(rule);
        } catch (Exception e) {
            throw new ValidationException(errorCode);
        }
    }

    protected void validateQueryIsNotAssigned(String name, RuleRepository ruleRepository, ERROR_CODES errorCode) throws ValidationException {
        if (!CollectionUtils.isEmpty(ruleRepository.findByQuery(name))) {
            throw new ValidationException(errorCode);
        }
    }

    protected RuleDLO validateAndFindQueriesToQueryNames(RuleLang langRule, RuleDLO rule, QueryRepository qr) throws ValidationException {

        Set<QueryDLO> queries = new HashSet<QueryDLO>();
        for (String queryName : langRule.getQueryNames()) {
            QueryDLO query = qr.findByName(queryName);
            if (null != query) {
                queries.add(query);
            } else {
                throw new ValidationException();
            }
        }
        rule.setQueries(queries);

        return rule;
    }

}
