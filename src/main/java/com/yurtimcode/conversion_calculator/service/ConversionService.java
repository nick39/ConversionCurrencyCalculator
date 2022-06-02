package com.yurtimcode.conversion_calculator.service;

import com.yurtimcode.conversion_calculator.models.ConvertResult;

import java.math.BigDecimal;

public interface ConversionService {

    ConvertResult convertFrom(String currencyFromCode, String currencyToCode, BigDecimal amountForSelling);

    ConvertResult convertTo(String currencyFromCode, String currencyToCode, BigDecimal amountForBuying);
}
