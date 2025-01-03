package com.rferraz.investments.domain.entities.strategy;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

class OlderThanTwoYearsTaxTest {

  @Test
  void shouldCalculateOlderThanTwoYearsTaxCorrectly() {
    BigDecimal expectedTax = new BigDecimal("15.00");
    BigDecimal profit = BigDecimal.valueOf(100);
    OlderThanTwoYearsTax tax = new OlderThanTwoYearsTax();

    BigDecimal result = tax.calculateTax(profit);

    Assertions.assertEquals(expectedTax, result);
  }

}