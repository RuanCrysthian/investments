package com.rferraz.investments.domain.entities;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;

class TaxCalculatorTest {

  @Test
  void shouldApplyLessThanOneYearTax() {
    LocalDateTime creationDate = LocalDateTime.of(2025, 1, 1, 0, 0);
    LocalDateTime withdrawalDate = LocalDateTime.of(2025, 6, 1, 0, 0);
    BigDecimal profit = new BigDecimal("200.00");

    TaxCalculator taxCalculator = new TaxCalculator();

    BigDecimal tax = taxCalculator.calculateTax(creationDate, withdrawalDate, profit);

    Assertions.assertEquals(new BigDecimal("45.00"), tax);
  }

  @Test
  void shouldApplyBetweenOneAndTwoYearsTax() {
    LocalDateTime creationDate = LocalDateTime.of(2024, 1, 1, 0, 0);
    LocalDateTime withdrawalDate = LocalDateTime.of(2025, 6, 1, 0, 0);
    BigDecimal profit = new BigDecimal("200.00");

    TaxCalculator taxCalculator = new TaxCalculator();

    BigDecimal tax = taxCalculator.calculateTax(creationDate, withdrawalDate, profit);

    Assertions.assertEquals(new BigDecimal("37.00"), tax);
  }

  @Test
  void shouldApplyOlderThanTwoYearsTax() {
    LocalDateTime creationDate = LocalDateTime.of(2022, 1, 1, 0, 0);
    LocalDateTime withdrawalDate = LocalDateTime.of(2025, 6, 1, 0, 0);
    BigDecimal profit = new BigDecimal("200.00");

    TaxCalculator taxCalculator = new TaxCalculator();

    BigDecimal tax = taxCalculator.calculateTax(creationDate, withdrawalDate, profit);

    Assertions.assertEquals(new BigDecimal("30.00"), tax);
  }
}