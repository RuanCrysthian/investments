package com.rferraz.investments.domain.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record WithdrawalInvestmentDto(
  String id,
  BigDecimal amount,
  BigDecimal gain,
  BigDecimal tax,
  BigDecimal withdrawalValue,
  LocalDateTime creationDate,
  LocalDateTime withdrawalDate
) {
}
