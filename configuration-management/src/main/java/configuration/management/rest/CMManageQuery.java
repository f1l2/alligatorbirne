package configuration.management.rest;

import java.util.List;

import org.springframework.http.ResponseEntity;

import common.data.dto.QueryDTO;

/**
 * Interface for managing queries.
 * 
 * The interface follows the REST style.
 * 
 * @author Manuel Filz
 *
 */
public interface CMManageQuery {

    public ResponseEntity<String> registerQuery(String name, String query);

    public ResponseEntity<String> withdrawQuery(String query);

    public List<QueryDTO> getAllQueries();

}
