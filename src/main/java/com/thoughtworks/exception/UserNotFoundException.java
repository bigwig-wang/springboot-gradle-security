package com.thoughtworks.exception;

public class UserNotFoundException extends BaseException{

    public UserNotFoundException() {
        super(ErrorCode.USER_NOT_FOUND);
    }
}
