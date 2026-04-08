package com.vlad.project.counter;

import com.vlad.project.config.RateProperties;
import com.vlad.project.dto.ScoringDataDto;
import com.vlad.project.dto.enumStatus.EmploymentStatus;
import com.vlad.project.dto.enumStatus.Gender;
import com.vlad.project.dto.enumStatus.MaritalStatus;
import com.vlad.project.dto.enumStatus.Position;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;

import static java.lang.Math.abs;
import static java.math.BigDecimal.*;
import static com.vlad.project.filter.ScoringDataFilter.scoringDataValidation;

@Slf4j
@Component
@RequiredArgsConstructor
public class CurrentRateCounter {

    private final RateProperties baseRate;

    public BigDecimal validRate(ScoringDataDto employee) {
        scoringDataValidation(employee);

        BigDecimal currentRate = baseRate.rate();

        BigDecimal statusScoring = employeeStatus(employee.getEmployment().getEmploymentStatus());
        BigDecimal positionScoring = employeePosition(employee.getEmployment().getPosition());
        BigDecimal maritalScoring = employeeMarital(employee.getMaritalStatus());
        BigDecimal dependentsScoring = employeeDependents(employee.getDependentAmount());
        BigDecimal genderScoring = employeeGender(employee.getGender(), employee.getBirthday());
        BigDecimal isInsuranceEnabledScoring = employeeInsurance(employee.getIsInsuranceEnabled());
        BigDecimal isSalaryClientScoring = employeeSalaryClient(employee.getIsSalaryClient());

        return currentRate.add(statusScoring)
                .add(positionScoring)
                .add(maritalScoring)
                .add(dependentsScoring)
                .add(genderScoring)
                .add(isInsuranceEnabledScoring)
                .add(isSalaryClientScoring);
    }

    private BigDecimal employeeSalaryClient(Boolean isSalaryClient) {
        return isSalaryClient ? valueOf(-1) : ZERO;
    }

    private BigDecimal employeeInsurance(Boolean isInsuranceEnabled) {
        return isInsuranceEnabled ? valueOf(-3) : ZERO;
    }

    private BigDecimal employeeGender(Gender gender, LocalDate birthday) {
        switch (gender) {
            case MALE -> {
                if (maleAge(birthday))
                    return valueOf(-3);
            }
            case FEMALE -> {
                if (femaleAge(birthday))
                    return valueOf(-3);
            }
            case NON_BINARY -> {
                return valueOf(3);
            }
        }
        return ZERO;
    }

    private BigDecimal employeeDependents(Integer dependentAmount) {
        return dependentAmount > 1 ? valueOf(1) : ZERO;
    }

    private BigDecimal employeeMarital(MaritalStatus maritalStatus) {
        switch (maritalStatus) {
            case MARRIED -> {
                return valueOf(-3);
            }
            case DIVORCED -> {
                return ONE;
            }
        }
        return ZERO;
    }

    private BigDecimal employeePosition(Position position) {
        switch (position) {
            case MID_MANAGER -> {
                return valueOf(-2);
            }
            case TOP_MANAGER -> {
                return valueOf(-4);
            }
            default -> {
                return ZERO;
            }
        }
    }

    private BigDecimal employeeStatus(EmploymentStatus status) {
        switch (status) {
            case SELF_EMPLOYED -> {
                return ONE;
            }
            case BUSINESS_OWNER -> {
                return valueOf(3);
            }
        }
                return ZERO;
    }

    private boolean femaleAge(LocalDate birthday) {
        var now = LocalDate.now();
        boolean moreMin = now.getYear() - birthday.getYear() >= 35 &&
                          abs(now.getMonthValue() - birthday.getMonthValue()) >= 0 &&
                          abs(now.getDayOfMonth() - birthday.getDayOfMonth()) > 0;
        boolean lessMax = now.getYear() - birthday.getYear() <= 65 &&
                          abs(now.getMonthValue() - birthday.getMonthValue()) >= 0 &&
                          abs(now.getDayOfMonth() - birthday.getDayOfMonth()) > 0;
        return moreMin && lessMax;
    }

    private boolean maleAge(LocalDate birthday) {
        var now = LocalDate.now();
        boolean moreMin = now.getYear() - birthday.getYear() >= 30 &&
                          abs(now.getMonthValue() - birthday.getMonthValue()) >= 0 &&
                          abs(now.getDayOfMonth() - birthday.getDayOfMonth()) > 0;
        boolean lessMax = now.getYear() - birthday.getYear() <= 55 &&
                          abs(now.getMonthValue() - birthday.getMonthValue()) >= 0 &&
                          abs(now.getDayOfMonth() - birthday.getDayOfMonth()) > 0;
        return moreMin && lessMax;
    }
}
