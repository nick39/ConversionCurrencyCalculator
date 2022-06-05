package com.yurtimcode.conversion_calculator.controller;
import com.yurtimcode.conversion_calculator.exception.CurrencyNotFoundException;
import com.yurtimcode.conversion_calculator.exception.RateNotFoundException;
import com.yurtimcode.conversion_calculator.service.ConversionService;
import com.yurtimcode.conversion_calculator.api.ConvertFromApi;
import com.yurtimcode.conversion_calculator.api.ConvertToApi;
import com.yurtimcode.conversion_calculator.models.ConvertResult;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.NativeWebRequest;

import java.math.BigDecimal;
import java.util.Optional;

@RestController
public class ConversionController implements ConvertFromApi, ConvertToApi {

    private final ConversionService conversionService;

    public ConversionController(ConversionService conversionService) {
        this.conversionService = conversionService;
    }

    @Override
    public Optional<NativeWebRequest> getRequest() {
        return ConvertFromApi.super.getRequest();
    }

    @ExceptionHandler(value = CurrencyNotFoundException.class)
    public ResponseEntity handleBlogAlreadyExistsException(CurrencyNotFoundException exception) {
        return new ResponseEntity(exception.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = RateNotFoundException.class)
    public ResponseEntity handleBlogAlreadyExistsException(RateNotFoundException exception) {
        return new ResponseEntity(exception.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @Override
    public ResponseEntity<ConvertResult> convertFrom(String currencyFrom,String currencyTo, BigDecimal amountFrom) {
        return ResponseEntity.accepted().body(conversionService.convertFrom(currencyFrom, currencyTo, amountFrom));
    }

    @Override
    public ResponseEntity<ConvertResult> convertTo(String currencyFrom, String currencyTo, BigDecimal amountTo) {
        return ResponseEntity.accepted().body(conversionService.convertTo(currencyFrom, currencyTo, amountTo));
    }
}
