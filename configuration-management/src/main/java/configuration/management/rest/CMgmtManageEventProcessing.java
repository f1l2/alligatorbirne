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

    public List<Connection> getAll();

    public Connection register(Connection connection);

    public ResponseEntity<String> heartBeat(Long id);

    public ResponseEntity<String> delegate(Long id, ConfigurationDelegation data);

}
