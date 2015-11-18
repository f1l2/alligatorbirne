package configuration.management.rest;

import java.util.List;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.BaseErrorListener;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.Recognizer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
import common.codes.SUCCESS_CODES;
import common.data.dto.QueryDTO;
import common.gen.language.QueryLexer;
import common.gen.language.QueryParser;
import common.rest.RESOURCE_NAMING;
import common.rest.ResourceUtils;
import common.transformer.Transformer;
import configuration.management.model.QueryDLO;
import configuration.management.repo.QueryRepository;
import configuration.management.repo.QueryTransformer;
import configuration.management.repo.RuleRepository;

@RestController
public class CMManageQueryImpl implements CMManageQuery {

    private static final Logger logger = LoggerFactory.getLogger(CMManageQueryImpl.class);

    @Autowired
    private QueryRepository queryRepository;

    @Autowired
    private QueryTransformer queryTransformer;

    @Autowired
    private RuleRepository ruleRepository;

    @Override
    @RequestMapping(value = "/registrations/query/{name}", method = RequestMethod.POST)
    public ResponseEntity<String> registerQuery(@PathVariable("name") String name, @RequestBody String query) {
        logger.info(ResourceUtils.getLogMessage(RESOURCE_NAMING.CM_REGISTRATION_QUERY));

        /**
         * Make sure that parameter 'name' is not empty or that it isn't already awarded.
         */
        if (StringUtils.isEmpty(name)) {
            return ERROR_CODES.ERROR_MISSING_QUERY_NAME.getErrorResponse();
        } else if ((null != queryRepository.findByName(name))) {
            return ERROR_CODES.ERROR_EXISTING_QUERY.getErrorResponse();
        }

        /**
         * Parse query and store it in the repository.
         */

        try {

            final QueryLexer queryLexer = new QueryLexer(new ANTLRInputStream(query));
            final QueryParser queryParser = new QueryParser(new CommonTokenStream(queryLexer));

            queryParser.addErrorListener(new BaseErrorListener() {
                @Override
                public void syntaxError(Recognizer<?, ?> recognizer, Object offendingSymbol, int line, int charPositionInLine, String msg, RecognitionException e) {
                    throw new IllegalStateException("Failed to parse at line " + line + " due to " + msg, e);
                }
            });
            queryParser.query();

            QueryDLO q = new QueryDLO();
            q.setQuery(query);
            q.setName(name);
            queryRepository.save(q);

        } catch (Exception e) {
            return ERROR_CODES.ERROR_PARSING_QUERY.getErrorResponse();
        }

        return SUCCESS_CODES.OK.getResponse();

    }

    @Override
    @RequestMapping(value = "/deregistrations/query/{name}", method = RequestMethod.DELETE)
    public ResponseEntity<String> withdrawQuery(@PathVariable("name") String name) {
        logger.info(ResourceUtils.getLogMessage(RESOURCE_NAMING.CM_DEREGISTRATION_QUERY));

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
        QueryDLO query = queryRepository.findByName(name);
        if (null == query) {
            return ERROR_CODES.ERROR_NON_EXISTING_QUERY.getErrorResponse();
        }

        /**
         * Make sure that query isn't assigned to a rule.
         * 
         */
        if (!CollectionUtils.isEmpty(ruleRepository.findByQuery(name))) {
            return ERROR_CODES.ERROR_DEREGISTER_ASSIGNED.getErrorResponse();
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
