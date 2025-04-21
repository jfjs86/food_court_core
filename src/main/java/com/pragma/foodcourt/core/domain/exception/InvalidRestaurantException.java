package com.pragma.foodcourt.core.domain.exception;

public class InvalidRestaurantException extends RuntimeException{

    public InvalidRestaurantException(String message) {
        super(message);
    }

    public InvalidRestaurantException(String message, Throwable cause) {
        super(message, cause);
    }
}
