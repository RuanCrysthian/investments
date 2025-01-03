package com.rferraz.investments.domain.entities.strategy;

import com.rferraz.investments.domain.entities.TaxStrategy;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class BetweenOneAndTwoYearsTax implements TaxStrategy {

  private static final BigDecimal TAX_PERCENTAGE = BigDecimal.valueOf(18.5);

  @Override
  public BigDecimal calculateTax(BigDecimal profit) {
    return profit.multiply(TAX_PERCENTAGE).divide(BigDecimal.valueOf(100), 2, RoundingMode.HALF_UP);
  }
}
