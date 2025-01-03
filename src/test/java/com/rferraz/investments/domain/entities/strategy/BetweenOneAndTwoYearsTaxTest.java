package com.rferraz.investments.domain.entities.strategy;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

class BetweenOneAndTwoYearsTaxTest {

  @Test
  void shouldCalculateBetweenOneAndTwoYearsTaxCorrectly() {
    BigDecimal expectedTax = new BigDecimal("18.50");
    BigDecimal profit = BigDecimal.valueOf(100);
    BetweenOneAndTwoYearsTax tax = new BetweenOneAndTwoYearsTax();

    BigDecimal result = tax.calculateTax(profit);

    Assertions.assertEquals(expectedTax, result);
  }

}