package configuration.management.rest;

import java.util.List;

import org.springframework.http.ResponseEntity;

import common.data.dto.RuleDTO;

public interface CMgmtManageRule {

    public static final String OK = "ok";

    public ResponseEntity<String> registerRule(String name, String rule);

    public ResponseEntity<String> withdrawRule(String rule);

    public List<RuleDTO> getAllRules();

}
