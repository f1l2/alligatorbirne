package common.exception;

import common.codes.ERROR_CODES;

public class ValidationException extends Exception {

    private static final long serialVersionUID = 1L;

    private ERROR_CODES errorCode;

    public ValidationException() {
        super();
    }

    public ValidationException(ERROR_CODES errorCode) {
        super(errorCode.getMessage());
        this.setErrorCode(errorCode);
    }

    public ERROR_CODES getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(ERROR_CODES errorCode) {
        this.errorCode = errorCode;
    }
}
