package com.pragma.foodcourt.core.domain.exception;

public class ErrorMessages {

    private ErrorMessages() {
    }

    public static final String USER_DATA_NULL = "User data cannot be null";
    public static final String INVALID_USER_DATA ="Invalid user data: ";
    public static final String INVALID_USER_ID_TYPE ="User identity type invalid";
    public static final String INVALID_USER_ID_NUMBER ="User identity number cannot be null";
    public static final String USER_NOT_ADULT = "User must be at least %d years old";
    public static final String INVALID_EMAIL_FORMAT = "Invalid email format";
    public static final String INVALID_PHONE_FORMAT = "Invalid phone number format or length (max %d digits with optional '+')";
    public static final String RESTAURANT_DATA_NULL = "Restaurant data cannot be null";
    public static final String INVALID_RESTAURANT_DATA ="Invalid restaurant data: ";
    public static final String INVALID_USER_ADMINISTRATOR = "User must be a Administrator";
    public static final String INVALID_RESTAURANT_IDENTITY = "Restaurant identity must be number";
    public static final String INVALID_RESTAURANT_NAME = "Restaurant name must have at least one letter";


}
