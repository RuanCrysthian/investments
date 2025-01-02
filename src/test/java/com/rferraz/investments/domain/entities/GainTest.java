package com.rferraz.investments.domain.entities;

import com.rferraz.investments.domain.exceptions.FieldIsRequiredException;
import com.rferraz.investments.domain.exceptions.InvalidAmountException;
import com.rferraz.investments.domain.exceptions.InvalidCurrentDateException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;


class GainTest {

  @Test
  void shouldInstantiateGainClass() {
    Gain gain = new Gain();
    Assertions.assertNotNull(gain);
  }

  @Test
  void shouldThrowInvalidCurrentDateWhenCurrentDateIsBeforeCreationDate() {
    Assertions.assertThrows(InvalidCurrentDateException.class, () -> {
      Gain.calculate(BigDecimal.TEN, LocalDateTime.now(), LocalDateTime.now().minusMinutes(1));
    });
  }

  @Test
  void shouldCalculateGainCorrectly() {
    BigDecimal amount = new BigDecimal("1000.00");
    LocalDateTime creationDate = LocalDateTime.of(2023, 1, 15, 10, 0);
    LocalDateTime currentDate = LocalDateTime.of(2023, 4, 15, 10, 0);
    BigDecimal expectedValue = new BigDecimal("1015.68");

    BigDecimal result = Gain.calculate(amount, creationDate, currentDate);

    Assertions.assertEquals(expectedValue, result);
  }

  @Test
  void shouldCalculateGainWithPartialMouth() {
    BigDecimal amount = new BigDecimal("1000.00");
    LocalDateTime creationDate = LocalDateTime.of(2023, 1, 15, 10, 0);
    LocalDateTime currentDate = LocalDateTime.of(2023, 5, 14, 10, 0);
    BigDecimal expectedValue = new BigDecimal("1015.68");

    BigDecimal result = Gain.calculate(amount, creationDate, currentDate);

    Assertions.assertEquals(expectedValue, result);
  }

  @Test
  void shouldThrowFieldIsRequiredExceptionWhenAmountIsNull() {
    Assertions.assertThrows(FieldIsRequiredException.class, () -> {
      BigDecimal amount = null;
      LocalDateTime creationDate = LocalDateTime.of(2023, 1, 15, 10, 0);
      LocalDateTime currentDate = LocalDateTime.of(2023, 4, 15, 10, 0);
      Gain.calculate(amount, creationDate, currentDate);
    });
  }

  @Test
  void shouldThrowFieldIsRequiredExceptionWhenAmountIsZero() {
    Assertions.assertThrows(InvalidAmountException.class, () -> {
      BigDecimal amount = BigDecimal.ZERO;
      LocalDateTime creationDate = LocalDateTime.of(2023, 1, 15, 10, 0);
      LocalDateTime currentDate = LocalDateTime.of(2023, 4, 15, 10, 0);
      Gain.calculate(amount, creationDate, currentDate);
    });
  }

  @Test
  void shouldThrowFieldIsRequiredExceptionWhenAmountIsNegative() {
    Assertions.assertThrows(InvalidAmountException.class, () -> {
      BigDecimal amount = new BigDecimal("-1000.00");
      LocalDateTime creationDate = LocalDateTime.of(2023, 1, 15, 10, 0);
      LocalDateTime currentDate = LocalDateTime.of(2023, 4, 15, 10, 0);
      Gain.calculate(amount, creationDate, currentDate);
    });
  }

  @Test
  void shouldThrowFieldIsRequiredExceptionWhenCreationDateIsNull() {
    Assertions.assertThrows(FieldIsRequiredException.class, () -> {
      BigDecimal amount = new BigDecimal("1000.00");
      LocalDateTime creationDate = null;
      LocalDateTime currentDate = LocalDateTime.of(2023, 4, 15, 10, 0);
      Gain.calculate(amount, creationDate, currentDate);
    });
  }

  @Test
  void shouldThrowFieldIsRequiredExceptionWhenCurrentDateIsNull() {
    Assertions.assertThrows(FieldIsRequiredException.class, () -> {
      BigDecimal amount = new BigDecimal("1000.00");
      LocalDateTime creationDate = LocalDateTime.of(2023, 1, 15, 10, 0);
      LocalDateTime currentDate = null;
      Gain.calculate(amount, creationDate, currentDate);
    });
  }
}