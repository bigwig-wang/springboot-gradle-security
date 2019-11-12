package com.thoughtworks.exception;

import static com.thoughtworks.exception.ErrorCode.USER_TOKEN_EXPIRED;

public class TokenExpiredException extends BaseException {

    public TokenExpiredException() {
        super(USER_TOKEN_EXPIRED);
    }
}
