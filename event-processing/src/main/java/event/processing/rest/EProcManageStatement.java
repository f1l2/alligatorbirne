package event.processing.rest;

import java.util.List;

import org.springframework.http.ResponseEntity;

import event.processing.query.Query;
import event.processing.rule.Rule;

public interface EProcManageStatement {

    public static final String OK = "ok";

    public ResponseEntity<String> registerQuery(String name, String query);

    public ResponseEntity<String> withdrawQuery(String query);

    public ResponseEntity<String> registerRule(String name, String rule);

    public ResponseEntity<String> withdrawRule(String rule);

    public ResponseEntity<String> activateRule(String name);

    public ResponseEntity<String> deactivateRule(String name);

    public List<Query> getAllQueries();

    public List<Rule> getAllRules();

}
