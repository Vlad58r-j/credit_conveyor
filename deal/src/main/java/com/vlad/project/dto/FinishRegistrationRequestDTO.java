package com.vlad.project.dto;

import com.vlad.project.dto.enumStatus.Gender;
import com.vlad.project.dto.enumStatus.MaritalStatus;
import lombok.Value;

import java.time.LocalDate;

@Value
public class FinishRegistrationRequestDTO {

    Gender gender;
    MaritalStatus maritalStatus;
    Integer dependentAmount;
    LocalDate passportIssueDate;
    String passportIssueBranch;
    EmploymentDto employment;
    String account;
}
