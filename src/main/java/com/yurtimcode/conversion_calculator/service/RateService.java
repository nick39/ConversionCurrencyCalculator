package com.yurtimcode.conversion_calculator.service;

import com.yurtimcode.conversion_calculator.entity.Rate;

public interface RateService {
    Rate getRate(String code);

    Rate getByInputOutputCurrency(String inputCode, String outputCode);
}
