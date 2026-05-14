package com.vlad.project.filter;

import com.vlad.project.annotation.EmploymentStatusValid;
import com.vlad.project.dto.enumStatus.EmploymentStatus;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class EmploymentStatusFilter implements ConstraintValidator<EmploymentStatusValid, EmploymentStatus> {

    @Override
    public boolean isValid(EmploymentStatus value, ConstraintValidatorContext context) {
        return !value.equals(EmploymentStatus.UNEMPLOYED);
    }
}
