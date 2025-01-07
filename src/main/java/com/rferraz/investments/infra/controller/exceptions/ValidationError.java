package com.rferraz.investments.infra.controller.exceptions;

import java.util.ArrayList;
import java.util.List;

public class ValidationError extends StandardError {

  private List<FieldMessage> errors = new ArrayList<>();

  public void addError(String fieldName, String message) {
    errors.add(new FieldMessage(fieldName, message));
  }


}
