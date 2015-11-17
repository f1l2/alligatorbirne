package configuration.management.rest;

import java.util.List;

import org.springframework.http.ResponseEntity;

import common.data.dto.QueryDTO;

public interface CMgmtManageQuery {

    public static final String OK = "ok";

    public ResponseEntity<String> registerQuery(String name, String query);

    public ResponseEntity<String> withdrawQuery(String query);

    public List<QueryDTO> getAllQueries();

}
