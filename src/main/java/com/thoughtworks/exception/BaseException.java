package com.thoughtworks.exception;

import org.springframework.http.HttpStatus;

public class BaseException extends RuntimeException{

    private ErrorCode errorCode;

    private HttpStatus httpStatus;

    public BaseException(String message) {
        super(message);
    }

    public BaseException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
        this.httpStatus = errorCode.getHttpStatus();
    }

    public BaseException(ErrorCode errorCode, HttpStatus httpStatus) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
        this.httpStatus = httpStatus;
    }

    public BaseException(ErrorCode errorCode, HttpStatus httpStatus, String errorMsg) {
        super(errorMsg);
        this.errorCode = errorCode;
        this.httpStatus = httpStatus;
    }

    public ErrorCode getErrorCode() {
        return errorCode;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }
}
