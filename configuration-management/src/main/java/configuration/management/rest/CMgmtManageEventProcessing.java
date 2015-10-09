package configuration.management.rest;

import java.util.List;

import org.springframework.http.ResponseEntity;

import common.data.ConfigurationDelegation;
import common.data.Connection;

/**
 * Interface definition
 * 
 * @author Manuel Filz
 * 
 */
public interface CMgmtManageEventProcessing {

    public ResponseEntity<List<Connection>> getAll();

    public ResponseEntity<Connection> register(Connection connection);

    public ResponseEntity<ConfigurationDelegation> delegate(Long id, ConfigurationDelegation data);

    public ResponseEntity<Connection> heartbeat(Connection connection);

}
