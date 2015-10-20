package configuration.management.rest;

import java.util.List;

import org.springframework.http.ResponseEntity;

import common.data.Connection;
import common.data.DataSource;
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

    public ResponseEntity<String> registerDataSources(Long id, DataSources data);

    public ResponseEntity<String> heartbeat(Long id);

    public ResponseEntity<List<DataSource>> getDataSources(Long id);

    public ResponseEntity<List<Connection>> getDeviceByDataSource(String devInfo, String domainInfo);

}
