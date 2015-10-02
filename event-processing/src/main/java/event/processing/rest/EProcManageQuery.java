package event.processing.rest;

import java.util.List;

import org.springframework.http.ResponseEntity;

import event.processing.query.Query;
import event.processing.rule.Rule;

public interface EProcManageQuery {

    public static final String OK = "ok";

    public static final String ERROR_MISSING_QUERY_NAME = "Missing query name.";

    public static final String ERROR_EXISTING_QUERY = "Query with the same name already exists.";

    public static final String ERROR_PARSING_QUERY = "Couldn't parse query. Please check syntax.";

    public static final String ERROR_MISSING_RULE_NAME = "Missing rule name.";

    public static final String ERROR_EXISTING_RULE = "Rule with the same name already exists.";

    public static final String ERROR_PARSING_RULE = "Couldn't parse rule. Please check syntax.";

    public static final String ERROR_NON_EXISTING_QUERY = "Query with given name isn't registered.";

    public static final String ERROR_NON_EXISTING_RULE = "Rule with given name isn't registered.";

    public static final String ERROR_REGISTER_QUERY = "Registration of statement at event engine failed.";

    public ResponseEntity<String> registerQuery(String name, String query);

    public ResponseEntity<String> unregisterQuery(String query);

    public ResponseEntity<String> registerRule(String name, String rule);

    public ResponseEntity<String> unregisterRule(String rule);

    public ResponseEntity<String> activateRule(String name);

    public List<Query> getAllQueries();

    public List<Rule> getAllRules();

}
