package com.vlad.project.counter;

import com.vlad.project.dto.PaymentScheduleElement;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static java.math.BigDecimal.*;

public class PaymentScheduleCounter {

    public static final int MONTHS_IN_YEAR = 12;
    public static final int TRANSFER_FROM_PERCENT = 100;

    public static List<PaymentScheduleElement> paymentSchedule(
            BigDecimal rate, BigDecimal totalAmount, BigDecimal monthlyPayment, Integer term) {
        List<PaymentScheduleElement> result = new ArrayList<>();
        BigDecimal monthlyPercent = rate.divide(valueOf(MONTHS_IN_YEAR), 10, RoundingMode.HALF_UP);
        monthlyPercent = monthlyPercent.divide(valueOf(TRANSFER_FROM_PERCENT), 10, RoundingMode.HALF_UP);

        int PAYMENT_NUMBER = 1;
        BigDecimal interestPayment;
        BigDecimal debtPayment;
        BigDecimal remainingDebt = totalAmount;
        LocalDate date = LocalDate.now();

        for (int i = 0; term.compareTo(i) > 0; i++) {
            interestPayment = remainingDebt.multiply(monthlyPercent)
                    .setScale(2, RoundingMode.HALF_UP);
            debtPayment = monthlyPayment.subtract(interestPayment);
            remainingDebt = remainingDebt.subtract(debtPayment);

            result.add(new PaymentScheduleElement(PAYMENT_NUMBER, date.plusMonths(PAYMENT_NUMBER), totalAmount,
                    interestPayment, debtPayment, remainingDebt));

            PAYMENT_NUMBER++;
        }

        return result;
    }

}
