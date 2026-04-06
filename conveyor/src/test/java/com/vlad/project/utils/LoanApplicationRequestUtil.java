package com.vlad.project.utils;

import com.vlad.project.dto.LoanApplicationRequestDto;

import java.time.LocalDate;

import static java.math.BigDecimal.valueOf;

public class LoanApplicationRequestUtil {

    public static LoanApplicationRequestDto getCorrectLoanApplicationRequestDto() {
        return new LoanApplicationRequestDto(
                valueOf(200000), 12,
                "Vlad", "Krivonos", "Igorevich",
                "vlad58r@gmail.com", LocalDate.of(2004, 12, 22),
                "1111", "222234");
    }

    public static LoanApplicationRequestDto getNotCorrectAmountLoanApplicationRequestDto() {
        return new LoanApplicationRequestDto(
                valueOf(20), 12,
                "Vlad", "Krivonos", "Igorevich",
                "vlad58r@gmail.com", LocalDate.of(2004, 12, 22),
                "1111", "222234");
    }

    public static LoanApplicationRequestDto getNotCorrectTermLoanApplicationRequestDto() {
        return new LoanApplicationRequestDto(
                valueOf(200000), null,
                "Vlad", "Krivonos", "Igorevich",
                "vlad58r@gmail.com", LocalDate.of(2004, 12, 22),
                "1111", "222234");
    }


    public static LoanApplicationRequestDto getNotCorrectNameLoanApplicationRequestDto() {
        return new LoanApplicationRequestDto(
                valueOf(200000), 12,
                "V", "Krivonos", "Igorevich",
                "vlad58r@gmail.com", LocalDate.of(2004, 12, 22),
                "1111", "222234");
    }

    public static LoanApplicationRequestDto getNotCorrectLastNameLoanApplicationRequestDto() {
        return new LoanApplicationRequestDto(
                valueOf(200000), 12,
                "Vlad", "K", "Igorevich",
                "vlad58r@gmail.com", LocalDate.of(2004, 12, 22),
                "1111", "222234");
    }

    public static LoanApplicationRequestDto getNotCorrectMidleNameLoanApplicationRequestDto() {
        return new LoanApplicationRequestDto(
                valueOf(200000), 12,
                "Vlad", "Krivonos", "I",
                "vlad58r@gmail.com", LocalDate.of(2004, 12, 22),
                "1111", "222234");
    }

    public static LoanApplicationRequestDto getNotCorrectEmailLoanApplicationRequestDto() {
        return new LoanApplicationRequestDto(
                valueOf(200000), 12,
                "Vlad", "Krivonos", "I",
                "vlad58rgmail.com", LocalDate.of(2004, 12, 22),
                "1111", "222234");
    }

    public static LoanApplicationRequestDto getNotCorrectBirthdateLoanApplicationRequestDto() {
        return new LoanApplicationRequestDto(
                valueOf(200000), 12,
                "Vlad", "Krivonos", "I",
                "vlad58r@gmail.com", LocalDate.of(2024, 12, 22),
                "1111", "222234");
    }

    public static LoanApplicationRequestDto getNotCorrectPassportSeriesLoanApplicationRequestDto() {
        return new LoanApplicationRequestDto(
                valueOf(200000), 12,
                "Vlad", "Krivonos", "I",
                "vlad58rgmail.com", LocalDate.of(2004, 12, 22),
                "123", "222234");
    }

    public static LoanApplicationRequestDto getNotCorrectPassportNumberLoanApplicationRequestDto() {
        return new LoanApplicationRequestDto(
                valueOf(200000), 12,
                "Vlad", "Krivonos", "I",
                "vlad58rgmail.com", LocalDate.of(2004, 12, 22),
                "123", "12345");
    }
}
