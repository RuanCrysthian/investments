package com.rferraz.investments.domain.entities.strategy;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

class LessThanOneYearTaxTest {

  @Test
  void shouldCalculateLessThanOneYearTaxCorrectly() {
    BigDecimal expectedTax = new BigDecimal("22.50");
    BigDecimal profit = BigDecimal.valueOf(100);
    LessThanOneYearTax tax = new LessThanOneYearTax();

    BigDecimal result = tax.calculateTax(profit);

    Assertions.assertEquals(expectedTax, result);
  }

}