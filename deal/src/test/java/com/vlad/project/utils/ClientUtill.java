package com.vlad.project.utils;

import com.vlad.project.database.entity.Client;
import com.vlad.project.database.entity.Employment;
import com.vlad.project.database.entity.Passport;
import com.vlad.project.dto.enumStatus.EmploymentStatus;
import com.vlad.project.dto.enumStatus.Gender;
import com.vlad.project.dto.enumStatus.Position;

import static java.math.BigDecimal.valueOf;

public class ClientUtill {

    public static Client client() {
        return Client.builder()
                .id(2L)
                .firstname("vlad")
                .lastname("krivonos")
                .gender(Gender.MALE)
                .employment(Employment.builder()
                        .employmentStatus(EmploymentStatus.EMPLOYED)
                        .employerInn("1234567890")
                        .salary(valueOf(60000))
                        .position(Position.WORKER)
                        .workExperienceTotal(24)
                        .workExperienceCurrent(10)
                        .build())
                .passport(Passport.builder()
                        .series("1234")
                        .number("123456")
                        .build())
                .build();
    }
}
