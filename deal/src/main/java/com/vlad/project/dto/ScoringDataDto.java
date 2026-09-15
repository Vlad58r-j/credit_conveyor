package com.vlad.project.dto;

import com.vlad.project.dto.enumStatus.Gender;
import com.vlad.project.dto.enumStatus.MaritalStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Value;

import java.math.BigDecimal;
import java.time.LocalDate;

@Value
@Builder
@Schema(description = "Расширенные данные о пользователе")
public class ScoringDataDto {

    @Schema(description = "Желаемая сумма кредита", example = "10000")
    BigDecimal amount;

    @Schema(description = "Время выплаты кредита", example = "6")
    Integer term;

    @Schema(description = "Имя заемщика", example = "example")
    String firstName;

    @Schema(description = "Фамилия заемщика", example = "example")
    String lastName;

    @Schema(description = "Пол заемщика", example = "MALE")
    Gender gender;

    @Schema(description = "Дата рождения заемщика", example = "2004-12-22")
    LocalDate birthday;

    @Schema(description = "Серия паспорта заемщика", example = "1234")
    String passportSeries;

    @Schema(description = "Номер паспорта заемщика", example = "123456")
    String passportNumber;

    @Schema(description = "Дата выдачи паспорта", example = "2020-01-23")
    LocalDate passportIssueDate;

    @Schema(description = "Кем выдан паспорт", example = "УМВД")
    String passportIssueBranch;

    @Schema(description = "Семейное положение", example = "MARRIED")
    MaritalStatus maritalStatus;

    @Schema(description = "Застрахованная сумма вклада", example = "100000")
    Integer dependentAmount;

    @Schema(description = "Данные о трудоустройстве заемщика",
            example = "EMPLOYED; 123456789011; 50000; WORKER; 13; 13")
    EmploymentDto employment;

    @Schema(description = "Аккаунт заемщика", example = "example")
    String account;

    @Schema(description = "Будет ли страховка или нет", example = "false")
    Boolean isInsuranceEnabled;

    @Schema(description = "Зарплатные клиент или нет", example = "false")
    Boolean isSalaryClient;
}
