package com.vlad.project.dto;

import com.vlad.project.dto.enumStatus.EmploymentStatus;
import com.vlad.project.dto.enumStatus.Position;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Value;

import java.math.BigDecimal;

@Value
@Schema(name = "Данные сотрудника", description = "Класс содержит информацию о рабочей информации сотрудника")
public class EmploymentDto {

    @Schema(description = "Статус трудоустройства", example = "EMPLOYED")
    EmploymentStatus employmentStatus;

    @Schema(description = "ИНН заемщика", example = "123456789011")
    String employerINN;

    @Schema(description = "Зарплата заемщика", example = "50000")
    BigDecimal salary;

    @Schema(description = "Позиция на которой работает заемщик", example = "WORKER")
    Position position;

    @Schema(description = "Общий опыт работы", example = "13")
    Integer workExperienceTotal;

    @Schema(description = "Опыт работы после последнего трудоустройства", example = "13")
    Integer workExperienceCurrent;
}