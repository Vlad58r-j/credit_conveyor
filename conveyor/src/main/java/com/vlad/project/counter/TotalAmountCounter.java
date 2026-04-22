package com.vlad.project.counter;

import java.math.BigDecimal;

import static java.math.BigDecimal.valueOf;

public class TotalAmountCounter {

    public static BigDecimal totalCreditAmount(BigDecimal amount, BigDecimal rate, Integer term, Boolean isInsuranceEnabled) {
        if (isInsuranceEnabled) {
            var insurance = amount.multiply(valueOf(0.01)).multiply(valueOf(term));

            amount = amount.add(insurance);
        }
        BigDecimal monthlyPayment = MonthlyPaymentCounter.monthlyPaymentCounter(rate, term, amount);

        return monthlyPayment.multiply(valueOf(term));
    }
}
