package configuration.management.rest;

import java.util.List;

import org.springframework.http.ResponseEntity;

import common.data.Connection;
import common.data.DataSources;

/**
 * Interface definition
 * 
 * @author Manuel Filz
 *
 */
public interface CMgmtManageIoTDevice {

    public ResponseEntity<Connection> register(Connection connection);

    public ResponseEntity<List<Connection>> getAll();

    public ResponseEntity<String> heartBeat(Long id);

    public ResponseEntity<String> registerDataSources(Long id, DataSources data);

}
