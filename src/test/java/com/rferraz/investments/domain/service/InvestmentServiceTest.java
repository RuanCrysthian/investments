package com.rferraz.investments.domain.service;

import com.rferraz.investments.domain.dto.ViewInvestmentDto;
import com.rferraz.investments.domain.entities.Investment;
import com.rferraz.investments.domain.exceptions.EntityNotFoundException;
import com.rferraz.investments.infra.repository.InvestmentRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

class InvestmentServiceTest {

  @Mock
  private InvestmentRepository repository;

  @InjectMocks
  private InvestmentService service;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  void shouldThrowEntityNotFoundExceptionWhenInvestmentDoesNotExists() {
    String investmentId = "123";
    Mockito.when(repository.findById(investmentId)).thenReturn(Optional.empty());
    Assertions.assertThrows(EntityNotFoundException.class, () -> {
      service.view(investmentId);
    });
  }

  @Test
  void shouldViewAnInvestmentCorrectly() {
    Investment investment = Investment.createInvestment(
      "123",
      new BigDecimal("1000"),
      LocalDateTime.of(2024, 1, 1, 10, 0)
    );
    Mockito.when(repository.findById(investment.getId())).thenReturn(Optional.of(investment));

    ViewInvestmentDto result = service.view(investment.getId());

    Assertions.assertEquals(investment.getId(), result.id());
    Assertions.assertEquals(investment.getAmount(), result.amount());
    Assertions.assertNotNull(result.expectedBalance());
    Mockito.verify(repository, Mockito.times(1)).findById(investment.getId());
  }

  @Test
  void shouldInvestmentExpectedBalanceEqualZeroWhenInvestmentAlreadyWasWithdrawal() {
    Investment investment = Investment.createInvestment(
      "123",
      new BigDecimal("1000"),
      LocalDateTime.of(2024, 1, 1, 10, 0)
    );
    Mockito.when(repository.findById(investment.getId())).thenReturn(Optional.of(investment));
    investment.withdrawal();
    ViewInvestmentDto result = service.view(investment.getId());

    Assertions.assertEquals(Boolean.TRUE, investment.getWasWithdrawal());
    Assertions.assertEquals(BigDecimal.ZERO, investment.getAmount());
    Assertions.assertEquals(BigDecimal.ZERO, result.expectedBalance());
  }
}