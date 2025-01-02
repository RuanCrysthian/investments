package com.rferraz.investments.domain.exceptions;

public class FieldIsRequiredException extends RuntimeException {
  public FieldIsRequiredException(String message) {
    super(message);
  }
}
