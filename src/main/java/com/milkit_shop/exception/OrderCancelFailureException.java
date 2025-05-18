package com.milkit_shop.exception;

public class OrderCancelFailureException extends RuntimeException {
    public OrderCancelFailureException(String message) {
        super(message);
    }
}
