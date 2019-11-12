package com.thoughtworks.exception;

import static com.thoughtworks.exception.ErrorCode.USER_NOT_UNAUTHORIZED;

public class NotAuthorizedException extends BaseException {

    public NotAuthorizedException() {
        super(USER_NOT_UNAUTHORIZED);
    }

}
