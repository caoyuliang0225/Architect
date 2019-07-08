package com.cloud.architect.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by Cao Yuliang on 2019-07-04.
 */
@ControllerAdvice
public class CustomExceptionHandler {

    @ResponseBody
    @ExceptionHandler(CustomException.class)
    public ResponseEntity<Error> customException(CustomException customException) {
        HttpStatus status = HttpStatus.OK;
        Error error = new Error(customException.getCode(), customException.getMessage());

        return new ResponseEntity<>(error, status);
    }
}
