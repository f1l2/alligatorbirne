package common.codes;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public enum SUCCESS_CODES {

    OK("OK", HttpStatus.OK);

    private String message;

    private HttpStatus httpStatus;

    SUCCESS_CODES(String message, HttpStatus httpStatus) {
        this.setMessage(message);
        this.setHttpStatus(httpStatus);
    }

    public ResponseEntity<String> getResponse() {
        return new ResponseEntity<String>(message, httpStatus);
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public void setHttpStatus(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
    }

}
