package com.yurtimcode.conversion_calculator.exception;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Data
@RequiredArgsConstructor
public class ErrorCode {
    private final HttpStatus httpStatus;
    private final String message;
}
