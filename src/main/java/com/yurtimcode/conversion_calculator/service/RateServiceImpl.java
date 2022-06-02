package com.yurtimcode.conversion_calculator.service;

import com.yurtimcode.conversion_calculator.entity.Rate;
import com.yurtimcode.conversion_calculator.exception.ErrorCode;
import com.yurtimcode.conversion_calculator.repository.RateRepository;
import com.yurtimcode.conversion_calculator.exception.ConversionException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Currency;

@Service
public class RateServiceImpl implements RateService{

    private final RateRepository rateRepository;

    public RateServiceImpl(RateRepository rateRepository) {
        this.rateRepository = rateRepository;
    }

    @Override
    public Rate getRate(String code) {
        return rateRepository.findByCode(code);
    }

    @Override
    public Rate getByInputOutputCurrency(String inputCode, String outputCode) {
        if (!currencyByCodeIsAvailable(inputCode)) {
            throw new ConversionException(new ErrorCode(HttpStatus.BAD_REQUEST, "Currency with code " + inputCode + " doesn't exist"));
        }
        if (!currencyByCodeIsAvailable(outputCode)) {
            throw new ConversionException(new ErrorCode(HttpStatus.BAD_REQUEST, "Currency with code " + outputCode + " doesn't exist"));
        }

        Rate rate = getRate(inputCode + outputCode);
        if (rate == null) {
            throw new ConversionException(new ErrorCode(HttpStatus.BAD_REQUEST, "Rate for pair of currencies " + inputCode + outputCode + " doesn't exist"));
        }
        return rate;
    }

    private boolean currencyByCodeIsAvailable(String code) {
        for (Currency currency: Currency.getAvailableCurrencies()) {
            if (currency.getCurrencyCode().equals(code)) {
                return true;
            }
        }
        return false;
    }
}
