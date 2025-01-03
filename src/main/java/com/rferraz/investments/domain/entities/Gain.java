package com.rferraz.investments.domain.entities;

import com.rferraz.investments.domain.exceptions.FieldIsRequiredException;
import com.rferraz.investments.domain.exceptions.InvalidAmountException;
import com.rferraz.investments.domain.exceptions.InvalidCurrentDateException;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;

public class Gain {

  private static final BigDecimal MONTHLY_INTEREST_RATE = new BigDecimal("0.0052");

  public static BigDecimal calculate(BigDecimal amount, LocalDateTime creationDate, LocalDateTime currentDate) {
    validateFields(amount, creationDate, currentDate);
    int monthsDifference = 0;
    LocalDateTime tempDate = creationDate;
    while (!tempDate.isAfter(currentDate)) {
      tempDate = tempDate.plusMonths(1);
      if (!tempDate.isAfter(currentDate)) {
        monthsDifference++;
      }
    }
    BigDecimal multiplier = BigDecimal.ONE.add(MONTHLY_INTEREST_RATE).pow(monthsDifference);
    return amount.multiply(multiplier).setScale(2, RoundingMode.HALF_UP);
  }

  private static void validateFields(BigDecimal amount, LocalDateTime creationDate, LocalDateTime currentDate) {
    if (creationDate == null) {
      throw new FieldIsRequiredException("creationDate is required");
    }
    if (currentDate == null) {
      throw new FieldIsRequiredException("currentDate is required");
    }
    if (amount == null) {
      throw new FieldIsRequiredException("amount is required");
    }
    if (currentDate.isBefore(creationDate)) {
      throw new InvalidCurrentDateException("currentDate should be after creationDate");
    }
    if (amount.compareTo(BigDecimal.ZERO) < 0) {
      throw new InvalidAmountException("amount should be greater than zero");
    }
  }
}
