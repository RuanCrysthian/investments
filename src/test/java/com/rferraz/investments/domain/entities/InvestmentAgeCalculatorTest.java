package com.rferraz.investments.domain.entities;

import com.rferraz.investments.domain.exceptions.InvalidCalculateYearsBetweenDatesException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

class InvestmentAgeCalculatorTest {

  @Test
  void shouldCalculateTheYearsBetweenTwoDatesCorrectly() {
    LocalDate date1 = LocalDate.of(2025, 1, 1);
    LocalDate date2 = LocalDate.of(2000, 1, 1);
    int expectedYearsBetweenDates = 25;

    int result = InvestmentAgeCalculator.calculateYearsBetween(date2, date1);

    Assertions.assertEquals(expectedYearsBetweenDates, result);
  }

  @Test
  void shouldThrowInvalidCalculateYearsBetweenDatesExceptionWhenCreationDateIsAfterWithdrawalDate() {
    Assertions.assertThrows(InvalidCalculateYearsBetweenDatesException.class, () -> {
      LocalDate date1 = LocalDate.of(2025, 1, 1);
      LocalDate date2 = LocalDate.of(2000, 1, 1);

      InvestmentAgeCalculator.calculateYearsBetween(date1, date2);
    });
  }

}