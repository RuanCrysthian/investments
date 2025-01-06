package com.rferraz.investments.infra.repository;

import com.rferraz.investments.domain.entities.Investment;
import com.rferraz.investments.domain.repository.InvestmentRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class InvestmentRepositoryImpl implements InvestmentRepository {

  private final JpaInvestmentRepository jpaRepository;

  public InvestmentRepositoryImpl(JpaInvestmentRepository jpaRepository) {
    this.jpaRepository = jpaRepository;
  }

  @Override
  public Optional<Investment> findById(String investmentId) {
    return jpaRepository.findById(investmentId).map(InvestmentModel::toDomain);
  }

  @Override
  public void save(Investment investment) {
    InvestmentModel entity = InvestmentModel.fromDomain(investment);
    jpaRepository.save(entity);
  }
}
