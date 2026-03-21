package com.vlad.project.dto;

import lombok.Value;
import org.springframework.format.annotation.DateTimeFormat;

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
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    LocalDate birthdate;
    String passportSeries;
    String passportNumber;
}
