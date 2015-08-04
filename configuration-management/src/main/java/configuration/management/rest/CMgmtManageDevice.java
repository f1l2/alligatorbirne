package configuration.management.rest;

import java.util.List;

import common.data.Connection;
import common.data.MeasurementData;

/**
 * Interface definition
 * 
 * @author Manuel Filz
 *
 */
public interface CMgmtManageDevice {

    public Connection registerDevice(Connection connection);

    public List<Connection> getAllDevices();

    public void heartBeat(Long id);

    public void registerDeviceSources(Long id, MeasurementData data);

}
