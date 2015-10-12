package iot.device.rest.activity;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public abstract class Activity<T1, T2> {

    private ResponseEntity<T1> errorResponse;

    private Activity<T1, T2> nextTask;

    public abstract ResponseEntity<T1> doStep(T2 item);

    public ResponseEntity<T1> next(T1 response, T2 item) {

        if (null != errorResponse) {
            return errorResponse;
        } else if (null != nextTask) {
            return nextTask.doStep(item);
        } else {
            return new ResponseEntity<T1>(response, HttpStatus.OK);
        }
    }

    public Activity<T1, T2> getNextTask() {
        return nextTask;
    }

    public void setNextTask(Activity<T1, T2> nextStep) {
        this.nextTask = nextStep;
    }

    public ResponseEntity<T1> getErrorResponse() {
        return errorResponse;
    }

    public void setErrorResponse(ResponseEntity<T1> errorResponse) {
        this.errorResponse = errorResponse;
    }
}
