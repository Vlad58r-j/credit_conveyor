package com.vlad.project.dto;

import lombok.Value;

import java.math.BigDecimal;
import java.time.LocalDate;

@Value
public class LoanApplicationRequestDto {

    BigDecimal amount;
    Integer term;
    String firstName;
    String lastName;
    String middleName;
    String email;
    LocalDate birthdate;
    String passportSeries;
    String passportNumber;
}
