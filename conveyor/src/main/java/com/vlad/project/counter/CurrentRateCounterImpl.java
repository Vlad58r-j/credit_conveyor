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
import java.time.Period;

import static java.math.BigDecimal.*;
import static com.vlad.project.filter.ScoringDataFilter.scoringDataValidation;

@Slf4j
@Component
@RequiredArgsConstructor
public class CurrentRateCounterImpl implements CurrentRateCounter{

    private final RateProperties baseRate;
    public static final int MIN_MALE_AGE = 30;
    public static final int MAX_MALE_AGE = 55;
    public static final int MALE_DAY_COUNT = 0;
    public static final int MIN_FEMALE_AGE = 35;
    public static final int MAX_FEMALE_AGE = 65;
    public static final int FEMALE_DAY_COUNT = 0;
    public static final int MALE_MONTH_COUNT = 0;
    public static final int FEMALE_MONTH_COUNT = 0;
    public static final int MIN_COUNT_DEPENDENT_AMOUNT = 1;
    public static final BigDecimal CHANGE_IF_NOT_MANGER = ZERO;
    public static final BigDecimal CHANGE_IF_JUST_WORKER = ZERO;
    public static final BigDecimal CHANGE_IF_SELF_EMPLOYED = ONE;
    public static final BigDecimal CHANGE_PERCENT_IF_DIVORCED = ONE;
    public static final BigDecimal CHANGE_RATE_IF_MARRIED = valueOf(-3);
    public static final BigDecimal CHANGE_RATE_NOT_SALARY_CLIENT = ZERO;
    public static final BigDecimal CHANGE_IF_BUSINESS_OWNER = valueOf(3);
    public static final BigDecimal CHANGE_RATE_IF_NON_BINARY = valueOf(3);
    public static final BigDecimal MINUS_RATE_IF_MID_MANAGER = valueOf(-2);
    public static final BigDecimal MINUS_RATE_IF_TOP_MANAGER = valueOf(-4);
    public static final BigDecimal CHANGE_RATE_SALARY_CLIENT = valueOf(-1);
    public static final BigDecimal CHANGE_RATE_NOT_INSURANCE_ENABLED = ZERO;
    public static final BigDecimal CHANGE_PERCENT_IF_NOT_INFO_MARITAL = ZERO;
    public static final BigDecimal CHANGE_RATE_IF_NOT_CORRECT_CONDITION = ZERO;
    public static final BigDecimal CHANGE_RATE_INSURANCE_ENABLED = valueOf(-3);
    public static final BigDecimal NOT_CHANGE_IF_A_LOT_DEPENDENT_AMOUNT = ZERO;
    public static final BigDecimal CHANGE_IF_ONE_DEPENDENT_AMOUNT = valueOf(1);
    public static final BigDecimal CHANGE_RATE_IF_MALE_AND_AGE_IN_THE_LIMIT = valueOf(-3);
    public static final BigDecimal CHANGE_RATE_IF_FEMALE_AND_AGE_IN_THE_LIMIT = valueOf(-3);

    @Override
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
        return isSalaryClient ? CHANGE_RATE_SALARY_CLIENT : CHANGE_RATE_NOT_SALARY_CLIENT;
    }

    private BigDecimal employeeInsurance(Boolean isInsuranceEnabled) {
        return isInsuranceEnabled ? CHANGE_RATE_INSURANCE_ENABLED : CHANGE_RATE_NOT_INSURANCE_ENABLED;
    }

    private BigDecimal employeeGender(Gender gender, LocalDate birthday) {
        switch (gender) {
            case MALE -> {
                if (maleAge(birthday))
                    return CHANGE_RATE_IF_MALE_AND_AGE_IN_THE_LIMIT;
            }
            case FEMALE -> {
                if (femaleAge(birthday))
                    return CHANGE_RATE_IF_FEMALE_AND_AGE_IN_THE_LIMIT;
            }
            case NON_BINARY -> {
                return CHANGE_RATE_IF_NON_BINARY;
            }
        }
        return CHANGE_RATE_IF_NOT_CORRECT_CONDITION;
    }

    private BigDecimal employeeDependents(Integer dependentAmount) {
        return dependentAmount > MIN_COUNT_DEPENDENT_AMOUNT ? CHANGE_IF_ONE_DEPENDENT_AMOUNT
                : NOT_CHANGE_IF_A_LOT_DEPENDENT_AMOUNT;
    }

    private BigDecimal employeeMarital(MaritalStatus maritalStatus) {
        switch (maritalStatus) {
            case MARRIED -> {
                return CHANGE_RATE_IF_MARRIED;
            }
            case DIVORCED -> {
                return CHANGE_PERCENT_IF_DIVORCED;
            }
        }
        return CHANGE_PERCENT_IF_NOT_INFO_MARITAL;
    }

    private BigDecimal employeePosition(Position position) {
        switch (position) {
            case MID_MANAGER -> {
                return MINUS_RATE_IF_MID_MANAGER;
            }
            case TOP_MANAGER -> {
                return MINUS_RATE_IF_TOP_MANAGER;
            }
            default -> {
                return CHANGE_IF_NOT_MANGER;
            }
        }
    }

    private BigDecimal employeeStatus(EmploymentStatus status) {
        switch (status) {
            case SELF_EMPLOYED -> {
                return CHANGE_IF_SELF_EMPLOYED;
            }
            case BUSINESS_OWNER -> {
                return CHANGE_IF_BUSINESS_OWNER;
            }
        }
                return CHANGE_IF_JUST_WORKER;
    }

    private boolean femaleAge(LocalDate birthday) {
        Period period = Period.between(birthday, LocalDate.now());
        return ((period.getYears() >= MIN_FEMALE_AGE && period.getMonths() == FEMALE_MONTH_COUNT
                && period.getDays() > FEMALE_DAY_COUNT) ||
                (period.getYears() > MIN_FEMALE_AGE && period.getMonths() > FEMALE_MONTH_COUNT))
               && period.getYears() <= MAX_FEMALE_AGE;
    }

    private boolean maleAge(LocalDate birthday) {
        Period period = Period.between(birthday, LocalDate.now());
        return ((period.getYears() >= MIN_MALE_AGE && period.getMonths() == MALE_MONTH_COUNT
                && period.getDays() > MALE_DAY_COUNT) ||
                (period.getYears() > MIN_MALE_AGE && period.getMonths() > MALE_MONTH_COUNT))
               && period.getYears() <= MAX_MALE_AGE;
    }
}
