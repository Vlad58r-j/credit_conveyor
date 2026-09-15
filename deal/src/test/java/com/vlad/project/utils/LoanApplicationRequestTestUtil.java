package com.vlad.project.utils;

import com.vlad.project.dto.LoanApplicationRequestDto;

import java.time.LocalDate;

public class LoanApplicationRequestTestUtil {

    public static LoanApplicationRequestDto getCorrectLoanApplicationDto() {
        return LoanApplicationRequestDto.builder()
                .id(null)
                .lastName("Krivonos")
                .firstName("Vladislav")
                .middleName("Igorevich")
                .birthdate(LocalDate.of(2005, 12, 4))
                .email("vlad59r@gmail.com")
                .build();
    }
}
