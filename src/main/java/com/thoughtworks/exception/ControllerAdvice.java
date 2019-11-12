package com.thoughtworks.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class ControllerAdvice {

    @ExceptionHandler(BaseException.class)
    public ResponseEntity handleBaseException(BaseException e) {
        log.error("throw BaseException, message is {}",e.getMessage());
        return new ResponseEntity(e.getErrorCode(), e.getHttpStatus());
    }
}
