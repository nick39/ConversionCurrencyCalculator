package com.yurtimcode.conversion_calculator.service.impl;

import com.yurtimcode.conversion_calculator.entity.Rate;
import com.yurtimcode.conversion_calculator.exception.CurrencyNotFoundException;
import com.yurtimcode.conversion_calculator.exception.RateNotFoundException;
import com.yurtimcode.conversion_calculator.models.ConvertResult;
import com.yurtimcode.conversion_calculator.models.ConvertResultConversionRate;
import com.yurtimcode.conversion_calculator.models.ConvertResultMargin;
import com.yurtimcode.conversion_calculator.service.ConversionService;
import com.yurtimcode.conversion_calculator.service.CurrencyService;
import com.yurtimcode.conversion_calculator.service.RateService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.OffsetDateTime;

@Service
public class ConversionServiceImpl implements ConversionService {

    @Value("${application.margin}")
    private BigDecimal margin;

    private final RateService rateService;
    private final CurrencyService currencyService;

    public ConversionServiceImpl(RateService rateService, CurrencyService currencyService) {
        this.rateService = rateService;
        this.currencyService = currencyService;
    }

    @Override
    public ConvertResult convertFrom(String currencyFromCode, String currencyToCode, BigDecimal amountForSelling) {

        if(!currencyService.isAvailable(currencyFromCode))
            throw new CurrencyNotFoundException("Currency with code " + currencyFromCode + " doesn't exist");
        if(!currencyService.isAvailable(currencyToCode))
            throw new CurrencyNotFoundException("Currency with code " + currencyToCode + " doesn't exist");

        Rate rate = rateService.getByCurrencyCodes(currencyFromCode, currencyToCode);
        if (rate == null)
            throw new RateNotFoundException("Rate for pair of currencies " + currencyFromCode + currencyToCode + " doesn't exist");

        BigDecimal marginAmount = amountForSelling.multiply(margin).setScale(2, RoundingMode.HALF_EVEN);
        ConvertResultMargin convertResultMargin = new ConvertResultMargin(marginAmount, currencyFromCode, margin);

        BigDecimal outputAmount = ((amountForSelling.subtract(marginAmount)).multiply(rate.getRate())).setScale(2, RoundingMode.HALF_EVEN);
        ConvertResultConversionRate conversionRate = new ConvertResultConversionRate(rate.getCode(), rate.getRate(), rate.getDate());

        return new ConvertResult(conversionRate, convertResultMargin, amountForSelling, currencyFromCode, outputAmount, currencyToCode, OffsetDateTime.now());
    }

    @Override
    public ConvertResult convertTo(String currencyFromCode, String currencyToCode, BigDecimal amountForBuying) {
        if(!currencyService.isAvailable(currencyFromCode))
            throw new CurrencyNotFoundException("Currency with code " + currencyFromCode + " doesn't exist");
        if(!currencyService.isAvailable(currencyToCode))
            throw new CurrencyNotFoundException("Currency with code " + currencyToCode + " doesn't exist");

        Rate rate = rateService.getByCurrencyCodes(currencyToCode, currencyFromCode);
        if (rate == null)
            throw new RateNotFoundException("Rate for pair of currencies " + currencyFromCode + currencyToCode + " doesn't exist");

        BigDecimal marginAmount = amountForBuying.multiply(margin).setScale(2, RoundingMode.HALF_EVEN);
        ConvertResultMargin convertResultMargin = new ConvertResultMargin(marginAmount, currencyToCode, margin);

        BigDecimal inputAmount = ((marginAmount.add(amountForBuying)).multiply(rate.getRate())).setScale(2, RoundingMode.HALF_EVEN);
        ConvertResultConversionRate conversionRate = new ConvertResultConversionRate(rate.getCode(), rate.getRate(), rate.getDate());

        return new ConvertResult(conversionRate, convertResultMargin, inputAmount, currencyFromCode, amountForBuying, currencyToCode, OffsetDateTime.now());
    }
}
