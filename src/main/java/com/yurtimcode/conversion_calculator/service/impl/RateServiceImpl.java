package com.yurtimcode.conversion_calculator.service.impl;

import com.yurtimcode.conversion_calculator.entity.Rate;
import com.yurtimcode.conversion_calculator.repository.RateRepository;
import com.yurtimcode.conversion_calculator.service.CurrencyService;
import com.yurtimcode.conversion_calculator.service.RateService;
import org.springframework.stereotype.Service;

@Service
public class RateServiceImpl implements RateService {

    private final RateRepository rateRepository;
    private final CurrencyService currencyService;

    public RateServiceImpl(RateRepository rateRepository, CurrencyService currencyService) {
        this.rateRepository = rateRepository;
        this.currencyService = currencyService;
    }

    @Override
    public Rate getRate(String code) {
        return rateRepository.findByCode(code);
    }

    @Override
    public Rate getByCurrencyCodes(String inputCode, String outputCode){
        return getRate(inputCode + outputCode);
    }
}
