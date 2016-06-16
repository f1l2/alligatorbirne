package event.processing.rest.activity;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public abstract class Activity<T1, T2> {

    private ResponseEntity<T1> errorResponse;

    private Activity<T1, T2> nextActivity;

    public abstract ResponseEntity<T1> doStep(T2 item);

    public ResponseEntity<T1> next(T1 content, T2 item) {
        if (null != errorResponse) {
            return errorResponse;
        } else if (null != nextActivity) {
            return nextActivity.doStep(item);
        } else {
            return new ResponseEntity<T1>(content, HttpStatus.OK);
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
