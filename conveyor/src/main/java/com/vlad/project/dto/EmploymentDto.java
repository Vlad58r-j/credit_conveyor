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

    @NotBlank
    @EmploymentStatusValid(message = "Заемщик должен быть трудоустроен")
    EmploymentStatus employmentStatus;

    @NotBlank
    String employerINN;

    @NotBlank
    @SalaryValid(message = "Сумма кредита должна быть меньше 20 зарплат")
    BigDecimal salary;

    @NotBlank
    Position position;

    @NotBlank
    @WorkExperienceTotalValid(message = "Общий опыт работы должен быть больше 12 месяцев")
    Integer workExperienceTotal;

    @NotBlank
    @WorkExperienceCurrentValid(message = "Текущий опыт работы должен быть больше 3 месяцев")
    Integer workExperienceCurrent;
}