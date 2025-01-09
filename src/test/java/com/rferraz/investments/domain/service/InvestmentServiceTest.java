package com.rferraz.investments.domain.service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.rferraz.investments.domain.dto.GetInvestmentDto;
import com.rferraz.investments.domain.dto.InsertInvestmentInputDto;
import com.rferraz.investments.domain.dto.InsertInvestmentOutputDto;
import com.rferraz.investments.domain.dto.ViewInvestmentDto;
import com.rferraz.investments.domain.dto.WithdrawalInvestmentDto;
import com.rferraz.investments.domain.entities.Investment;
import com.rferraz.investments.domain.exceptions.EntityNotFoundException;
import com.rferraz.investments.domain.exceptions.InvestmentAlreadyWithdrawException;
import com.rferraz.investments.domain.repository.InvestmentRepository;

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
        LocalDateTime.of(2024, 1, 1, 10, 0));
    Mockito.when(repository.findById(investment.getId())).thenReturn(Optional.of(investment));

    ViewInvestmentDto result = service.view(investment.getId());

    Assertions.assertEquals(investment.getId(), result.id());
    Assertions.assertEquals(investment.getAmount(), result.amount());
    Assertions.assertNotNull(result.expectedBalance());
    Mockito.verify(repository, Mockito.times(1)).findById(investment.getId());
  }

  @Test
  void shouldInvestmentExpectedBalanceEqualZeroWhenInvestmentAlreadyWasWithdrawal() {
    Assertions.assertThrows(InvestmentAlreadyWithdrawException.class, () -> {
      Investment investment = Investment.createInvestment(
          "123",
          new BigDecimal("1000"),
          LocalDateTime.of(2024, 1, 1, 10, 0));
      Mockito.when(repository.findById(investment.getId())).thenReturn(Optional.of(investment));
      investment.withdrawal();
      service.view(investment.getId());
    });
  }

  @Test
  void shouldWithdrawInvestmentWithAgeBetweenOneAndTwoYearsSuccessfully() {
    BigDecimal expectedAmount = BigDecimal.ZERO;
    BigDecimal expectedGain = new BigDecimal("1064.22");
    BigDecimal expectedTax = new BigDecimal("11.88");
    BigDecimal expectedWithdrawalValue = new BigDecimal("1052.34");
    Investment investment = Investment.createInvestment(
        "123",
        new BigDecimal("1000"),
        LocalDateTime.now().minusYears(1));
    InvestmentRepository repository = Mockito.mock(InvestmentRepository.class);
    Mockito.when(repository.findById(investment.getId())).thenReturn(Optional.of(investment));

    InvestmentService service = new InvestmentService(repository);

    WithdrawalInvestmentDto result = service.withdrawalInvestment(investment.getId());

    Assertions.assertEquals(expectedAmount, result.amount());
    Assertions.assertEquals(expectedGain, result.gain());
    Assertions.assertEquals(expectedTax, result.tax());
    Assertions.assertEquals(expectedWithdrawalValue, result.withdrawalValue());
    Assertions.assertNotNull(result.creationDate());
    Assertions.assertNotNull(result.withdrawalDate());
    Mockito.verify(repository).save(investment);
  }

  @Test
  void shouldWithdrawInvestmentWithAgeOlderThanTwoYearsSuccessfully() {
    BigDecimal expectedAmount = BigDecimal.ZERO;
    BigDecimal expectedGain = new BigDecimal("1282.68");
    BigDecimal expectedTax = new BigDecimal("42.40");
    BigDecimal expectedWithdrawalValue = new BigDecimal("1240.28");
    Investment investment = Investment.createInvestment(
        "123",
        new BigDecimal("1000"),
        LocalDateTime.now().minusYears(4));
    InvestmentRepository repository = Mockito.mock(InvestmentRepository.class);
    Mockito.when(repository.findById(investment.getId())).thenReturn(Optional.of(investment));

    InvestmentService service = new InvestmentService(repository);

    WithdrawalInvestmentDto result = service.withdrawalInvestment(investment.getId());

    Assertions.assertEquals(expectedAmount, result.amount());
    Assertions.assertEquals(expectedGain, result.gain());
    Assertions.assertEquals(expectedTax, result.tax());
    Assertions.assertEquals(expectedWithdrawalValue, result.withdrawalValue());
    Assertions.assertNotNull(result.creationDate());
    Assertions.assertNotNull(result.withdrawalDate());
    Mockito.verify(repository).save(investment);
  }

  @Test
  void shouldWithdrawInvestmentWithAgeLessThanOneYearSuccessfully() {
    BigDecimal expectedAmount = BigDecimal.ZERO;
    BigDecimal expectedGain = new BigDecimal("1010.43");
    BigDecimal expectedTax = new BigDecimal("2.35");
    BigDecimal expectedWithdrawalValue = new BigDecimal("1008.08");
    Investment investment = Investment.createInvestment(
        "123",
        new BigDecimal("1000"),
        LocalDateTime.now().minusMonths(2));
    InvestmentRepository repository = Mockito.mock(InvestmentRepository.class);
    Mockito.when(repository.findById(investment.getId())).thenReturn(Optional.of(investment));

    InvestmentService service = new InvestmentService(repository);

    WithdrawalInvestmentDto result = service.withdrawalInvestment(investment.getId());

    Assertions.assertEquals(expectedAmount, result.amount());
    Assertions.assertEquals(expectedGain, result.gain());
    Assertions.assertEquals(expectedTax, result.tax());
    Assertions.assertEquals(expectedWithdrawalValue, result.withdrawalValue());
    Assertions.assertNotNull(result.creationDate());
    Assertions.assertNotNull(result.withdrawalDate());
    Mockito.verify(repository).save(investment);
  }

  @Test
  void shouldThrowEntityNotFoundExceptionWhenInvestmentDoesNotExist() {
    String investmentId = "123";
    InvestmentRepository repository = Mockito.mock(InvestmentRepository.class);
    Mockito.when(repository.findById(investmentId)).thenReturn(Optional.empty());

    InvestmentService service = new InvestmentService(repository);

    Assertions.assertThrows(EntityNotFoundException.class, () -> service.withdrawalInvestment(investmentId));
  }

  @Test
  void shouldInsertCorrectlyAnInvestment() {
    String ownerId = "123";
    BigDecimal amount = new BigDecimal("1000");
    LocalDateTime creationDate = LocalDateTime.now().minusMonths(2);
    InsertInvestmentInputDto input = new InsertInvestmentInputDto(ownerId, amount, creationDate);

    InsertInvestmentOutputDto result = service.insertInvestment(input);

    Assertions.assertNotNull(result.id());
    Assertions.assertEquals(input.ownerId(), result.ownerId());
    Assertions.assertEquals(input.amount(), result.amount());
    Assertions.assertEquals(input.creationDate(), result.creationDate());
    Assertions.assertFalse(result.wasWithdrawal());
    Assertions.assertNull(result.withdrawalDate());
  }

  @Test
  void shouldThrowInvestmentAlreadyWithdrawExceptionWhenWasWithdrawIsTrue() {
    Assertions.assertThrows(InvestmentAlreadyWithdrawException.class, () -> {
      Investment investment = Investment.createInvestment(
          "123",
          new BigDecimal("1000"),
          LocalDateTime.of(2024, 1, 1, 10, 0));
      Mockito.when(repository.findById(investment.getId())).thenReturn(Optional.of(investment));
      investment.withdrawal();

      service.view(investment.getId());
    });
  }

  @Test
  void shouldThrowEntityNotFoundExceptionWhenInvestmentDoesNotExistOnFind() {
    Assertions.assertThrows(EntityNotFoundException.class, () -> {
      String investmentId = "123";
      Mockito.when(repository.findById(investmentId)).thenReturn(Optional.empty());

      service.find(investmentId);
    });
  }

  @Test
  void shouldReturnAGetInvestmentDtoWhenFindAnInvestment() {
    Investment investment = Investment.createInvestment(
        "123",
        new BigDecimal("1000"),
        LocalDateTime.of(2024, 1, 1, 10, 0));
    Mockito.when(repository.findById(investment.getId())).thenReturn(Optional.of(investment));

    GetInvestmentDto result = service.find(investment.getId());

    Assertions.assertEquals(investment.getId(), result.id());
    Assertions.assertEquals(investment.getOwnerId(), result.ownerId());
    Assertions.assertEquals(investment.getAmount(), result.amount());
    Assertions.assertEquals(investment.getCreationDate(), result.creationDate());
  }
}