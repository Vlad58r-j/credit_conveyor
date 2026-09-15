package com.vlad.project.utils;

import com.vlad.project.database.entity.Credit;
import com.vlad.project.dto.enumStatus.CreditStatus;

import java.util.List;

import static java.math.BigDecimal.valueOf;

public class CreditUtils {

    public static Credit getCredit() {
        return Credit.builder()
                .id(1L)
                .amount(valueOf(400000))
                .term(24)
                .monthlyPayment(valueOf(12000))
                .rate(valueOf(13))
                .psk(valueOf(3400))
                .paymentSchedule(List.of())
                .isInsuranceEnabled(true)
                .isSalaryClient(false)
                .creditStatus(CreditStatus.CALCULATED)
            .build();
    }

    public static Credit getCreditForCreditTest() {
        return Credit.builder()
                .id(1L)
                .amount(valueOf(400000))
                .term(24)
                .monthlyPayment(valueOf(12000))
                .rate(valueOf(13))
                .psk(valueOf(3400))
                .paymentSchedule(List.of())
                .isInsuranceEnabled(false)
                .isSalaryClient(true)
                .creditStatus(CreditStatus.CALCULATED)
                .build();
    }
}
