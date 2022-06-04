package com.yurtimcode.conversion_calculator;

import com.yurtimcode.conversion_calculator.controller.ConversionController;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ConversionCalculatorApplicationTests {

    @Autowired
    private ConversionController conversionController;

    @Test
    void contextLoads() {
        Assertions.assertThat(conversionController).isNotNull();
    }

}
