package com.rferraz.investments.domain.dto;

import java.math.BigDecimal;

public record ViewInvestmentDto(
  String id,
  BigDecimal amount,
  BigDecimal expectedBalance
) {
}
