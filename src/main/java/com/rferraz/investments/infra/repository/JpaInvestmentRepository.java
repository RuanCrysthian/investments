package com.rferraz.investments.infra.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaInvestmentRepository extends JpaRepository<InvestmentModel, String> {
}