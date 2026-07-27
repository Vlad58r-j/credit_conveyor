package com.vlad.project.utils;

import com.vlad.project.dto.LoanApplicationRequestDto;
import com.vlad.project.dto.LoanOfferDto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

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

    public static List<LoanOfferDto> getCorrectLoanOfferDto() {
        return List.of(new LoanOfferDto(
                1L, new BigDecimal("100000"), new BigDecimal("100000"), 12,
                new BigDecimal("12444"), new BigDecimal("16"), true, true));
    }
}
