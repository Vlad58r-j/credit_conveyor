package com.vlad.project.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Данные о ежемесячных платежах и остатка")
public class PaymentScheduleElement {

    @Schema(description = "Номер платежа", example = "12")
    private Integer number;

    @Schema(description = "Дата выплаты", example = "2026-08-12")
    private LocalDate date;

    @Schema(description = "Общая сумма", example = "129999")
    private BigDecimal totalPayment;

    @Schema(description = "Выплата процентов", example = "1244")
    private BigDecimal interestPayment;

    @Schema(description = "Платеж по долгу", example = "2000")
    private BigDecimal debtPayment;

    @Schema(description = "Остаток задолженности", example = "114000")
    private BigDecimal remainingDebt;
}
