package com.vlad.project.utils;

import com.vlad.project.dto.LoanApplicationRequestDto;

import java.time.LocalDate;

public class LoanApplicationRequestTestUtil {

    public static LoanApplicationRequestDto correctLoanApplicationDto = LoanApplicationRequestDto.builder()
            .id(null)
            .lastName("Krivonos")
            .firstName("Vladislav")
            .middleName("Igorevich")
            .birthdate(LocalDate.of(2005, 12, 4))
            .email("vlad59r@gmail.com")
            .build();

    public static LoanApplicationRequestDto notCorrectNameLoanApplicationDto = LoanApplicationRequestDto.builder()
            .id(null)
            .lastName("Krivonos")
            .firstName(null)
            .middleName("Igorevich")
            .birthdate(LocalDate.of(2005, 12, 4))
            .email("vlad12r@gmail.com")
            .build();

    public static LoanApplicationRequestDto notCorrectLastNameLoanApplicationDto = LoanApplicationRequestDto.builder()
            .id(null)
            .lastName(null)
            .firstName("Vladislav")
            .middleName("Igorevich")
            .birthdate(LocalDate.of(2005, 12, 4))
            .email("vlad50r@gmail.com")
            .build();

}
