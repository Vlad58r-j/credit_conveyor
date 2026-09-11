package com.vlad.project.filter;

import com.vlad.project.annotation.WorkExperienceTotalValid;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class WorkExperienceCurrentFilter implements ConstraintValidator<WorkExperienceTotalValid, Integer> {

    public static final int MIN_CURRENT_WORK_EXPERIENCE = 3;

    @Override
    public boolean isValid(Integer value, ConstraintValidatorContext context) {
        return value > MIN_CURRENT_WORK_EXPERIENCE;
    }
}
