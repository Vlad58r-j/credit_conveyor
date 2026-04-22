package com.vlad.project.filter;

import com.vlad.project.annotation.Age;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.time.LocalDate;
import java.time.Period;

public class AgeValidation implements ConstraintValidator<Age, LocalDate> {

    @Override
    public boolean isValid(LocalDate value, ConstraintValidatorContext context) {
        var now = LocalDate.now();
        Period between = Period.between(value, now);
        return (between.getYears() >= 18 && between.getMonths() == 0 && between.getDays() > 0) ||
               (between.getYears() >= 18 && between.getMonths() > 0);
    }
}
