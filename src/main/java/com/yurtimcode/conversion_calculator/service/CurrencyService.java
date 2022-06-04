package com.yurtimcode.conversion_calculator.service;

import com.yurtimcode.conversion_calculator.exception.CurrencyNotFoundException;

public interface CurrencyService {

    boolean isAvailable(String code) throws CurrencyNotFoundException;
}
