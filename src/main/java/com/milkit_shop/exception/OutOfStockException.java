package com.milkit_shop.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Map;

@Getter
public class OutOfStockException extends RuntimeException {

    private final HttpStatus status = HttpStatus.CONFLICT;
    private final String code = "OUT_OF_STOCK";

    public OutOfStockException(String message) {
        super(message);
    }
}
