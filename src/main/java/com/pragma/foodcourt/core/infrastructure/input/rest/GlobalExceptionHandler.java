package com.pragma.foodcourt.core.infrastructure.input.rest;

import feign.FeignException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<WrapperResponse<Object>> handleGlobalException(Exception e) {
        log.error("Unexpected error: "+e);
        return handleInternalError(e, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<WrapperResponse<Object>> handleIllegalArgumentException(IllegalArgumentException e) {
        log.error("IllegalArgumentException: "+e);
        return handleInternalError(e, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(FeignException.class)
    public ResponseEntity<WrapperResponse<Object>> handleFeignException(FeignException e) {
        log.error("FeignException: "+e);
        HttpStatus status;
        if (e.status() >= 500) {
            status = HttpStatus.BAD_GATEWAY;
        } else if (e.status() >= 400) {
            status = HttpStatus.BAD_REQUEST;
        } else {
            status = HttpStatus.CONFLICT;
        }
        return handleInternalError(e, status);
    }

    @ExceptionHandler(FeignException.FeignClientException.class)
    public ResponseEntity<WrapperResponse<Object>> handleFeignClientException(FeignException.FeignClientException e) {
        log.error("FeignClientException: "+e);
        return handleInternalError(e, HttpStatus.valueOf(e.status()));
    }

    private ResponseEntity<WrapperResponse<Object>> handleInternalError(Exception e, HttpStatus httpStatus) {
        WrapperResponse<Object> response = new WrapperResponse<>(false, e.getLocalizedMessage(), null);
        return new ResponseEntity<>(response, httpStatus);
    }
}