package com.rferraz.investments.domain.exceptions;

public class InvalidCreationDateException extends RuntimeException {
  public InvalidCreationDateException(String message) {
    super(message);
  }
}
