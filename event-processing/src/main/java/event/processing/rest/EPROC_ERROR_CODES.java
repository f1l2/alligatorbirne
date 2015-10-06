package event.processing.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public enum EPROC_ERROR_CODES {

    ERROR_MISSING_QUERY_NAME("Missing query name.", HttpStatus.INTERNAL_SERVER_ERROR),

    ERROR_EXISTING_QUERY("Query with the same name already exists.", HttpStatus.INTERNAL_SERVER_ERROR),

    ERROR_PARSING_QUERY("Couldn't parse query. Please check syntax.", HttpStatus.INTERNAL_SERVER_ERROR),

    ERROR_MISSING_RULE_NAME("Missing rule name.", HttpStatus.INTERNAL_SERVER_ERROR),

    ERROR_EXISTING_RULE("Rule with the same name already exists.", HttpStatus.INTERNAL_SERVER_ERROR),

    ERROR_PARSING_RULE("Couldn't parse rule. Please check syntax.", HttpStatus.INTERNAL_SERVER_ERROR),

    ERROR_NON_EXISTING_QUERY("Query with given name isn't registered.", HttpStatus.INTERNAL_SERVER_ERROR),

    ERROR_NON_EXISTING_RULE("Rule with given name isn't registered.", HttpStatus.INTERNAL_SERVER_ERROR),

    ERROR_ACTIVATE("Registration of statement at event engine failed.", HttpStatus.INTERNAL_SERVER_ERROR),

    ERROR_DEACTIVATE("Deregistration of statement at event engine failed.", HttpStatus.INTERNAL_SERVER_ERROR),

    ERROR_DEREGISTER_ACTIVE("Deregistration failed because rule is active.", HttpStatus.INTERNAL_SERVER_ERROR),

    ERROR_DEREGISTER_ASSIGNED("Deregistration failed because query is assigned to a rule.", HttpStatus.INTERNAL_SERVER_ERROR);

    private String message;

    private HttpStatus httpStatus;

    private static final Logger logger = LoggerFactory.getLogger(EPROC_ERROR_CODES.class);

    EPROC_ERROR_CODES(String message, HttpStatus httpStatus) {
        this.setMessage(message);
        this.setHttpStatus(httpStatus);
    }

    public ResponseEntity<String> getErrorResponse() {
        logger.error(message);
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
