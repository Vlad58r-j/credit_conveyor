package com.vlad.project.filter;

import com.vlad.project.annotation.WorkExperienceTotalValid;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class WorkExperienceTotalFilter implements ConstraintValidator<WorkExperienceTotalValid, Integer> {

    public static final int MIN_TOTAL_WORK_EXPERIENCE = 12;

    @Override
    public boolean isValid(Integer value, ConstraintValidatorContext context) {
        return value > MIN_TOTAL_WORK_EXPERIENCE;
    }
}
