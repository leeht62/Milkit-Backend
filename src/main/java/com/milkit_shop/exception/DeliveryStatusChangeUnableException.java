package com.milkit_shop.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class DeliveryStatusChangeUnableException extends RuntimeException {

    private final HttpStatus status = HttpStatus.BAD_REQUEST;
    private final String code = "DELIVERY_STATUS_CHANGE_UNABLE";

    public DeliveryStatusChangeUnableException(String message) {
        super(message);
    }
}
