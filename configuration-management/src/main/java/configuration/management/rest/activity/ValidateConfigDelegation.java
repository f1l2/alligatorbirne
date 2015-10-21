package configuration.management.rest.activity;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import common.data.ConfigurationDelegation;

@Component
public class ValidateConfigDelegation extends Activity<ConfigurationDelegation, ConfigurationDelegation> {

    final static Logger logger = LoggerFactory.getLogger(ValidateConfigDelegation.class);

    @Override
    public ResponseEntity<ConfigurationDelegation> doStep(ConfigurationDelegation item) {

        if (null == item.getConfigurationModification()) {
            logger.error("Delegation failed due missing ConfigurationModification authority");
            this.setErrorResponse(new ResponseEntity<ConfigurationDelegation>(item, HttpStatus.BAD_REQUEST));
        } else if ((null == item.getDeviceInformation()) || (null == item.getDeviceInformation().getName())) {
            logger.error("Delegation failed due missing DeviceInformation");
            this.setErrorResponse(new ResponseEntity<ConfigurationDelegation>(item, HttpStatus.BAD_REQUEST));
        } else if ((null == item.getDomainInformation()) || (null == item.getDomainInformation().getName())) {
            logger.error("Delegation failed due missing DomainInformation");
            this.setErrorResponse(new ResponseEntity<ConfigurationDelegation>(item, HttpStatus.BAD_REQUEST));
        }
        return next(item, item);
    }

}
