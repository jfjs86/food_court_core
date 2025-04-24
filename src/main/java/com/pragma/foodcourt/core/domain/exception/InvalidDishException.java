package com.pragma.foodcourt.core.domain.exception;

public class InvalidDishException extends RuntimeException{

    public InvalidDishException(String message) {
        super(message);
    }

    public InvalidDishException(String message, Throwable cause) {
        super(message, cause);
    }
}
