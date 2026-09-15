package com.vlad.project.utils;

import com.vlad.project.dto.EmploymentDto;
import com.vlad.project.dto.FinishRegistrationRequestDTO;
import com.vlad.project.dto.enumStatus.EmploymentStatus;
import com.vlad.project.dto.enumStatus.Gender;
import com.vlad.project.dto.enumStatus.MaritalStatus;
import com.vlad.project.dto.enumStatus.Position;

import java.time.LocalDate;

import static java.math.BigDecimal.valueOf;

public class FinishRegistrationRequestDtoUtill {

    public static FinishRegistrationRequestDTO registrationDTO() {
        return new FinishRegistrationRequestDTO(
                Gender.MALE,
                MaritalStatus.SINGLE,
                20000,
                LocalDate.now(),
                "PNZ",
                new EmploymentDto(EmploymentStatus.EMPLOYED,
                        "1234567890",
                        valueOf(60000),
                        Position.WORKER,
                        24,
                        10
                ),
                "vlad58"
        );
    }
}
