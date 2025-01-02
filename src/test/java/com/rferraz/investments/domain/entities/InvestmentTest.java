package com.rferraz.investments.domain.entities;

import com.rferraz.investments.domain.exceptions.FieldIsRequiredException;
import com.rferraz.investments.domain.exceptions.InvalidAmountException;
import com.rferraz.investments.domain.exceptions.InvalidCreationDateException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

class InvestmentTest {

  @Test
  void shouldCreateAnInvestment() {
    String ownerId = "1";
    BigDecimal amount = new BigDecimal(1000);
    LocalDateTime creation = LocalDateTime.of(2025, 1, 1, 10, 0);

    Investment result = Investment.createInvestment(ownerId, amount, creation);

    Assertions.assertEquals(ownerId, result.getOwnerId());
    Assertions.assertEquals(amount, result.getAmount());
    Assertions.assertEquals(creation, result.getCreationDate());
    Assertions.assertNotNull(result.getId());
  }

  @Test
  void shouldThrowFieldIsRequiredExceptionWhenOwnerIdIsNull() {
    Assertions.assertThrows(FieldIsRequiredException.class, () -> {
      Investment investment = Investment.createInvestment(null, new BigDecimal(1000), LocalDateTime.now());
    });
  }

  @Test
  void shouldThrowFieldIsRequiredExceptionWhenOwnerIdIsEmpty() {
    Assertions.assertThrows(FieldIsRequiredException.class, () -> {
      Investment investment = Investment.createInvestment(" ", new BigDecimal(1000), LocalDateTime.now());
    });
  }

  @Test
  void shouldThrowFieldIsRequiredExceptionWhenAmountIdIsNull() {
    Assertions.assertThrows(FieldIsRequiredException.class, () -> {
      Investment investment = Investment.createInvestment("1", null, LocalDateTime.now());
    });
  }

  @Test
  void shouldThrowInvalidAmountExceptionWhenAmountIsNegative() {
    Assertions.assertThrows(InvalidAmountException.class, () -> {
      Investment investment = Investment.createInvestment("1", new BigDecimal(-1000), LocalDateTime.now());
    });
  }

  @Test
  void shouldThrowInvalidAmountExceptionWhenAmountIsZero() {
    Assertions.assertThrows(InvalidAmountException.class, () -> {
      Investment investment = Investment.createInvestment("1", BigDecimal.ZERO, LocalDateTime.now());
    });
  }

  @Test
  void shouldThrowFieldIsRequiredExceptionWhenCreationDateIsNull() {
    Assertions.assertThrows(FieldIsRequiredException.class, () -> {
      Investment investment = Investment.createInvestment("1", new BigDecimal(1000), null);
    });
  }

  @Test
  void shouldThrowInvalidCreationDateExceptionWhenCreationDateIsInTheFuture() {
    Assertions.assertThrows(InvalidCreationDateException.class, () -> {
      Investment investment = Investment.createInvestment("1", new BigDecimal(1000), LocalDateTime.now().plusMinutes(1));
    });
  }

  @Test
  void shouldGenerateUniqueIdsForDifferentInstances() {
    Investment investment1 = Investment.createInvestment("1", new BigDecimal(1000), LocalDateTime.now());
    Investment investment2 = Investment.createInvestment("1", new BigDecimal(1000), LocalDateTime.now());

    Assertions.assertNotEquals(investment1.getId(), investment2.getId());
  }

  @Test
  void shouldGenerateValidUUID() {
    Investment result = Investment.createInvestment("1", new BigDecimal(1000), LocalDateTime.now());

    Assertions.assertDoesNotThrow(() -> UUID.fromString(result.getId()));
  }
}