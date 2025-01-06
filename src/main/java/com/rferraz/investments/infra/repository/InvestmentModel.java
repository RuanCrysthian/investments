package com.rferraz.investments.infra.repository;


import com.rferraz.investments.domain.entities.Investment;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Table(name = "investments")
@Entity
public class InvestmentModel {
  @Id
  @Column(name = "investment_id")
  private String investmentId;

  @Column(name = "owner_id")
  private String ownerId;

  @Column(name = "amount")
  private BigDecimal amount;

  @Column(name = "creation_date")
  private LocalDateTime creationDate;

  @Column(name = "was_withdrawal")
  private Boolean wasWithdrawal;

  @Column(name = "withdrawal")
  private LocalDateTime withdrawalDate;


  // Converte de Investment para InvestmentModel
  public static InvestmentModel fromDomain(Investment investment) {
    InvestmentModel model = new InvestmentModel();
    model.investmentId = investment.getId();
    model.ownerId = investment.getOwnerId();
    model.amount = investment.getAmount();
    model.creationDate = investment.getCreationDate();
    model.wasWithdrawal = investment.getWasWithdrawal();
    model.withdrawalDate = investment.getWithdrawalDate();
    return model;
  }

  // Converte de InvestmentModel para Investment
  public Investment toDomain() {
    return Investment.createInvestment(investmentId, ownerId, amount, creationDate, wasWithdrawal, withdrawalDate);
  }
}
