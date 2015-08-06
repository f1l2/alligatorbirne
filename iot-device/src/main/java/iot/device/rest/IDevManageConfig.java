package iot.device.rest;

import iot.device.repo.InstructionJPA;

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

    public InstructionJPA getConfigurationByEP(Long id);

    public void setConfiguration(ConfigurationModification configurationModification);

    public void setConfiguration1();

}
