package com.vlad.project.filter;

import com.vlad.project.annotation.SalaryValid;
import com.vlad.project.dto.ScoringDataDto;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import static java.math.BigDecimal.*;

public class SalaryFilter implements ConstraintValidator<SalaryValid, ScoringDataDto> {

    @Override
    public boolean isValid(ScoringDataDto value, ConstraintValidatorContext context) {
        var salaryEmployee = value.getEmployment().getSalary();
        var amountEmployee = value.getAmount();
        var max = salaryEmployee.multiply(valueOf(20)).max(amountEmployee);
        return max.equals(amountEmployee);
    }
}
