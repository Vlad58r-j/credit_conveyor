package com.vlad.project.counter;

import com.vlad.project.dto.PaymentScheduleElement;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static java.math.BigDecimal.*;

public class PaymentScheduleCounter {

    public static List<PaymentScheduleElement> paymentSchedule(
            BigDecimal rate, BigDecimal totalAmount, BigDecimal monthlyPayment, Integer term) {
        List<PaymentScheduleElement> result = new ArrayList<>();
        BigDecimal monthlyPercent = rate.divide(valueOf(12), 10, RoundingMode.HALF_UP);
        monthlyPercent = monthlyPercent.divide(valueOf(100), 10, RoundingMode.HALF_UP);

        int number = 1;
        BigDecimal interestPayment;
        BigDecimal debtPayment;
        BigDecimal remainingDebt = totalAmount;
        LocalDate date = LocalDate.now();

        for (int i = 0; term.compareTo(i) > 0; i++) {
            interestPayment = remainingDebt.multiply(monthlyPercent)
                    .setScale(2, RoundingMode.HALF_UP);
            debtPayment = monthlyPayment.subtract(interestPayment);
            remainingDebt = remainingDebt.subtract(debtPayment);

            result.add(new PaymentScheduleElement(number, date.plusMonths(number), totalAmount,
                    interestPayment, debtPayment, remainingDebt));

            number++;
        }

        return result;
    }

}
