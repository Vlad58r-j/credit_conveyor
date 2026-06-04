package com.vlad.project.dto;

import lombok.Value;

import java.time.LocalDate;

@Value
public class ClientReadDto {

    Long id;
    String firstName;
    String lastName;
    String middleName;
    String email;
    LocalDate birthdate;
}
