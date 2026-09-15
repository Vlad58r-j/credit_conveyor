package com.vlad.project.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Value;

import java.time.LocalDate;

@Value
@Schema(description = "Дто для чтения основных данных клиента с базы данных")
public class ClientReadDto {

    @Schema(description = "Id в таблице", example = "2")
    Long id;

    @Schema(description = "Имя клиента", example = "Влад")
    String firstName;

    @Schema(description = "Фамилия клиента", example = "Кривонос")
    String lastName;

    @Schema(description = "Отчество клиента", example = "Игоревич")
    String middleName;

    @Schema(description = "Почта клиента", example = "example@example.com")
    String email;

    @Schema(description = "Дата рождения", example = "2004-12-12")
    LocalDate birthdate;
}
