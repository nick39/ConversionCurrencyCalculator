package com.yurtimcode.conversion_calculator.controller;
import com.yurtimcode.conversion_calculator.exception.ConversionException;
import com.yurtimcode.conversion_calculator.exception.ErrorCode;
import com.yurtimcode.conversion_calculator.service.ConversionService;
import com.yurtimcode.conversion_calculator.api.ConvertFromApi;
import com.yurtimcode.conversion_calculator.api.ConvertToApi;
import com.yurtimcode.conversion_calculator.models.ConvertResult;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.NativeWebRequest;

import javax.validation.constraints.NotEmpty;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
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

    @ExceptionHandler(ConversionException.class)
    public ResponseEntity<ErrorCode> handleException(ConversionException e) {
        return new ResponseEntity<>(e.getCode(), HttpStatus.BAD_REQUEST);
    }

    @Override
    public ResponseEntity<ConvertResult> convertFrom(String currencyFrom,String currencyTo, BigDecimal amountFrom) {
        if(currencyFrom.isEmpty() || currencyTo.isEmpty()) {
            throw new ConversionException(new ErrorCode(HttpStatus.BAD_REQUEST, "{\"error\":\"At least one parameter is empty\"}"));
        }
        ConvertResult result = conversionService.convertFrom(currencyFrom, currencyTo, amountFrom);
        return ResponseEntity.accepted().body(result);
    }

    @Override
    public ResponseEntity<ConvertResult> convertTo(String currencyFrom, String currencyTo, BigDecimal amountTo) {
        if(currencyFrom.isEmpty() || currencyTo.isEmpty()) {
            throw new ConversionException(new ErrorCode(HttpStatus.BAD_REQUEST, "{\"error\":\"At least one parameter is empty\"}"));
        }
        ConvertResult result = conversionService.convertTo(currencyFrom, currencyTo, amountTo);
        return ResponseEntity.accepted().body(result);
    }
}
