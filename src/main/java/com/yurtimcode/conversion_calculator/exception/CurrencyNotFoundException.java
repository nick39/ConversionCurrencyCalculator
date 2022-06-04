package com.yurtimcode.conversion_calculator.exception;

public class CurrencyNotFoundException extends RuntimeException{
    private String message;
    public CurrencyNotFoundException(String message) {
        super(message);
        this.message = message;
    }
    public CurrencyNotFoundException() {
    }
}
