package com.vlad.project.dto;

import lombok.Value;
import jakarta.validation.constraints.NotBlank;
import com.vlad.project.annotation.SalaryValid;
import com.vlad.project.dto.enumStatus.Position;
import io.swagger.v3.oas.annotations.media.Schema;
import com.vlad.project.dto.enumStatus.EmploymentStatus;
import com.vlad.project.annotation.EmploymentStatusValid;
import com.vlad.project.annotation.WorkExperienceTotalValid;
import com.vlad.project.annotation.WorkExperienceCurrentValid;

import java.math.BigDecimal;

@Value
@Schema(name = "Данные сотрудника", description = "Класс содержит информацию о рабочей информации сотрудника")
public class EmploymentDto {

    @NotBlank(message = "Как трудоустроен заемщик не может быть пустым")
    @EmploymentStatusValid(message = "Заемщик должен быть трудоустроен")
    @Schema(description = "Статус трудоустройства", example = "EMPLOYED")
    EmploymentStatus employmentStatus;

    @NotBlank(message = "ИНН заемщика не может быть пустым")
    @Schema(description = "ИНН заемщика", example = "123456789011")
    String employerINN;

    @NotBlank(message = "Зарплата заемщика не может быть пустой ")
    @SalaryValid(message = "Сумма кредита должна быть меньше 20 зарплат")
    @Schema(description = "Зарплата заемщика", example = "50000")
    BigDecimal salary;

    @NotBlank(message = "Рабочая позиция заемщика не может быть пустой")
    @Schema(description = "Позиция на которой работает заемщик", example = "WORKER")
    Position position;

    @NotBlank(message = "Общий опыт работы не может быть пустым")
    @WorkExperienceTotalValid(message = "Общий опыт работы должен быть больше 12 месяцев")
    @Schema(description = "Общий опыт работы", example = "13")
    Integer workExperienceTotal;

    @NotBlank(message = "Текущий опыт работы не может быть пустым")
    @WorkExperienceCurrentValid(message = "Текущий опыт работы должен быть больше 3 месяцев")
    @Schema(description = "Опыт работы после последнего трудоустройства", example = "13")
    Integer workExperienceCurrent;
}