package event.processing.rest;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import common.codes.ERROR_CODES;
import common.data.dto.QueryDTO;
import common.rest.RESOURCE_NAMING;
import common.rest.ResourceUtils;
import event.processing.engine.EngineFactory;
import event.processing.query.Query;
import event.processing.query.QueryFactory;
import event.processing.query.QueryTransformer;
import event.processing.repo.QueryRepository;
import event.processing.repo.RuleRepository;

@RestController
public class EPManageQueryImpl implements EPManageQuery {

    private static final Logger logger = LoggerFactory.getLogger(EPManageQueryImpl.class);

    @Autowired
    @Qualifier("esper")
    private EngineFactory factory;

    @Autowired
    private QueryRepository queryRepository;

    @Autowired
    private RuleRepository ruleRepository;

    @Autowired
    private QueryFactory queryFactory;

    @Autowired
    private QueryTransformer queryTransformer;

    @Override
    @RequestMapping(value = "/registrations/query/{name}", method = RequestMethod.POST)
    public ResponseEntity<String> registerQuery(@PathVariable("name") String name, @RequestBody String query) {
        logger.info(ResourceUtils.getLogMessage(RESOURCE_NAMING.EP_REGISTRATION_QUERY));

        /**
         * Make sure that parameter 'name' is not empty or that it isn't already awarded.
         */
        if (StringUtils.isEmpty(name)) {
            return ERROR_CODES.ERROR_MISSING_QUERY_NAME.getErrorResponse();
        } else if ((null != queryRepository.findOne(name))) {
            return ERROR_CODES.ERROR_EXISTING_QUERY.getErrorResponse();
        }

        /**
         * Parse query and store it in the repository.
         */
        try {
            Query q = queryFactory.parse(query, name);
            q.setNativeQuery(query);
            queryRepository.save(q);

        } catch (Exception e) {
            return ERROR_CODES.ERROR_PARSING_QUERY.getErrorResponse();
        }

        return new ResponseEntity<String>(OK, HttpStatus.OK);

    }

    @Override
    @RequestMapping(value = "/deregistrations/query/{name}", method = RequestMethod.DELETE)
    public ResponseEntity<String> withdrawQuery(@PathVariable("name") String name) {
        logger.info(ResourceUtils.getLogMessage(RESOURCE_NAMING.EP_DEREGISTRATION_QUERY));

        /**
         * Make sure that parameter 'name' is not empty.
         */
        if (StringUtils.isEmpty(name)) {
            return ERROR_CODES.ERROR_MISSING_QUERY_NAME.getErrorResponse();
        }

        /**
         * Make sure that corresponding query exists.
         * 
         */
        Query query = queryRepository.findOne(name);
        if (null == query) {
            return ERROR_CODES.ERROR_NON_EXISTING_QUERY.getErrorResponse();
        }

        /**
         * Make sure that query isn't assigned to a rule.
         * 
         */
        if (!CollectionUtils.isEmpty(ruleRepository.findRulesByQueryName(name))) {
            return ERROR_CODES.ERROR_DEREGISTER_ASSIGNED.getErrorResponse();
        }

        ruleRepository.delete(name);
        return new ResponseEntity<String>(OK, HttpStatus.OK);
    }

    @Override
    @RequestMapping(value = "/registrations/queries", method = RequestMethod.GET)
    public @ResponseBody List<QueryDTO> getAllQueries() {
        logger.info(ResourceUtils.getLogMessage(RESOURCE_NAMING.EP_GET_ALL_QUERIES));

        return queryTransformer.toRemote(queryRepository.findAll());
    }
}
