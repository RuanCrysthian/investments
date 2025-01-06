package com.rferraz.investments.domain.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record InsertInvestmentInputDto(
  String ownerId,
  BigDecimal amount,
  LocalDateTime creationDate
) {
}
