package com.rferraz.investments.domain.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record GetInvestmentDto(
    String id,
    String ownerId,
    BigDecimal amount,
    LocalDateTime creationDate) {

}
