package com.vlad.project.util;

import com.vlad.project.dto.LoanOfferDto;

import static java.math.BigDecimal.valueOf;

public class LoanOfferDtoUtil {

    public static LoanOfferDto correctLoanOfferDto() {
        return LoanOfferDto.builder()
                .applicationId(1L)
                .requestedAmount(valueOf(140000))
                .totalAmount(valueOf(100000))
                .term(12)
                .isInsuranceEnabled(false)
                .isSalaryClient(true)
                .build();
    }
}
