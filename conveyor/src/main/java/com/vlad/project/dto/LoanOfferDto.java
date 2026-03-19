package com.vlad.project.dto;

import lombok.Value;

import java.math.BigDecimal;

@Value
public class LoanOfferDto {

    Long applicationId;
    BigDecimal requestedAmount;
    BigDecimal totalAmount;
    Integer term;
    BigDecimal monthlyPayment;
    BigDecimal rate;
    Boolean isInsuranceEnabled;
    Boolean isSalaryClient;

}
