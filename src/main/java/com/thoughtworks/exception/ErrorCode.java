package com.thoughtworks.exception;

import org.springframework.http.HttpStatus;

public enum ErrorCode {

    USER_NOT_FOUND("10000", "找不到用户", HttpStatus.BAD_REQUEST),
    USER_NOT_UNAUTHORIZED("10001", "用户未授权", HttpStatus.UNAUTHORIZED),
    USER_TOKEN_EXPIRED("10002", "用户token失效", HttpStatus.INTERNAL_SERVER_ERROR),
    SCHEDULE_NOT_FOUND("20000", "Schedule找不到", HttpStatus.BAD_REQUEST),
    LOGIN_FAIL("1001", "登录失败", HttpStatus.BAD_REQUEST);

    private String code;
    private String message;
    private HttpStatus httpStatus;

    ErrorCode(String code, String message, HttpStatus httpStatus) {
        this.code = code;
        this.message = message;
        this.httpStatus= httpStatus;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }
}
