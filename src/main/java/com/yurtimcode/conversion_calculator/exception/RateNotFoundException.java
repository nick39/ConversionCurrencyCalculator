package com.yurtimcode.conversion_calculator.exception;

public class RateNotFoundException extends RuntimeException{
    private String message;
    public RateNotFoundException(String message) {
        super(message);
        this.message = message;
    }
    public RateNotFoundException() {
    }
}
