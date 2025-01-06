package com.rferraz.investments.domain.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record InsertInvestmentOutputDto(
  String id,
  String ownerId,
  BigDecimal amount,
  LocalDateTime creationDate,
  Boolean wasWithdrawal,
  LocalDateTime withdrawalDate
) {
}
