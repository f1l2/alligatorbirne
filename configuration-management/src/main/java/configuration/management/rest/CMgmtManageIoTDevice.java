package configuration.management.rest;

import java.util.List;

import common.data.Connection;
import common.data.DataSources;

/**
 * Interface definition
 * 
 * @author Manuel Filz
 *
 */
public interface CMgmtManageIoTDevice {

    public Connection register(Connection connection);

    public List<Connection> getAll();

    public void heartBeat(Long id);

    public void registerDataSources(Long id, DataSources data);

}
