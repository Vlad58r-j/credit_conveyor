package com.vlad.project.dto;

import com.vlad.project.dto.enumStatus.Gender;
import com.vlad.project.dto.enumStatus.MaritalStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Value;

import java.time.LocalDate;

@Value
@Schema(name = "Данные пользователя", description = "Класс содержит дополнительные данные пользователя")
public class FinishRegistrationRequestDTO {

    @Schema(description = "Пол человека", example = "Male")
    Gender gender;

    @Schema(description = "Семейное положение", example = "MARRIED")
    MaritalStatus maritalStatus;

    @Schema(description = "Застрахованная сумма вклада", example = "100000")
    Integer dependentAmount;

    @Schema(description = "Дата выдачи паспорта", example = "2020-01-23")
    LocalDate passportIssueDate;

    @Schema(description = "Кем выдан паспорт", example = "УМВД")
    String passportIssueBranch;

    @Schema(description = "Данные о трудоустройстве заемщика",
            example = "EMPLOYED; 123456789011; 50000; WORKER; 13; 13")
    EmploymentDto employment;

    @Schema(description = "Аккаунт заемщика", example = "example")
    String account;
}
