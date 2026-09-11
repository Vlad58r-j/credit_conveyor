package com.vlad.project.filter;

import com.vlad.project.annotation.SalaryValid;
import com.vlad.project.dto.ScoringDataDto;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import static java.math.BigDecimal.*;

public class SalaryFilter implements ConstraintValidator<SalaryValid, ScoringDataDto> {

    public static final java.math.BigDecimal AMOUNT_SALARY_FOR_VALID = valueOf(20);

    @Override
    public boolean isValid(ScoringDataDto value, ConstraintValidatorContext context) {
        var salaryEmployee = value.getEmployment().getSalary();
        var amountEmployee = value.getAmount();
        var max = salaryEmployee.multiply(AMOUNT_SALARY_FOR_VALID).max(amountEmployee);
        return max.equals(amountEmployee);
    }
}
