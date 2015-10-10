package configuration.management.rest.task;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import common.data.ConfigurationDelegation;

@Component
public class ValidateConfigDelegation extends Task<ConfigurationDelegation> {

    final static Logger logger = LoggerFactory.getLogger(ValidateConfigDelegation.class);

    @Override
    public ResponseEntity<ConfigurationDelegation> doStep(ConfigurationDelegation item) {

        if (null == item.getConfigurationModification()) {
            logger.error("Delegation failed due missing ConfigurationModification authority");
            return new ResponseEntity<ConfigurationDelegation>(item, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return next(item);
    }

}