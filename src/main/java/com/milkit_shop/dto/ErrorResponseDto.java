package com.milkit_shop.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@AllArgsConstructor
public class ErrorResponseDto {
    private HttpStatus status;
    private String code;
    private String message;
}
