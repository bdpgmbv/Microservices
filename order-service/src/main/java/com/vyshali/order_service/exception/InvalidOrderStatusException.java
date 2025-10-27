package com.vyshali.order_service.exception;

public class InvalidOrderStatusException extends RuntimeException {
    
    public InvalidOrderStatusException(String message) {
        super(message);
    }
    
    public InvalidOrderStatusException(String message, Throwable cause) {
        super(message, cause);
    }
}
