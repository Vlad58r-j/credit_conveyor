package com.vlad.project.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@Schema(description = "Данные пользователя для подачи заявки на кредит")
public class LoanApplicationRequestDto {

    @JsonProperty("id")
    Long Id;

    @Schema(description = "Желаемая сумма кредита", example = "10000")
    BigDecimal amount;

    @Schema(description = "Время выплаты кредита", example = "6")
    Integer term;

    @Schema(description = "Имя заемщика", example = "example")
    String firstName;

    @Schema(description = "Фамилия заемщика", example = "example")
    String lastName;

    @Schema(description = "Отчество заемщика; Может быть пустым", example = "example")
    String middleName;

    @Schema(description = "Email адрес заемщика", example = "example@gmail.com")
    String email;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Schema(description = "Дата рождения заемщика", example = "2004-12-22")
    LocalDate birthdate;

    @Schema(description = "Серия паспорта заемщика", example = "1234")
    String passportSeries;

    @Schema(description = "Номер паспорта заемщика", example = "123456")
    String passportNumber;
}
