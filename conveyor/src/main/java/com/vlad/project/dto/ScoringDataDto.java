package com.vlad.project.dto;

import com.vlad.project.dto.enumStatus.Gender;
import com.vlad.project.dto.enumStatus.MaritalStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.Value;
import org.springframework.format.annotation.DateTimeFormat;

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

    @NotNull(message = "Имя клиента не может быть пустым")
    @Schema(description = "Имя заемщика", example = "example")
    @Size(min = 2, max = 30, message = "Имя должно быть от 2 до 30 символов")
    String firstName;

    @NotNull(message = "Фамилия клиента не может быть пустым")
    @Schema(description = "Фамилия заемщика", example = "example")
    @Size(min = 2, max = 30, message = "Фамилия должно быть от 2 до 30 символов")
    String lastName;

    @NotNull(message = "У человека должен быть пол")
    @Schema(description = "Пол заемщика", example = "MALE")
    Gender gender;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @NotNull(message = "Обязательно укажите дату рождения")
    @Schema(description = "Дата рождения заемщика", example = "2004-12-22")
    LocalDate birthday;

    @NotBlank(message = "Обязательно укажите паспортные данные")
    @Schema(description = "Серия паспорта заемщика", example = "1234")
    @Pattern(regexp = "\\d{4}", message = "Серия паспорта содержит 4 цифры")
    String passportSeries;

    @NotBlank(message = "Обязательно укажите паспортные данные")
    @Schema(description = "Номер паспорта заемщика", example = "123456")
    @Pattern(regexp = "\\d{6}", message = "Серия паспорта содержит 6 цифр")
    String passportNumber;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @NotBlank(message = "Дата выдачи паспорта не может быть пустой")
    @Schema(description = "Дата выдачи паспорта", example = "2020-01-23")
    LocalDate passportIssueDate;

    @NotBlank(message = "Кем выдан паспорт не может быть пустой")
    @Schema(description = "Кем выдан паспорт", example = "УМВД")
    String passportIssueBranch;

    @NotBlank(message = "Семейное положение не может быть пустым")
    @Schema(description = "Семейное положение", example = "MARRIED")
    MaritalStatus maritalStatus;

    @NotBlank(message = "Застрахованная сумма вклада не может быть пустой")
    @Schema(description = "Застрахованная сумма вклада", example = "100000")
    Integer dependentAmount;

    @NotBlank(message = "Данные о трудоустройстве заемщика не могут быть пустыми")
    @Schema(description = "Данные о трудоустройстве заемщика",
            example = "EMPLOYED; 123456789011; 50000; WORKER; 13; 13")
    EmploymentDto employment;

    @NotBlank(message = "Аккаунт заемщика не может быть пустой")
    @Schema(description = "Аккаунт заемщика", example = "example")
    String account;

    @NotBlank(message = "Наличие страховки не может быть пустым")
    @Schema(description = "Будет ли страховка или нет", example = "false")
    Boolean isInsuranceEnabled;

    @NotBlank(message = "Зарплатный клиент или нет не может быть пустым")
    @Schema(description = "Зарплатные клиент или нет", example = "false")
    Boolean isSalaryClient;
}
