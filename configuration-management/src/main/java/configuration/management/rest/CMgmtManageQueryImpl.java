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

import common.data.dto.QueryDTO;
import common.error.codes.EP_ERROR_CODES;
import common.rest.RESOURCE_NAMING;
import common.rest.ResourceUtils;
import configuration.management.model.Query;
import configuration.management.repo.QueryRepository;

@RestController
public class CMgmtManageQueryImpl implements CMgmtManageQuery {

    private static final Logger logger = LoggerFactory.getLogger(CMgmtManageQueryImpl.class);

    @Autowired
    private QueryRepository queryRepository;

    @Override
    @RequestMapping(value = "/registrations/query/{name}", method = RequestMethod.POST)
    public ResponseEntity<String> registerQuery(@PathVariable("name") String name, @RequestBody String query) {
        logger.info(ResourceUtils.getLogMessage(RESOURCE_NAMING.EPROCESSING_REGISTRATION_QUERY));

        /**
         * Make sure that parameter 'name' is not empty or that it isn't already awarded.
         */
        if (StringUtils.isEmpty(name)) {
            return EP_ERROR_CODES.ERROR_MISSING_QUERY_NAME.getErrorResponse();
        } else if ((null != queryRepository.findByName(name))) {
            return EP_ERROR_CODES.ERROR_EXISTING_QUERY.getErrorResponse();
        }

        /**
         * Parse query and store it in the repository.
         */
        try {
            Query q = new Query();
            q.setQuery(query);
            q.setName(name);
            queryRepository.save(q);

        } catch (Exception e) {
            return EP_ERROR_CODES.ERROR_PARSING_QUERY.getErrorResponse();
        }

        return new ResponseEntity<String>(OK, HttpStatus.OK);

    }

    @Override
    @RequestMapping(value = "/deregistrations/query/{name}", method = RequestMethod.DELETE)
    public ResponseEntity<String> withdrawQuery(@PathVariable("name") String name) {
        logger.info(ResourceUtils.getLogMessage(RESOURCE_NAMING.EPROCESSING_DEREGISTRATION_QUERY));

        /**
         * Make sure that parameter 'name' is not empty.
         */
        if (StringUtils.isEmpty(name)) {
            return EP_ERROR_CODES.ERROR_MISSING_QUERY_NAME.getErrorResponse();
        }

        /**
         * Make sure that corresponding query exists.
         * 
         */
        Query query = queryRepository.findByName(name);
        if (null == query) {
            return EP_ERROR_CODES.ERROR_NON_EXISTING_QUERY.getErrorResponse();
        }

        /**
         * Make sure that query isn't assigned to a rule.
         * 
         */

        // TODO

        // if (!CollectionUtils.isEmpty(ruleRepository.findRulesByQueryName(name))) {
        // return EP_ERROR_CODES.ERROR_DEREGISTER_ASSIGNED.getErrorResponse();
        // }

        queryRepository.delete(query);
        return new ResponseEntity<String>(OK, HttpStatus.OK);
    }

    @Override
    @RequestMapping(value = "/registrations/queries", method = RequestMethod.GET)
    public @ResponseBody List<QueryDTO> getAllQueries() {
        logger.info(ResourceUtils.getLogMessage(RESOURCE_NAMING.EPROCESSING_GET_ALL_QUERIES));

        return null;
    }
}
