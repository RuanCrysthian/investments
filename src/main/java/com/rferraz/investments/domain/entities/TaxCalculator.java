package com.rferraz.investments.domain.entities;

import com.rferraz.investments.domain.entities.strategy.BetweenOneAndTwoYearsTax;
import com.rferraz.investments.domain.entities.strategy.LessThanOneYearTax;
import com.rferraz.investments.domain.entities.strategy.OlderThanTwoYearsTax;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class TaxCalculator {

  public BigDecimal calculateTax(LocalDateTime creationDate, LocalDateTime withdrawalDate, BigDecimal profit) {
    int years = InvestmentAgeCalculator.calculateYearsBetween(
      creationDate.toLocalDate(),
      withdrawalDate.toLocalDate()
    );
    TaxStrategy strategy;
    if (years < 1) {
      strategy = new LessThanOneYearTax();
    } else if (years < 2) {
      strategy = new BetweenOneAndTwoYearsTax();
    } else {
      strategy = new OlderThanTwoYearsTax();
    }

    return strategy.calculateTax(profit);
  }
}
