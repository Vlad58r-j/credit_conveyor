package com.vlad.project.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Value;

import java.math.BigDecimal;

@Value
@Schema(description = "Данные о кредитном предложении")
public class LoanOfferDto {

    @Schema(description = "Id заявки", example = "3")
    Long applicationId;

    @Schema(description = "Сумма заема", example = "100000")
    BigDecimal requestedAmount;

    @Schema(description = "Итоговая выплата кредита", example = "120000")
    BigDecimal totalAmount;

    @Schema(description = "Срок кредита", example = "12")
    Integer term;

    @Schema(description = "Ежемесячный платеж", example = "10000")
    BigDecimal monthlyPayment;

    @Schema(description = "Ставка по кредиту", example = "14")
    BigDecimal rate;

    @Schema(description = "Наличие страховки", example = "true")
    Boolean isInsuranceEnabled;

    @Schema(description = "Зарплатный ли клиент", example = "false")
    Boolean isSalaryClient;

}
