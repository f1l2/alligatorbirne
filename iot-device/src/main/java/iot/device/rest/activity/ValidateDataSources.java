package iot.device.rest.activity;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import common.data.ConfigurationDelegation;

@Component
public class ValidateDataSources extends Activity<String, ConfigurationDelegation> {

    final static Logger logger = LoggerFactory.getLogger(ValidateDataSources.class);;

    @Override
    public ResponseEntity<String> doStep(ConfigurationDelegation item) {

        if (null == item) {
            this.setErrorResponse(new ResponseEntity<String>("Data sources is null.", HttpStatus.BAD_REQUEST));
        } else if (CollectionUtils.isEmpty(item.getDataSources())) {
            this.setErrorResponse(new ResponseEntity<String>("Data sources are empty.", HttpStatus.BAD_REQUEST));
        }

        return next("OK", item);
    }

}
