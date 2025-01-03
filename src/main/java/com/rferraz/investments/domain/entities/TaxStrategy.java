package com.rferraz.investments.domain.entities;

import java.math.BigDecimal;

public interface TaxStrategy {
  BigDecimal calculateTax(BigDecimal profit);
}
