package com.vlad.project.dto;

import com.vlad.project.dto.enumStatus.Gender;
import com.vlad.project.dto.enumStatus.MaritalStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Value;

import java.math.BigDecimal;
import java.time.LocalDate;

@Value
public class ScoringDataDto {

    @NotNull(message = "Обязательно укажите сумму кредита")
    @Schema(description = "Желаемая сумма кредита", example = "10000")
    @DecimalMin(value = "10000", message = "Сумма кредита должна быть более 10.000 рублей")
    BigDecimal amount;

    @NotNull(message = "Обязательно укажите срок кредита")
    @Schema(description = "Время выплаты кредита", example = "6")
    @Min(value = 6,  message = "Минимальный срок кредита 6 месяцев")
    Integer term;
    String firstName;
    String lastName;
    Gender gender;
    LocalDate birthday;
    String passportSeries;
    String passportNumber;
    LocalDate passportIssueDate;
    String passportIssueBranch;
    MaritalStatus maritalStatus;
    Integer dependentAmount;
    EmploymentDto employment;
    String account;
    Boolean isInsuranceEnabled;
    Boolean isSalaryClient;
}
