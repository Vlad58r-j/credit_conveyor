package com.vlad.project.dto;

import com.vlad.project.annotation.Age;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Данные пользователя для подачи заявки на кредит")
public class LoanApplicationRequestDto {

    @Schema(description = "Желаемая сумма кредита", example = "10000")
    @NotNull(message = "Обязательно укажите сумму кредита")
    @DecimalMin(value = "10000", message = "Сумма кредита должна быть более 10.000 рублей")
    @DecimalMax(value = "10000000", message = "Сумма кредита должна быть более 10.000.000 рублей")
    BigDecimal amount;

    @Schema(description = "Время выплаты кредита", example = "6")
    @NotNull(message = "Обязательно укажите срок кредита")
    @Min(value = 6, message = "Минимальный срок кредита 6 месяцев")
    @Max(value = 360, message = "Максимальный срок кредита 360 месяцев")
    Integer term;

    @Schema(description = "Имя заемщика", example = "example")
    @NotBlank(message = "Имя клиента не может быть пустым")
    @Size(min = 2, max = 30, message = "Имя должно быть от 2 до 30 символов")
    String firstName;

    @Schema(description = "Фамилия заемщика", example = "example")
    @NotBlank(message = "Фамилия клиента не может быть пустым")
    @Size(min = 2, max = 30, message = "Фамилия должно быть от 2 до 30 символов")
    String lastName;

    @Schema(description = "Отчество заемщика; Может быть пустым", example = "example")
    @Size(min = 2, max = 30, message = "Отчество должно быть от 2 до 30 символов")
    String middleName;

    @Schema(description = "Email адрес заемщика", example = "example@gmail.com")
    @NotBlank(message = "Поле не должно быть пустым")
    @Email(regexp = "[\\w.]{2,50}@[\\w.]{2,20}", message = "Введите конкретный email")
    String email;

    @Schema(description = "Дата рождения заемщика", example = "2004-12-22")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @NotNull(message = "Обязательно укажите дату рождения")
    @Age(message = "Минимальный возраст для выдачи кредита - 18 лет")
    LocalDate birthDate;

    @Schema(description = "Серия паспорта заемщика", example = "1234")
    @NotBlank(message = "Обязательно укажите паспортные данные")
    @Pattern(regexp = "\\d{4}", message = "Серия паспорта содержит 4 цифры")
    String passportSeries;

    @Schema(description = "Номер паспорта заемщика", example = "123456")
    @NotBlank(message = "Обязательно укажите паспортные данные")
    @Pattern(regexp = "\\d{6}", message = "Серия паспорта содержит 6 цифр")
    String passportNumber;
}
