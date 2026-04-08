package com.vlad.project.counter;

import java.math.BigDecimal;

public interface MonthlyPaymentCounter {

    BigDecimal monthlyPaymentCounter(BigDecimal amount, Integer term,
                                            BigDecimal rate, Boolean isInsurance,
                                            Boolean salaryClient);

    BigDecimal amountCounter(BigDecimal amount, Integer term, BigDecimal rate,
                                    Boolean isInsurance, Boolean salaryClient);
}
