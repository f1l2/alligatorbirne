package iot.device.rest;

import java.util.List;

import org.springframework.http.ResponseEntity;

import common.data.ConfigurationDelegation;
import iot.device.repo.DeliveryTaskRO;

/**
 * Interface definition
 * 
 * @author Manuel Filz
 * 
 */
public interface DevManageConfig {

    public ResponseEntity<List<DeliveryTaskRO>> getAllConfiguration();

    public ResponseEntity<DeliveryTaskRO> getConfigurationByEPAuthority(String authority);

    public ResponseEntity<String> setConfiguration(ConfigurationDelegation configurationDelegation);

    public ResponseEntity<String> startDelivery(ConfigurationDelegation configurationDelegation);

    public ResponseEntity<String> stopDelivery(ConfigurationDelegation configurationDelegation);

}
