package com.pragma.foodcourt.core.infrastructure.input.rest;

import feign.FeignException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleGlobalException(Exception e) {
        e.printStackTrace();
        return handleInternalError(e, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<?> handleIllegalArgumentExceptionn(IllegalArgumentException e, HttpStatus httpStatus) {
        e.printStackTrace();
        return handleInternalError(e, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(FeignException.class)
    public ResponseEntity<?> handleFeignException(FeignException e){
        e.printStackTrace();
        return handleInternalError(e,HttpStatus.CONFLICT);
    }

    @ExceptionHandler(FeignException.FeignClientException.class)
    public ResponseEntity<?> handleFeignClientException(FeignException.FeignClientException e){
        e.printStackTrace();
        return handleInternalError(e,HttpStatus.CONFLICT);
    }

    private ResponseEntity<WrapperResponse<?>> handleInternalError(Exception e, HttpStatus httpStatus) {
        WrapperResponse<?> response = new WrapperResponse<>(false, e.getLocalizedMessage(), null);
        return new ResponseEntity<>(response, httpStatus);
    }

}