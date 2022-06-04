package com.yurtimcode.conversion_calculator.repository;

import com.yurtimcode.conversion_calculator.entity.Rate;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RateRepository extends JpaRepository<Rate, Long> {
    Rate findByCode(String code);
}
