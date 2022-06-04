package com.yurtimcode.conversion_calculator.service.impl;

import com.yurtimcode.conversion_calculator.service.CurrencyService;
import org.springframework.stereotype.Service;

import java.util.Currency;

@Service
public class CurrencyServiceImpl implements CurrencyService {

    @Override
    public boolean isAvailable(String code) {
        for (Currency currency: Currency.getAvailableCurrencies()) {
            if (currency.getCurrencyCode().equals(code)) {
                return true;
            }
        }
        return false;
    }
}
