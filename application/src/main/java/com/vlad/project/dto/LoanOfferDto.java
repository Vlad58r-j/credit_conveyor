package com.vlad.project.dto;

import lombok.*;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
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
