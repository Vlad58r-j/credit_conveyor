package com.vlad.project.filter;

import com.vlad.project.annotation.WorkExperienceTotalValid;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class WorkExperienceCurrentFilter implements ConstraintValidator<WorkExperienceTotalValid, Integer> {

    @Override
    public boolean isValid(Integer value, ConstraintValidatorContext context) {
        return value > 3;
    }
}
