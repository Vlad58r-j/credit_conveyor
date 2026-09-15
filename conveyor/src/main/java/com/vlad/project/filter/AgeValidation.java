package com.vlad.project.filter;

import com.vlad.project.annotation.Age;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.time.LocalDate;
import java.time.Period;

public class AgeValidation implements ConstraintValidator<Age, LocalDate> {

    public static final int MIN_AGE_FOR_CREDITOR = 18;
    public static final int MIN_DAY_AMOUNT = 0;
    public static final int MIN_MONTH_AMOUNT = 0;

    @Override
    public boolean isValid(LocalDate value, ConstraintValidatorContext context) {
        var now = LocalDate.now();
        Period between = Period.between(value, now);
        return (between.getYears() >= MIN_AGE_FOR_CREDITOR && between.getMonths() == MIN_MONTH_AMOUNT
                && between.getDays() > MIN_DAY_AMOUNT) ||
               (between.getYears() >= MIN_AGE_FOR_CREDITOR && between.getMonths() > MIN_MONTH_AMOUNT);
    }
}
