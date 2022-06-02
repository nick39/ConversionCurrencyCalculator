package com.yurtimcode.conversion_calculator.models;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonTypeName;
import java.math.BigDecimal;
import java.time.LocalDate;
import org.springframework.format.annotation.DateTimeFormat;
import org.openapitools.jackson.nullable.JsonNullable;
import java.time.OffsetDateTime;
import javax.validation.Valid;
import javax.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import javax.annotation.Generated;

/**
 * ConvertResultConversionRate
 */
@lombok.AllArgsConstructor

@JsonTypeName("ConvertResult_conversionRate")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2022-06-02T13:32:08.921+03:00[Europe/Moscow]")
public class ConvertResultConversionRate {

  @JsonProperty("code")
  private String code;

  @JsonProperty("rate")
  private BigDecimal rate;

  @JsonProperty("date")
  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
  private LocalDate date;

  public ConvertResultConversionRate code(String code) {
    this.code = code;
    return this;
  }

  /**
   * Get code
   * @return code
  */
  @Size(max = 6) 
  @Schema(name = "code", required = false)
  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public ConvertResultConversionRate rate(BigDecimal rate) {
    this.rate = rate;
    return this;
  }

  /**
   * Get rate
   * @return rate
  */
  @Valid 
  @Schema(name = "rate", required = false)
  public BigDecimal getRate() {
    return rate;
  }

  public void setRate(BigDecimal rate) {
    this.rate = rate;
  }

  public ConvertResultConversionRate date(LocalDate date) {
    this.date = date;
    return this;
  }

  /**
   * Get date
   * @return date
  */
  @Valid 
  @Schema(name = "date", required = false)
  public LocalDate getDate() {
    return date;
  }

  public void setDate(LocalDate date) {
    this.date = date;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ConvertResultConversionRate convertResultConversionRate = (ConvertResultConversionRate) o;
    return Objects.equals(this.code, convertResultConversionRate.code) &&
        Objects.equals(this.rate, convertResultConversionRate.rate) &&
        Objects.equals(this.date, convertResultConversionRate.date);
  }

  @Override
  public int hashCode() {
    return Objects.hash(code, rate, date);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ConvertResultConversionRate {\n");
    sb.append("    code: ").append(toIndentedString(code)).append("\n");
    sb.append("    rate: ").append(toIndentedString(rate)).append("\n");
    sb.append("    date: ").append(toIndentedString(date)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}

