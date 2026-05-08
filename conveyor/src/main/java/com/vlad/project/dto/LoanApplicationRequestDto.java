package com.vlad.project.dto;

import com.vlad.project.annotation.Age;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.Value;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.LocalDate;

@Value
@Schema(description = "Данные пользователя для подачи заявки на кредит")
public class LoanApplicationRequestDto {

    @NotNull(message = "Обязательно укажите сумму кредита")
    @Schema(description = "Желаемая сумма кредита", example = "10000")
    @DecimalMin(value = "10000", message = "Сумма кредита должна быть более 10.000 рублей")
    BigDecimal amount;

    @NotNull(message = "Обязательно укажите срок кредита")
    @Schema(description = "Время выплаты кредита", example = "6")
    @Min(value = 6,  message = "Минимальный срок кредита 6 месяцев")
    Integer term;

    @NotBlank(message = "Имя клиента не может быть пустым")
    @Schema(description = "Имя заемщика", example = "example")
    @Size(min = 2, max = 30, message = "Имя должно быть от 2 до 30 символов")
    String firstName;

    @NotBlank(message = "Фамилия клиента не может быть пустым")
    @Schema(description = "Фамилия заемщика", example = "example")
    @Size(min = 2, max = 30, message = "Фамилия должно быть от 2 до 30 символов")
    String lastName;

    @Size(min = 2, max = 30, message = "Отчество должно быть от 2 до 30 символов")
    @Schema(description = "Отчество заемщика; Может быть пустым", example = "example")
    String middleName;

    @Email(regexp = "[\\w.]{2,50}@[\\w.]{2,20}")
    @NotBlank(message = "Обязательно укажите email адрес")
    @Schema(description = "Email адрес заемщика", example = "example@gmail.com")
    String email;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @NotNull(message = "Обязательно укажите дату рождения")
    @Age(message = "Минимальный возраст для выдачи кредита - 18 лет")
    @Schema(description = "Дата рождения заемщика", example = "2004-12-22")
    LocalDate birthdate;

    @NotBlank(message = "Обязательно укажите паспортные данные")
    @Schema(description = "Серия паспорта заемщика", example = "1234")
    @Pattern(regexp = "\\d{4}", message = "Серия паспорта содержит 4 цифры")
    String passportSeries;

    @NotBlank(message = "Обязательно укажите паспортные данные")
    @Schema(description = "Номер паспорта заемщика", example = "123456")
    @Pattern(regexp = "\\d{6}", message = "Серия паспорта содержит 6 цифр")
    String passportNumber;
}
