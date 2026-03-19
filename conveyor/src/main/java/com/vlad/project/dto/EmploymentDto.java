package com.vlad.project.dto;

import com.vlad.project.dto.enumStatus.EmploymentStatus;
import com.vlad.project.dto.enumStatus.Position;
import lombok.Value;

import java.math.BigDecimal;

@Value
public class EmploymentDto {

    EmploymentStatus employmentStatus;
    String employerINN;
    BigDecimal salary;
    Position position;
    Integer workExperienceTotal;
    Integer workExperienceCurrent;
}
