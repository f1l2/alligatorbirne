package configuration.management.rest.activity;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public abstract class Activity<T> {

    private ResponseEntity<T> errorResponse;

    private Activity<T> nextTask;

    public abstract ResponseEntity<T> doStep(T item);

    public ResponseEntity<T> next(T item) {

        if (null != errorResponse) {
            return errorResponse;
        } else if (null != nextTask) {
            return nextTask.doStep(item);
        } else {
            return new ResponseEntity<T>(item, HttpStatus.OK);
        }
    }

    public Activity<T> getNextTask() {
        return nextTask;
    }

    public void setNextTask(Activity<T> nextStep) {
        this.nextTask = nextStep;
    }

    public ResponseEntity<T> getErrorResponse() {
        return errorResponse;
    }

    public void setErrorResponse(ResponseEntity<T> errorResponse) {
        this.errorResponse = errorResponse;
    }
}
