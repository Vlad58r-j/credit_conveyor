package com.vlad.project.counter;

import java.math.BigDecimal;
import java.math.RoundingMode;

import static java.math.BigDecimal.ONE;
import static java.math.BigDecimal.valueOf;

public class MonthlyPaymentCounter {

    public static BigDecimal monthlyPaymentCounter(BigDecimal rate, Integer term, BigDecimal amount,
                                                   Boolean isInsuranceEnabled){
        if (isInsuranceEnabled) {
            var insurance = amount.multiply(valueOf(0.01)).multiply(valueOf(term));

            amount = amount.add(insurance);
        }

        var percent = rate.divide(valueOf(100), 10, RoundingMode.HALF_UP)
                .divide(valueOf(12), 10, RoundingMode.HALF_UP);

        var onePlusPercent = ONE.add(percent);

        var pow = onePlusPercent.pow(term);
        var reversePow = ONE.divide(pow, 10, RoundingMode.HALF_UP);

        var oneMinusPow = ONE.subtract(reversePow);

        return amount.multiply(percent)
                .divide(oneMinusPow, 0, RoundingMode.HALF_UP);
    }
}