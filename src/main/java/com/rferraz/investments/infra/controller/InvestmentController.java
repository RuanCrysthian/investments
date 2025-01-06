package com.rferraz.investments.infra.controller;

import com.rferraz.investments.domain.dto.InsertInvestmentInputDto;
import com.rferraz.investments.domain.dto.InsertInvestmentOutputDto;
import com.rferraz.investments.domain.dto.ViewInvestmentDto;
import com.rferraz.investments.domain.dto.WithdrawalInvestmentDto;
import com.rferraz.investments.domain.service.InvestmentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/investments")
public class InvestmentController {

  private final InvestmentService investmentService;

  public InvestmentController(InvestmentService investmentService) {
    this.investmentService = investmentService;
  }

  @PostMapping
  public ResponseEntity<InsertInvestmentOutputDto> createInvestment(@RequestBody InsertInvestmentInputDto input) {
    InsertInvestmentOutputDto result = investmentService.insertInvestment(input);
    return ResponseEntity.ok(result);
  }

  @GetMapping("{investment_id}/view")
  public ResponseEntity<ViewInvestmentDto> getInvestment(@PathVariable String investment_id) {
    Optional<ViewInvestmentDto> result = Optional.ofNullable(investmentService.view(investment_id));
    return result.map(ResponseEntity::ok)
      .orElse(ResponseEntity.notFound().build());
  }

  @PostMapping("/{id}/withdraw")
  public ResponseEntity<WithdrawalInvestmentDto> withdrawInvestment(@PathVariable String id) {
    WithdrawalInvestmentDto result = investmentService.withdrawalInvestment(id);
    return ResponseEntity.ok(result);
  }
}
