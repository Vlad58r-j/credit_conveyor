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

    public static void scoringDataValidation(ScoringDataDto employee) {
        Optional.ofNullable(employee)
                .orElseThrow(() -> new ScoringException("ScoringDataDto не может быть null"));

        log.info("Валидируем заемщика работающего на позиции {}, со стажем работы {}," +
                 " желаемая сумма кредита {} на срок {}",
                employee.getEmployment().getPosition(),
                employee.getEmployment().getWorkExperienceTotal(),
                employee.getAmount(),
                employee.getTerm());

        Optional.ofNullable(employee.getEmployment().getEmploymentStatus())
                .filter(status -> !status.equals(EmploymentStatus.UNEMPLOYED))
                .orElseThrow(() -> new ScoringException("Заемщик должен быть трудоустроен"));

        BigDecimal salary = employee.getEmployment().getSalary();
        Optional.ofNullable(employee.getAmount())
                .filter(amount -> amount.compareTo(salary.multiply(valueOf(20))) < 0)
                .orElseThrow(() -> new ScoringException("Сумма кредита должна быть меньше 20 зарплат"));

        Optional.ofNullable(employee.getBirthday())
                .filter(birthday -> {
                    Period period = Period.between(birthday, LocalDate.now());
                    return ((period.getYears() >= 20 && period.getMonths() == 0 && period.getDays() > 0) ||
                            (period.getYears() > 20 && period.getMonths() > 0))
                           && period.getYears() < 60;
                })
                .orElseThrow(() -> new ScoringException("Возраст заемщика должен быть от 20 до 60"));

        Optional.ofNullable(employee.getEmployment().getWorkExperienceTotal())
                .filter(experienceTotal -> experienceTotal > 12)
                .orElseThrow(() -> new ScoringException("Общий опыт работы должен быть больше 12 месяцев"));

        Optional.ofNullable(employee.getEmployment().getWorkExperienceCurrent())
                .filter(experienceTotal -> experienceTotal > 3)
                .orElseThrow(() -> new ScoringException("Текущий опыт работы должен быть больше 3 месяцев"));

        Optional.ofNullable(employee.getTerm())
                .filter(term -> term.compareTo(6) > -1)
                .orElseThrow(() -> new PreScoringException("Минимальный срок кредита 6 месяцев"));

        Optional.ofNullable(employee.getAmount())
                .filter(amount -> amount.compareTo(new BigDecimal(10_000)) > -1)
                .orElseThrow(() -> new PreScoringException("Сумма кредита должна быть более 10.000 рублей"));
    }
}
