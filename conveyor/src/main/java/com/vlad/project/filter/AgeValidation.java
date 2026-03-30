package com.vlad.project.filter;

import com.vlad.project.annotation.Age;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.time.LocalDate;

import static java.lang.Math.*;

public class AgeValidation implements ConstraintValidator<Age, LocalDate> {

    @Override
    public boolean isValid(LocalDate value, ConstraintValidatorContext context) {
        var now = LocalDate.now();
        return now.getYear() - value.getYear() >= 18 &&
               abs(now.getMonthValue() - value.getMonthValue()) >= 0 &&
               abs(now.getDayOfMonth() - value.getDayOfMonth()) > 0;
    }
}
