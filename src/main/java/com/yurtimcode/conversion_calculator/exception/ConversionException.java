package com.yurtimcode.conversion_calculator.exception;

import lombok.Getter;

@Getter
public class ConversionException extends RuntimeException{

    private final ErrorCode code;
    private String description;

    public ConversionException(ErrorCode code, String details) {
        this.code = code;
        this.description = details;
    }

    public ConversionException(ErrorCode code) {
        this.code = code;
        this.description = code.getMessage();
    }
}
