package com.rferraz.investments.domain.repository;

import com.rferraz.investments.domain.entities.Investment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InvestmentRepositoryInterface extends JpaRepository<Investment, String> {
}
