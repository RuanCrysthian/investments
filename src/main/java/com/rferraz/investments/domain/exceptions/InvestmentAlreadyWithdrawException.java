package com.rferraz.investments.domain.exceptions;

public class InvestmentAlreadyWithdrawException extends RuntimeException {
  public InvestmentAlreadyWithdrawException(String message) {
    super(message);
  }
}
