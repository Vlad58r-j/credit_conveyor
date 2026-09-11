package com.vlad.project.counter;

import java.math.BigDecimal;
import java.math.RoundingMode;

import static java.math.BigDecimal.ONE;
import static java.math.BigDecimal.valueOf;

public class MonthlyPaymentCounter {

    public static final BigDecimal ONE_PERCENT_FOR_COUNTING_INSURANCE = valueOf(0.01);
    public static final BigDecimal TRANSFER_RATE_FROM_PERCENT_TO_FRACTION = valueOf(100);
    public static final BigDecimal MONTH_AMOUNT = valueOf(12);
    public static final BigDecimal PLUS_ONE_TO_RATE_PRECENT = ONE;
    public static final BigDecimal ONE_MINUS_DENOMINATOR_FOR_FRACTION = ONE;
    public static final BigDecimal ONE_DIVIDE_ON_POW_FOR_NEGATIVE_POW = ONE;

    public static BigDecimal monthlyPaymentCounter(BigDecimal rate, Integer term, BigDecimal amount,
                                                   Boolean isInsuranceEnabled) {
        if (isInsuranceEnabled) {
            var insurance = amount.multiply(ONE_PERCENT_FOR_COUNTING_INSURANCE).multiply(valueOf(term));

            amount = amount.add(insurance);
        }

        var percent = rate.divide(TRANSFER_RATE_FROM_PERCENT_TO_FRACTION, 10, RoundingMode.HALF_UP)
                .divide(MONTH_AMOUNT, 10, RoundingMode.HALF_UP);

        var onePlusPercent = PLUS_ONE_TO_RATE_PRECENT.add(percent);

        var pow = onePlusPercent.pow(term);
        var reversePow = ONE_DIVIDE_ON_POW_FOR_NEGATIVE_POW.divide(pow, 10, RoundingMode.HALF_UP);

        var oneMinusPow = ONE_MINUS_DENOMINATOR_FOR_FRACTION.subtract(reversePow);

        return amount.multiply(percent)
                .divide(oneMinusPow, 0, RoundingMode.HALF_UP);
    }
}