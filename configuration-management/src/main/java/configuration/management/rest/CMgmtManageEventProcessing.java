package configuration.management.rest;

import java.util.List;

import org.springframework.http.ResponseEntity;

import common.data.ConfigurationDelegation;
import common.data.Connection;
import common.data.DataSource;

/**
 * Interface definition
 * 
 * @author Manuel Filz
 * 
 */
public interface CMgmtManageEventProcessing {

    public ResponseEntity<List<Connection>> getAll();

    public ResponseEntity<Connection> register(Connection connection);

    public ResponseEntity<String> delegate(ConfigurationDelegation data);

    public ResponseEntity<String> heartbeat(Long id);

    public ResponseEntity<List<DataSource>> getDataSources(Long id);

}
