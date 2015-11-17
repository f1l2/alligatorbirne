package event.processing.rest;

import java.util.List;

import org.springframework.http.ResponseEntity;

import common.data.dto.RuleDTO;

public interface EProcManageRule {

    public static final String OK = "ok";

    public ResponseEntity<String> registerRule(String name, String rule);

    public ResponseEntity<String> withdrawRule(String rule);

    public ResponseEntity<String> activateRule(String name);

    public ResponseEntity<String> deactivateRule(String name);

    public List<RuleDTO> getAllRules();

}
