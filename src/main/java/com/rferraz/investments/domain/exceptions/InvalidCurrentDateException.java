package com.rferraz.investments.domain.exceptions;

public class InvalidCurrentDateException extends RuntimeException {
  public InvalidCurrentDateException(String message) {
    super(message);
  }
}
