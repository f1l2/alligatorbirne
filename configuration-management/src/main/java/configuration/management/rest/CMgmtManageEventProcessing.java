package configuration.management.rest;

import java.util.List;

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

    public void heartBeat(Long id);

    public void delegate(Long id, ConfigurationDelegation data);

}
