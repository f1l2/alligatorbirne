package configuration.management.rest;

import java.util.List;

import common.data.ConnectionProperties;
import common.data.MeasurementData;

/**
 * Interface definition
 * 
 * @author Manuel Filz
 *
 */
public interface CMgmtManageDevice {

    public ConnectionProperties registerDevice(ConnectionProperties connection);

    public List<ConnectionProperties> getAllDevices();

    public void heartBeat(Long id);

    public void registerDeviceSources(Long id, MeasurementData data);

}
