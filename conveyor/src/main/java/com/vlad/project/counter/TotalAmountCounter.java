package com.vlad.project.counter;

import java.math.BigDecimal;

import static java.math.BigDecimal.valueOf;

public class TotalAmountCounter {

    public static BigDecimal totalCreditAmount(BigDecimal amount, BigDecimal rate, Integer term,
                                               Boolean isInsuranceEnabled) {
        BigDecimal monthlyPayment = MonthlyPaymentCounter.monthlyPaymentCounter(
                rate, term, amount, isInsuranceEnabled);

        return monthlyPayment.multiply(valueOf(term));
    }
}
