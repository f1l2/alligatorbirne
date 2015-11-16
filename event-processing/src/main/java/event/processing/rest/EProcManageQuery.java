package event.processing.rest;

import java.util.List;

import org.springframework.http.ResponseEntity;

import event.processing.query.Query;

public interface EProcManageQuery {

    public static final String OK = "ok";

    public ResponseEntity<String> registerQuery(String name, String query);

    public ResponseEntity<String> withdrawQuery(String query);

    public List<Query> getAllQueries();

}
