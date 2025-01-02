package com.rferraz.investments.domain.entities;

import com.rferraz.investments.domain.exceptions.FieldIsRequiredException;
import com.rferraz.investments.domain.exceptions.InvalidAmountException;
import com.rferraz.investments.domain.exceptions.InvalidCreationDateException;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public class Investment {

  private final String id;
  private final String ownerId;
  private final BigDecimal amount;
  private final LocalDateTime creationDate;

  private Investment(String ownerId, BigDecimal amount, LocalDateTime creationDate) {
    this.id = UUID.randomUUID().toString();
    this.ownerId = ownerId;
    this.amount = amount;
    this.creationDate = creationDate;
    this.validate();
  }

  public static Investment createInvestment(String ownerId, BigDecimal amount, LocalDateTime creation) {
    return new Investment(ownerId, amount, creation);
  }

  private void validate() {
    if (this.ownerId == null || this.ownerId.trim().isEmpty()) {
      throw new FieldIsRequiredException("ownerId is required");
    }
    if (this.amount == null) {
      throw new FieldIsRequiredException("amount is required");
    }
    if (this.amount.compareTo(BigDecimal.ZERO) <= 0) {
      throw new InvalidAmountException("amount should be greater than zero");
    }
    if (this.creationDate == null) {
      throw new FieldIsRequiredException("creationDate is required");
    }
    if (this.creationDate.isAfter(LocalDateTime.now())) {
      throw new InvalidCreationDateException("creationDate should be now or in the past");
    }
  }

  public String getId() {
    return id;
  }

  public String getOwnerId() {
    return ownerId;
  }

  public BigDecimal getAmount() {
    return amount;
  }

  public LocalDateTime getCreationDate() {
    return creationDate;
  }
}
