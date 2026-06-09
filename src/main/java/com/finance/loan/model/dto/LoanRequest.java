package com.finance.loan.model.dto;

import java.time.LocalDateTime;
import java.math.BigDecimal;

public class LoanRequest {
    private String name;
    private BigDecimal principal;
    private BigDecimal interestRate;
    private Integer termMonths;
    private String collateral;
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public BigDecimal getPrincipal() { return principal; }
    public void setPrincipal(BigDecimal principal) { this.principal = principal; }
    public BigDecimal getInterestRate() { return interestRate; }
    public void setInterestRate(BigDecimal interestRate) { this.interestRate = interestRate; }
    public Integer getTermMonths() { return termMonths; }
    public void setTermMonths(Integer termMonths) { this.termMonths = termMonths; }
    public String getCollateral() { return collateral; }
    public void setCollateral(String collateral) { this.collateral = collateral; }
}
