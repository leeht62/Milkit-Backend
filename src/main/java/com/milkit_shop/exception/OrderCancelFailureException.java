package com.milkit_shop.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class OrderCancelFailureException extends RuntimeException {

    private final HttpStatus status = HttpStatus.BAD_REQUEST;
    private final String code = "ORDER_CANCEL_FAILURE";

    public OrderCancelFailureException(String message) {
        super(message);
    }
}
