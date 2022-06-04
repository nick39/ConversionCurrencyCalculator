package com.yurtimcode.conversion_calculator.service;

import com.yurtimcode.conversion_calculator.entity.Rate;
import com.yurtimcode.conversion_calculator.exception.RateNotFoundException;
import com.yurtimcode.conversion_calculator.repository.RateRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.Optional;

@Service
public class RateServiceImpl implements RateService{

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
