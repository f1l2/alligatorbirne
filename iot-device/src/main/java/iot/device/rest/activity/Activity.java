package iot.device.rest.activity;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public abstract class Activity<T1, T2> {

    final static Logger logger = LoggerFactory.getLogger(Activity.class);

    private ResponseEntity<T1> errorResponse;

    private Activity<T1, T2> nextActivity;

    public abstract ResponseEntity<T1> doStep(T2 item);

    public ResponseEntity<T1> next(T1 response, T2 item) {

        if (null != errorResponse) {

            logger.error("{}", errorResponse);

            return errorResponse;
        } else if (null != nextActivity) {

            logger.info("Do next step in chain: {}", nextActivity.getClass().getSimpleName());

            return nextActivity.doStep(item);
        } else {
            return new ResponseEntity<T1>(response, HttpStatus.OK);
        }
    }

    public Activity<T1, T2> getNextActivity() {
        return nextActivity;
    }

    public void setNextActivity(Activity<T1, T2> nextStep) {
        this.nextActivity = nextStep;
    }

    public ResponseEntity<T1> getErrorResponse() {
        return errorResponse;
    }

    public void setErrorResponse(ResponseEntity<T1> errorResponse) {
        this.errorResponse = errorResponse;
    }
}
