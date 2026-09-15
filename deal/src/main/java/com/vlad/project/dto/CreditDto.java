package com.vlad.project.dto;

import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Value;

import java.math.BigDecimal;
import java.util.List;

@Value
@Schema(name = "Кредитные данные", description = """
        Содержит основные кредитные данные для дальнейшего подсчета предложений""")
public class CreditDto {

    @Schema(description = "Сумма кредита", example = "124000")
    BigDecimal amount;

    @Schema(description = "Срок кредита", example = "36")
    Integer term;

    @Schema(description = "Ежемесячный платеж", example = "12000")
    BigDecimal monthlyPayment;

    @Schema(description = "Кредитная ставка", example = "14")
    BigDecimal rate;

    @Schema(description = "Полная стоимость кредита", example = "124300")
    BigDecimal psk;

    @Schema(description = "Наличие страховки", example = "true")
    Boolean isInsuranceEnabled;

    @Schema(description = "Зарплатный ли клиент", example = "false")
    Boolean isSalaryClient;

    @ArraySchema(schema = @Schema(implementation = PaymentScheduleElement.class,
            description = "График ежемесячных платежей", example = """
            {(1, 2026-12-12, 124300, 12000, 10000, 123100), ...}"""))
    List<PaymentScheduleElement> paymentSchedule;

}
