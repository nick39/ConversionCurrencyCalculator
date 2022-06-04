package com.yurtimcode.conversion_calculator.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Getter
@Setter
public class Rate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * CurrencyInputCode + CurrencyOutputCode(e.g. EURGBP)
     */
    @Column(name = "code", length = 6)
    private String code;

    @Column(name = "rate", precision = 19, scale = 2, columnDefinition="DECIMAL(19,2)")
    private BigDecimal rate;
    @Column(name = "date")
    private LocalDate date;

}
