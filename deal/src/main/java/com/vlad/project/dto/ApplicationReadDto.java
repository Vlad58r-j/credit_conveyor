package com.vlad.project.dto;

import com.vlad.project.database.entity.Client;
import com.vlad.project.dto.enumStatus.ApplicationStatus;
import lombok.Value;

import java.time.LocalDate;

@Value
public class ApplicationReadDto {

    Long id;
    ApplicationStatus status;
    LocalDate creationDate;
    Client client;

}
