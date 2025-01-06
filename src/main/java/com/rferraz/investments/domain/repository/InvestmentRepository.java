package com.rferraz.investments.domain.repository;

import com.rferraz.investments.domain.entities.Investment;

import java.util.Optional;

public interface InvestmentRepository {
  Optional<Investment> findById(String investmentId);

  void save(Investment investment);
}
