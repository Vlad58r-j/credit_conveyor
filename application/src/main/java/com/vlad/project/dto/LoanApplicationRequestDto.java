package com.vlad.project.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Данные пользователя для подачи заявки на кредит")
public class LoanApplicationRequestDto {

    BigDecimal amount;
    Integer term;
    String firstName;
    String lastName;
    String middleName;
    String email;
    LocalDate birthDate;
    String passportSeries;
    String passportNumber;
}
