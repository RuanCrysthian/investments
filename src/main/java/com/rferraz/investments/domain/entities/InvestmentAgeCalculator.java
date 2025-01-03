package com.rferraz.investments.domain.entities;

import com.rferraz.investments.domain.exceptions.InvalidCalculateYearsBetweenDatesException;

import java.time.LocalDate;
import java.time.Period;

public class InvestmentAgeCalculator {

  public static int calculateYearsBetween(LocalDate creationDate, LocalDate withdrawalDate) {
    if (creationDate.isAfter(withdrawalDate)) {
      throw new InvalidCalculateYearsBetweenDatesException("creationDate should be before withdrawalDate");
    }
    return Period.between(creationDate, withdrawalDate).getYears();
  }
}