package iot.device.rest;

import java.util.List;

import org.springframework.http.ResponseEntity;

import common.data.ConfigurationModification;
import iot.device.repo.DeliveryTaskRO;

/**
 * Interface definition
 * 
 * @author Manuel Filz
 * 
 */
public interface IDevManageConfig {

    public ResponseEntity<List<DeliveryTaskRO>> getAllConfiguration();

    public ResponseEntity<DeliveryTaskRO> getConfigurationByEPAuthority(String authority);

    public ResponseEntity<String> setConfiguration(ConfigurationModification configurationModification);

}
