package com.vlad.project.dto;

import lombok.EqualsAndHashCode;
import lombok.Value;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.LocalDate;

@Value
@EqualsAndHashCode
public class PaymentScheduleElement {

    Integer number;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    LocalDate date;
    BigDecimal totalPayment;
    BigDecimal interestPayment;
    BigDecimal debtPayment;
    BigDecimal remainingDebt;
}
