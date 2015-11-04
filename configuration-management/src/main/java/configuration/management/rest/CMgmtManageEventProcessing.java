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

    public ResponseEntity<List<DataSource>> getDataSources(Long id);

    public ResponseEntity<Connection> register(Connection connection);

    public ResponseEntity<String> registerDataSources(ConfigurationDelegation body);

    public ResponseEntity<String> deregisterDataSources(ConfigurationDelegation body);

    public ResponseEntity<String> delegate(ConfigurationDelegation body);

    public ResponseEntity<String> heartbeat(Long id);

}
