package configuration.management.rest.activity.validate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import configuration.management.rest.activity.Activity;
import configuration.management.rest.activity.model.AssignRuleItem;

@Component
public class ValidateAssignRuleItem extends Activity<String, AssignRuleItem> {

    final static Logger logger = LoggerFactory.getLogger(ValidateAssignRuleItem.class);

    @Override
    public ResponseEntity<String> doStep(AssignRuleItem item) {

        if (null == item.getRule()) {
            logger.error("Assigning task failed due missing rule.");
            this.setErrorResponse(new ResponseEntity<String>("", HttpStatus.BAD_REQUEST));
        } else if ((null == item.getEp()) || (null == item.getEp().getUrl())) {
            logger.error("Assigning task failed due missing ep.");
            this.setErrorResponse(new ResponseEntity<String>("", HttpStatus.BAD_REQUEST));
        }

        return next("OK", item);
    }

}
