package com.thoughtworks.exception;

public class InvalidEnumException extends BaseException {
    public static String message = "不合法的枚举类型";

    public InvalidEnumException() {
        super(message);
    }
}
