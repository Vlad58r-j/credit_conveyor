package com.vlad.project.filter;

import com.vlad.project.dto.ScoringDataDto;
import com.vlad.project.dto.enumStatus.EmploymentStatus;
import com.vlad.project.exception.PreScoringException;
import com.vlad.project.exception.ScoringException;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Period;
import java.util.Optional;

import static java.math.BigDecimal.*;

@Slf4j
public class ScoringDataFilter {

    public static final int MIN_AGE_CREDITOR = 20;
    public static final BigDecimal MAX_SALARY_AMOUNT_FOR_CREDIT_AMOUNT = valueOf(MIN_AGE_CREDITOR);
    public static final int MAX_AGE_CREDITOR = 60;
    public static final int MIN_MONTH_AMOUNT = 0;
    public static final int MIN_DAY_AMOUNT = 0;
    public static final int MIN_TOTAL_WORK_EXPERIENCE = 12;
    public static final int MIN_CURRENT_WORK_EXPERIENCE = 3;
    public static final int MIN_CREDIT_TERM = 6;
    public static final BigDecimal MIN_CREDIT_AMOUNT = valueOf(10_000);

    public static void scoringDataValidation(ScoringDataDto employee) {
        Optional.ofNullable(employee)
                .orElseThrow(() -> new ScoringException("ScoringDataDto не может быть null", "employee is null"));

        log.info("Валидируем заемщика работающего на позиции {}, со стажем работы {}," +
                 " желаемая сумма кредита {} на срок {}",
                employee.getEmployment().getPosition(),
                employee.getEmployment().getWorkExperienceTotal(),
                employee.getAmount(),
                employee.getTerm());

        Optional.ofNullable(employee.getEmployment().getEmploymentStatus())
                .filter(status -> !status.equals(EmploymentStatus.UNEMPLOYED))
                .orElseThrow(() -> new ScoringException("Заемщик должен быть трудоустроен", "status"));

        BigDecimal salary = employee.getEmployment().getSalary();
        Optional.ofNullable(employee.getAmount())
                .filter(amount -> amount.compareTo(salary.multiply(MAX_SALARY_AMOUNT_FOR_CREDIT_AMOUNT)) < 0)
                .orElseThrow(() -> new ScoringException("Сумма кредита должна быть меньше 20 зарплат", "amount"));

        Optional.ofNullable(employee.getBirthday())
                .filter(birthday -> {
                    Period period = Period.between(birthday, LocalDate.now());
                    return ((period.getYears() >= MIN_AGE_CREDITOR && period.getMonths() == MIN_MONTH_AMOUNT
                            && period.getDays() > MIN_DAY_AMOUNT) ||
                            (period.getYears() > MIN_AGE_CREDITOR && period.getMonths() > MIN_MONTH_AMOUNT))
                           && period.getYears() < MAX_AGE_CREDITOR;
                })
                .orElseThrow(() -> new ScoringException("Возраст заемщика должен быть от 20 до 60", "birthday"));

        Optional.ofNullable(employee.getEmployment().getWorkExperienceTotal())
                .filter(experienceTotal -> experienceTotal > MIN_TOTAL_WORK_EXPERIENCE)
                .orElseThrow(() -> new ScoringException("Общий опыт работы должен быть больше 12 месяцев",
                        "WorkExperienceTotal"));

        Optional.ofNullable(employee.getEmployment().getWorkExperienceCurrent())
                .filter(experienceTotal -> experienceTotal > MIN_CURRENT_WORK_EXPERIENCE)
                .orElseThrow(() -> new ScoringException("Текущий опыт работы должен быть больше 3 месяцев",
                        "WorkExperienceCurrent"));

        Optional.ofNullable(employee.getTerm())
                .filter(term -> term.compareTo(MIN_CREDIT_TERM) > -1)
                .orElseThrow(() -> new PreScoringException("Минимальный срок кредита 6 месяцев"));

        Optional.ofNullable(employee.getAmount())
                .filter(amount -> amount.compareTo(MIN_CREDIT_AMOUNT) > -1)
                .orElseThrow(() -> new PreScoringException("Сумма кредита должна быть более 10.000 рублей"));
    }
}
