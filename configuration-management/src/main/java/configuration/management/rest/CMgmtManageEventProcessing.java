package configuration.management.rest;

import java.util.List;

import org.springframework.http.ResponseEntity;

import common.data.ConfigurationDelegation;
import common.data.Connection;
import common.data.DataSource;
import common.data.dto.DataSourcesDTO;

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

    public ResponseEntity<String> registerDataSources(Long id, DataSourcesDTO data);

    public ResponseEntity<String> deregisterDataSources(Long id, DataSourcesDTO dataSources);

    public ResponseEntity<String> delegate(ConfigurationDelegation data);

    public ResponseEntity<String> heartbeat(Long id);

}
