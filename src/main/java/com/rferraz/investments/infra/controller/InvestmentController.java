package com.rferraz.investments.infra.controller;

import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rferraz.investments.domain.dto.GetInvestmentDto;
import com.rferraz.investments.domain.dto.InsertInvestmentInputDto;
import com.rferraz.investments.domain.dto.InsertInvestmentOutputDto;
import com.rferraz.investments.domain.dto.ViewInvestmentDto;
import com.rferraz.investments.domain.dto.WithdrawalInvestmentDto;
import com.rferraz.investments.domain.service.InvestmentService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/api/v1/investments")
public class InvestmentController {

  private final InvestmentService investmentService;

  public InvestmentController(InvestmentService investmentService) {
    this.investmentService = investmentService;
  }

  @Operation(description = "create a new investment.")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "create an investment"),
      @ApiResponse(responseCode = "400", description = "invalid request")
  })
  @PostMapping
  public ResponseEntity<InsertInvestmentOutputDto> createInvestment(@RequestBody InsertInvestmentInputDto input) {
    InsertInvestmentOutputDto result = investmentService.insertInvestment(input);
    return ResponseEntity.ok(result);
  }

  @Operation(description = "Visualize the expected value of an investment.")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "expected value of an investment"),
      @ApiResponse(responseCode = "404", description = "investment_id not found")
  })
  @GetMapping("{investment_id}/view")
  public ResponseEntity<ViewInvestmentDto> getInvestment(@PathVariable String investment_id) {
    Optional<ViewInvestmentDto> result = Optional.ofNullable(investmentService.view(investment_id));
    return result.map(ResponseEntity::ok)
        .orElse(ResponseEntity.notFound().build());
  }

  @Operation(description = "withdraw an investment.")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "withdraw an investment"),
      @ApiResponse(responseCode = "404", description = "investment_id not found")
  })
  @PostMapping("/{investment_id}/withdraw")
  public ResponseEntity<WithdrawalInvestmentDto> withdrawInvestment(@PathVariable String investment_id) {
    WithdrawalInvestmentDto result = investmentService.withdrawalInvestment(investment_id);
    return ResponseEntity.ok(result);
  }

  @Operation(description = "Find an investment by id.")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "expected value of an investment"),
      @ApiResponse(responseCode = "404", description = "investment_id not found")
  })
  @GetMapping("/{investment_id}")
  public ResponseEntity<GetInvestmentDto> findById(@PathVariable String investment_id) {
    Optional<GetInvestmentDto> result = Optional.ofNullable(investmentService.find(investment_id));
    return result.map(ResponseEntity::ok)
        .orElse(ResponseEntity.notFound().build());
  }
}
