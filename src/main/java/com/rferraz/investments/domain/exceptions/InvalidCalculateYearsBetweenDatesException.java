package com.rferraz.investments.domain.exceptions;

public class InvalidCalculateYearsBetweenDatesException extends RuntimeException {
  public InvalidCalculateYearsBetweenDatesException(String message) {
    super(message);
  }
}
