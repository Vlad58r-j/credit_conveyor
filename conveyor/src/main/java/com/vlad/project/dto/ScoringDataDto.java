package com.vlad.project.dto;

import com.vlad.project.dto.enumStatus.Gender;
import com.vlad.project.dto.enumStatus.MaritalStatus;
import lombok.Value;

import java.math.BigDecimal;
import java.time.LocalDate;

@Value
public class ScoringDataDto {

    BigDecimal amount;
    Integer term;
    String firstName;
    String lastName;
    Gender gender;
    LocalDate birthday;
    String passportSeries;
    String passportNumber;
    LocalDate passportIssueDate;
    String passportIssueBranch;
    MaritalStatus maritalStatus;
    Integer dependentAmount;
    EmploymentDto employment;
    String account;
    Boolean isInsuranceEnabled;
    Boolean isSalaryClient;
}
