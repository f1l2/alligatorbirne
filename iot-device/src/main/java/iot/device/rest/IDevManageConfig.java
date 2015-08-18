package iot.device.rest;

import iot.device.repo.DeliveryTaskRO;

import java.util.List;

import common.data.ConfigurationModification;

/**
 * Interface definition
 * 
 * @author Manuel Filz
 * 
 */
public interface IDevManageConfig {

    public List<ConfigurationModification> getAllConfiguration();

    public DeliveryTaskRO getConfigurationByEventProcessingId(Long id);

    public void setConfiguration(ConfigurationModification configurationModification);

    public void setConfiguration1();

}
