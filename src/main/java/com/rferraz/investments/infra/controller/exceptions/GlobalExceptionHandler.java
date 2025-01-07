package com.rferraz.investments.infra.controller.exceptions;

import com.rferraz.investments.domain.exceptions.*;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.Instant;

@ControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(EntityNotFoundException.class)
  public ResponseEntity<ValidationError> handleEntityNotFound(EntityNotFoundException e, HttpServletRequest request) {
    HttpStatus status = HttpStatus.NOT_FOUND;
    ValidationError err = new ValidationError();

    // Configura informações do erro
    err.setTimestamp(Instant.now());
    err.setStatus(status.value());
    err.setError("Entity Not Found");
    err.setPath(request.getRequestURI());
    // Mensagem de erro
    err.setMessage(e.getMessage());  // A exceção normalmente tem uma mensagem descritiva
    return ResponseEntity.status(status).body(err);
  }

  @ExceptionHandler(FieldIsRequiredException.class)
  public ResponseEntity<ValidationError> handleFieldIsRequiredException(FieldIsRequiredException e, HttpServletRequest request) {
    HttpStatus status = HttpStatus.BAD_REQUEST;
    ValidationError err = new ValidationError();
    err.setTimestamp(Instant.now());
    err.setStatus(status.value());
    err.setError("Field Is Required");
    err.setPath(request.getRequestURI());
    // Mensagem de erro
    err.setMessage(e.getMessage());  // A exceção normalmente tem uma mensagem descritiva
    return ResponseEntity.status(status).body(err);
  }

  @ExceptionHandler(InvalidAmountException.class)
  public ResponseEntity<ValidationError> handleInvalidAmountException(InvalidAmountException e, HttpServletRequest request) {
    HttpStatus status = HttpStatus.BAD_REQUEST;
    ValidationError err = new ValidationError();
    err.setTimestamp(Instant.now());
    err.setStatus(status.value());
    err.setError("Invalid Amount");
    err.setPath(request.getRequestURI());
    // Mensagem de erro
    err.setMessage(e.getMessage());  // A exceção normalmente tem uma mensagem descritiva
    return ResponseEntity.status(status).body(err);
  }

  @ExceptionHandler(InvalidCreationDateException.class)
  public ResponseEntity<ValidationError> handleInvalidCreationDateException(InvalidCreationDateException e, HttpServletRequest request) {
    HttpStatus status = HttpStatus.BAD_REQUEST;
    ValidationError err = new ValidationError();
    err.setTimestamp(Instant.now());
    err.setStatus(status.value());
    err.setError("Invalid creationDate");
    err.setPath(request.getRequestURI());
    // Mensagem de erro
    err.setMessage(e.getMessage());  // A exceção normalmente tem uma mensagem descritiva
    return ResponseEntity.status(status).body(err);
  }

  @ExceptionHandler(InvalidCalculateYearsBetweenDatesException.class)
  public ResponseEntity<ValidationError> handleInvalidCalculateYearsBetweenDatesException(InvalidCalculateYearsBetweenDatesException e, HttpServletRequest request) {
    HttpStatus status = HttpStatus.BAD_REQUEST;
    ValidationError err = new ValidationError();
    err.setTimestamp(Instant.now());
    err.setStatus(status.value());
    err.setError("creationDate should be before withdrawalDate");
    err.setPath(request.getRequestURI());
    // Mensagem de erro
    err.setMessage(e.getMessage());  // A exceção normalmente tem uma mensagem descritiva
    return ResponseEntity.status(status).body(err);
  }

  @ExceptionHandler(InvalidCurrentDateException.class)
  public ResponseEntity<ValidationError> handleInvalidCurrentDateException(InvalidCurrentDateException e, HttpServletRequest request) {
    HttpStatus status = HttpStatus.BAD_REQUEST;
    ValidationError err = new ValidationError();
    err.setTimestamp(Instant.now());
    err.setStatus(status.value());
    err.setError("currentDate should be after creationDate");
    err.setPath(request.getRequestURI());
    // Mensagem de erro
    err.setMessage(e.getMessage());  // A exceção normalmente tem uma mensagem descritiva
    return ResponseEntity.status(status).body(err);
  }

  @ExceptionHandler(InvestmentAlreadyWithdrawException.class)
  public ResponseEntity<ValidationError> handleInvestmentAlreadyWithdrawException(InvestmentAlreadyWithdrawException e, HttpServletRequest request) {
    HttpStatus status = HttpStatus.BAD_REQUEST;
    ValidationError err = new ValidationError();
    err.setTimestamp(Instant.now());
    err.setStatus(status.value());
    err.setError("investment already withdrawal");
    err.setPath(request.getRequestURI());
    // Mensagem de erro
    err.setMessage(e.getMessage());  // A exceção normalmente tem uma mensagem descritiva
    return ResponseEntity.status(status).body(err);
  }
}
