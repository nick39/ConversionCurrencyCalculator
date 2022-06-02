package com.yurtimcode.conversion_calculator.models;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.yurtimcode.conversion_calculator.models.ConvertResultConversionRate;
import com.yurtimcode.conversion_calculator.models.ConvertResultMargin;
import java.math.BigDecimal;
import org.openapitools.jackson.nullable.JsonNullable;
import java.time.OffsetDateTime;
import javax.validation.Valid;
import javax.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import javax.annotation.Generated;

/**
 * Conversion Result with rates
 */
@lombok.AllArgsConstructor

@Schema(name = "ConvertResult", description = "Conversion Result with rates")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2022-06-02T13:32:08.921+03:00[Europe/Moscow]")
public class ConvertResult {

  @JsonProperty("conversionRate")
  private ConvertResultConversionRate conversionRate;

  @JsonProperty("amount_from")
  private BigDecimal amountFrom;

  @JsonProperty("amount_to")
  private BigDecimal amountTo;

  @JsonProperty("margin")
  private ConvertResultMargin margin;

  public ConvertResult conversionRate(ConvertResultConversionRate conversionRate) {
    this.conversionRate = conversionRate;
    return this;
  }

  /**
   * Get conversionRate
   * @return conversionRate
  */
  @Valid 
  @Schema(name = "conversionRate", required = false)
  public ConvertResultConversionRate getConversionRate() {
    return conversionRate;
  }

  public void setConversionRate(ConvertResultConversionRate conversionRate) {
    this.conversionRate = conversionRate;
  }

  public ConvertResult amountFrom(BigDecimal amountFrom) {
    this.amountFrom = amountFrom;
    return this;
  }

  /**
   * Get amountFrom
   * @return amountFrom
  */
  @Valid 
  @Schema(name = "amount_from", required = false)
  public BigDecimal getAmountFrom() {
    return amountFrom;
  }

  public void setAmountFrom(BigDecimal amountFrom) {
    this.amountFrom = amountFrom;
  }

  public ConvertResult amountTo(BigDecimal amountTo) {
    this.amountTo = amountTo;
    return this;
  }

  /**
   * Get amountTo
   * @return amountTo
  */
  @Valid 
  @Schema(name = "amount_to", required = false)
  public BigDecimal getAmountTo() {
    return amountTo;
  }

  public void setAmountTo(BigDecimal amountTo) {
    this.amountTo = amountTo;
  }

  public ConvertResult margin(ConvertResultMargin margin) {
    this.margin = margin;
    return this;
  }

  /**
   * Get margin
   * @return margin
  */
  @Valid 
  @Schema(name = "margin", required = false)
  public ConvertResultMargin getMargin() {
    return margin;
  }

  public void setMargin(ConvertResultMargin margin) {
    this.margin = margin;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ConvertResult convertResult = (ConvertResult) o;
    return Objects.equals(this.conversionRate, convertResult.conversionRate) &&
        Objects.equals(this.amountFrom, convertResult.amountFrom) &&
        Objects.equals(this.amountTo, convertResult.amountTo) &&
        Objects.equals(this.margin, convertResult.margin);
  }

  @Override
  public int hashCode() {
    return Objects.hash(conversionRate, amountFrom, amountTo, margin);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ConvertResult {\n");
    sb.append("    conversionRate: ").append(toIndentedString(conversionRate)).append("\n");
    sb.append("    amountFrom: ").append(toIndentedString(amountFrom)).append("\n");
    sb.append("    amountTo: ").append(toIndentedString(amountTo)).append("\n");
    sb.append("    margin: ").append(toIndentedString(margin)).append("\n");
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

