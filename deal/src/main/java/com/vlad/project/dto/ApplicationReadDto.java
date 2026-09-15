package com.vlad.project.dto;

import com.vlad.project.database.entity.Client;
import com.vlad.project.dto.enumStatus.ApplicationStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Value;

import java.time.LocalDate;

@Value
@Schema(description = "Дто для чтения основных данных заявки с базы данных")
public class ApplicationReadDto {

    @Schema(description = "Id клиента в таблице", example = "2")
    Long id;

    @Schema(description = "Статус заявки", example = "APPROVED")
    ApplicationStatus status;

    @Schema(description = "Даты создания предложения", example = "2025-12-12")
    LocalDate creationDate;

    @Schema(description = "Данные из таблицы Client", example = """
            (2, vlad, krivonos, igorevich, 2004-12-22, vlad58r@gmail.com,
            Male, Single, 13490, (2, 1234, 123456, 2024-12-30, PNZ),
            (2, Employed, 123456789011, 20000, Worker, 12, 4))""")
    Client client;
}
