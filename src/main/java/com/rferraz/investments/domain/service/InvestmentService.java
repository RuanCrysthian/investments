package com.rferraz.investments.domain.service;

import com.rferraz.investments.domain.dto.ViewInvestmentDto;
import com.rferraz.investments.domain.entities.Gain;
import com.rferraz.investments.domain.entities.Investment;
import com.rferraz.investments.domain.exceptions.EntityNotFoundException;
import com.rferraz.investments.infra.repository.InvestmentRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class InvestmentService {

  private final InvestmentRepository repository;

  public InvestmentService(InvestmentRepository repository) {
    this.repository = repository;
  }

  public ViewInvestmentDto view(String investmentId) {
    Optional<Investment> investment = repository.findById(investmentId);
    if (investment.isEmpty()) {
      throw new EntityNotFoundException("investment not found");
    }
    String id = investment.get().getId();
    BigDecimal amount = investment.get().getAmount();
    BigDecimal expectedBalance = Gain.calculate(amount, investment.get().getCreationDate(), LocalDateTime.now());
    return new ViewInvestmentDto(id, amount, expectedBalance);
  }
}
