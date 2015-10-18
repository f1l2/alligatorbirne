package iot.device.rest.activity;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import common.data.ConfigurationModification;
import common.data.type.COMPONENT_TYPE;

@Component
public class ValidateRequestBody extends Activity<String, ConfigurationModification> {

    final static Logger logger = LoggerFactory.getLogger(ValidateRequestBody.class);;

    private COMPONENT_TYPE ct;

    @Override
    public ResponseEntity<String> doStep(ConfigurationModification cm) {

        if ((null == cm) || (null == cm.getDataSink())) {
            String error = "Set configuration failed due missing data sink.";
            logger.error(error);
            return new ResponseEntity<String>(error, HttpStatus.BAD_REQUEST);
        }

        else if ((null == cm.getDataSink().getUrl()) || (StringUtils.isEmpty(cm.getDataSink().getUrl().getAuthority()))) {
            String error = "Set configuration failed due missing URL authority.";
            logger.error(error);
            return new ResponseEntity<String>(error, HttpStatus.BAD_REQUEST);
        }

        else if ((null == cm.getDataSink().getComponentType()) || (!cm.getDataSink().getComponentType().equals(ct))) {
            String error = "Set configuraiton failed due wrong component type";
            logger.error(error);
            return new ResponseEntity<String>(error, HttpStatus.BAD_REQUEST);
        }

        return next("OK", cm);
    }

    public void setCt(COMPONENT_TYPE ct) {
        this.ct = ct;
    }

}
