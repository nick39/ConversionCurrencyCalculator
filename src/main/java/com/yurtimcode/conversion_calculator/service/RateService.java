package com.yurtimcode.conversion_calculator.service;

import com.yurtimcode.conversion_calculator.entity.Rate;
import com.yurtimcode.conversion_calculator.exception.RateNotFoundException;

import java.util.Optional;

public interface RateService {
    Rate getRate(String code);

    Rate getByCurrencyCodes(String inputCode, String outputCode) throws RateNotFoundException;
}
