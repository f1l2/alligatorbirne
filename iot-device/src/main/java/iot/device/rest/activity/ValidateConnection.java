package iot.device.rest.activity;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import common.data.ConfigurationDelegation;
import common.data.type.COMPONENT_TYPE;

@Component
public class ValidateConnection extends Activity<String, ConfigurationDelegation> {

    final static Logger logger = LoggerFactory.getLogger(ValidateConnection.class);

    private COMPONENT_TYPE ct = COMPONENT_TYPE.EVENT_PROCESSING;

    @Override
    public ResponseEntity<String> doStep(ConfigurationDelegation cd) {

        if ((null == cd) || (null == cd.getDataSink())) {
            String error = "Set configuration failed due missing data sink.";
            logger.error(error);
            return new ResponseEntity<String>(error, HttpStatus.BAD_REQUEST);
        }

        else if ((null == cd.getDataSink().getUrl()) || (StringUtils.isEmpty(cd.getDataSink().getUrl().getAuthority()))) {
            String error = "Set configuration failed due missing URL authority.";
            logger.error(error);
            return new ResponseEntity<String>(error, HttpStatus.BAD_REQUEST);
        }

        else if ((null == cd.getDataSink().getComponentType()) || (!cd.getDataSink().getComponentType().equals(ct))) {
            String error = "Set configuraiton failed due wrong component type";
            logger.error(error);
            return new ResponseEntity<String>(error, HttpStatus.BAD_REQUEST);
        }

        return next("OK", cd);
    }

    public void setCt(COMPONENT_TYPE ct) {
        this.ct = ct;
    }

}
