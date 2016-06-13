package configuration.management.rest;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import common.codes.ERROR_CODES;
import common.codes.SUCCESS_CODES;
import common.data.dto.QueryDTO;
import common.exception.ValidationException;
import common.rest.RESOURCE_NAMING;
import common.rest.ResourceUtils;
import common.transformer.Transformer;
import common.utilities.NormalizeString;
import configuration.management.model.QueryDLO;
import configuration.management.repo.QueryRepository;
import configuration.management.repo.QueryTransformer;
import configuration.management.repo.RuleRepository;
import configuration.management.statement.QueryLangFactory;

@RestController
public class CMManageQueryImpl extends RestCM implements CMManageQuery {

    private static final Logger logger = LoggerFactory.getLogger(CMManageQueryImpl.class);

    @Autowired
    private QueryRepository queryRepository;

    @Autowired
    private QueryTransformer queryTransformer;

    @Autowired
    private QueryLangFactory queryLangFactory;

    @Autowired
    private RuleRepository ruleRepository;

    @Override
    @RequestMapping(value = "/registrations/query/{name}", method = RequestMethod.POST)
    public ResponseEntity<String> registerQuery(@PathVariable("name") String name, @RequestBody String query) {

        logInvokeOfMethod(RESOURCE_NAMING.CM_REGISTRATION_QUERY, logger);

        name = NormalizeString.normalize(name);
        query = NormalizeString.normalize(query);

        try {
            validateIsNotEmpty(name, ERROR_CODES.ERROR_MISSING_QUERY_NAME);

            validateNotExists(name, queryRepository, ERROR_CODES.ERROR_EXISTING_QUERY);

            validateQuerySyntax(query, name, queryLangFactory, ERROR_CODES.ERROR_PARSING_QUERY);

        } catch (ValidationException e) {
            return e.getErrorCode().getErrorResponse();
        }

        QueryDLO q = new QueryDLO();
        q.setQuery(query);
        q.setName(name);
        queryRepository.save(q);

        return SUCCESS_CODES.OK.getResponse();
    }

    @Override
    @RequestMapping(value = "/deregistrations/query/{name}", method = RequestMethod.DELETE)
    public ResponseEntity<String> withdrawQuery(@PathVariable("name") String name) {

        logInvokeOfMethod(RESOURCE_NAMING.CM_DEREGISTRATION_QUERY, logger);

        name = NormalizeString.normalize(name);

        QueryDLO query = null;

        try {
            validateIsNotEmpty(name, ERROR_CODES.ERROR_MISSING_QUERY_NAME);

            query = validateExists(name, queryRepository, ERROR_CODES.ERROR_NON_EXISTING_QUERY);

            validateQueryIsNotAssigned(name, ruleRepository, ERROR_CODES.ERROR_DEREGISTER_ASSIGNED);

        } catch (ValidationException e) {
            return e.getErrorCode().getErrorResponse();
        }

        queryRepository.delete(query);
        return SUCCESS_CODES.OK.getResponse();
    }

    @Override
    @RequestMapping(value = "/registrations/queries", method = RequestMethod.GET)
    public @ResponseBody List<QueryDTO> getAllQueries() {
        logger.info(ResourceUtils.getLogMessage(RESOURCE_NAMING.CM_GET_ALL_QUERIES));

        List<QueryDLO> locals = Transformer.makeCollection(queryRepository.findAll());

        return queryTransformer.toRemote(locals);
    }
}
