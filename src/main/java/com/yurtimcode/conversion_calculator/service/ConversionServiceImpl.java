package com.yurtimcode.conversion_calculator.service;

import com.yurtimcode.conversion_calculator.entity.Rate;
import com.yurtimcode.conversion_calculator.exception.CurrencyNotFoundException;
import com.yurtimcode.conversion_calculator.exception.RateNotFoundException;
import com.yurtimcode.conversion_calculator.models.ConvertResult;
import com.yurtimcode.conversion_calculator.models.ConvertResultConversionRate;
import com.yurtimcode.conversion_calculator.models.ConvertResultMargin;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

@Service
public class ConversionServiceImpl implements ConversionService {

    @Value("${application.margin}")
    private String margin;

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

        BigDecimal marginBD = new BigDecimal(margin);
        BigDecimal convertedAmount = amountForSelling.multiply(rate.getRate()).setScale(2, BigDecimal.ROUND_HALF_EVEN);
        BigDecimal marginAmount = convertedAmount.multiply(marginBD.setScale(2, BigDecimal.ROUND_HALF_EVEN));
        BigDecimal outputAmount = convertedAmount.subtract(marginAmount);

        ConvertResultConversionRate conversionRate = new ConvertResultConversionRate(rate.getCode(), rate.getRate(), rate.getDate());
        ConvertResultMargin convertResultMargin = new ConvertResultMargin(marginAmount, currencyToCode, marginBD);

        return new ConvertResult(conversionRate, amountForSelling, outputAmount, convertResultMargin);
    }

    @Override
    public ConvertResult convertTo(String currencyFromCode, String currencyToCode, BigDecimal amountForBuying) {
        if(!currencyService.isAvailable(currencyFromCode))
            throw new CurrencyNotFoundException("Currency with code " + currencyFromCode + " doesn't exist");
        if(!currencyService.isAvailable(currencyToCode))
            throw new CurrencyNotFoundException("Currency with code " + currencyToCode + " doesn't exist");

        Rate rate = rateService.getByCurrencyCodes(currencyFromCode, currencyToCode);
        if (rate == null)
            throw new RateNotFoundException("Rate for pair of currencies " + currencyFromCode + currencyToCode + " doesn't exist");

        BigDecimal marginBD = new BigDecimal(margin);
        BigDecimal marginAmount = amountForBuying.multiply(marginBD.setScale(2, BigDecimal.ROUND_HALF_EVEN));
        BigDecimal inputAmount = (marginAmount.add(amountForBuying)).multiply(rate.getRate()).setScale(2, BigDecimal.ROUND_HALF_EVEN);

        ConvertResultConversionRate conversionRate = new ConvertResultConversionRate(currencyToCode, rate.getRate(), rate.getDate());
        ConvertResultMargin convertResultMargin = new ConvertResultMargin(marginAmount, currencyToCode, marginBD);

        return new ConvertResult(conversionRate, inputAmount, amountForBuying, convertResultMargin);
    }
}
