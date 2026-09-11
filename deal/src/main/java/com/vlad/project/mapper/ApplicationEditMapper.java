package com.vlad.project.mapper;

import com.vlad.project.database.entity.Application;
import com.vlad.project.database.entity.Client;
import com.vlad.project.database.entity.Passport;
import com.vlad.project.dto.LoanApplicationRequestDto;
import com.vlad.project.dto.enumStatus.ApplicationStatus;
import com.vlad.project.dto.enumStatus.Gender;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.time.LocalDate;

@Component
@RequiredArgsConstructor
public class ApplicationEditMapper implements Mapper<LoanApplicationRequestDto, Application> {


    @Override
    public Application map(LoanApplicationRequestDto dto) {
        Application application = new Application();
        copy(dto, application);

        return application;
    }

    private void copy(LoanApplicationRequestDto dto, Application application) {
        application.setStatus(ApplicationStatus.PREAPPROVAL);
        application.setCreationDate(LocalDate.now());
        application.setStatusHistory(List.of(application.getStatus()));
        application.setClient(Client.builder()
                        .lastname(dto.getLastName())
                        .firstname(dto.getFirstName())
                        .middleName(dto.getMiddleName())
                        .birthDate(dto.getBirthdate())
                        .email(dto.getEmail())
                        .passport(Passport.builder()
                                .series(dto.getPassportSeries())
                                .number(dto.getPassportNumber())
                                .build())
                        .gender(Gender.NON_BINARY)
                .build());
    }

}