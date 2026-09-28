package com.vlad.project.util;

import com.vlad.project.dto.LoanApplicationRequestDto;

import java.time.LocalDate;

import static java.math.BigDecimal.valueOf;

public class LoanApplicationRequestDtoUtil {

    public static LoanApplicationRequestDto correctLoanApplicationDto() {
        return LoanApplicationRequestDto.builder()
                .amount(valueOf(124000))
                .term(12)
                .firstName("Vlad")
                .lastName("Krivonos")
                .middleName("Igorevich")
                .email("vlad58r@gmail.com")
                .birthDate(LocalDate.of(2004, 12, 22))
                .passportSeries("1234")
                .passportNumber("123456")
                .build();
    }

    public static LoanApplicationRequestDto correctLoanApplicationDtoWithoutMiddleName() {
        return LoanApplicationRequestDto.builder()
                .amount(valueOf(124000))
                .term(12)
                .firstName("Vlad")
                .lastName("Krivonos")
                .email("vlad58r@gmail.com")
                .birthDate(LocalDate.of(2004, 12, 22))
                .passportSeries("1234")
                .passportNumber("123456")
                .build();
    }

    public static LoanApplicationRequestDto notCorrectLoanApplicationDtoWithNotCorrectAmount() {
        return LoanApplicationRequestDto.builder()
                .amount(valueOf(100))
                .term(12)
                .firstName("Vlad")
                .lastName("Krivonos")
                .middleName("Igorevich")
                .email("vlad58r@gmail.com")
                .birthDate(LocalDate.of(2004, 12, 22))
                .passportSeries("1234")
                .passportNumber("123456")
                .build();
    }

    public static LoanApplicationRequestDto notCorrectLoanApplicationDtoWithNotCorrectTerm() {
        return LoanApplicationRequestDto.builder()
                .amount(valueOf(100000))
                .term(1)
                .firstName("Vlad")
                .lastName("Krivonos")
                .middleName("Igorevich")
                .email("vlad58r@gmail.com")
                .birthDate(LocalDate.of(2004, 12, 22))
                .passportSeries("1234")
                .passportNumber("123456")
                .build();
    }

    public static LoanApplicationRequestDto notCorrectLoanApplicationDtoWithNotCorrectFirstName() {
        return LoanApplicationRequestDto.builder()
                .amount(valueOf(1000000))
                .term(12)
                .firstName("V")
                .lastName("Krivonos")
                .middleName("Igorevich")
                .email("vlad58r@gmail.com")
                .birthDate(LocalDate.of(2004, 12, 22))
                .passportSeries("1234")
                .passportNumber("123456")
                .build();
    }

    public static LoanApplicationRequestDto notCorrectLoanApplicationDtoWithNotCorrectLastName() {
        return LoanApplicationRequestDto.builder()
                .amount(valueOf(1000000))
                .term(12)
                .firstName("Vlad")
                .lastName("Krivonoshdhbvhdgbjkvnfdjvnjhdnfvkndnvjdfvkdv")
                .middleName("Igorevich")
                .email("vlad58r@gmail.com")
                .birthDate(LocalDate.of(2004, 12, 22))
                .passportSeries("1234")
                .passportNumber("123456")
                .build();
    }

    public static LoanApplicationRequestDto notCorrectLoanApplicationDtoWithNotCorrectEmail() {
        return LoanApplicationRequestDto.builder()
                .amount(valueOf(104440))
                .term(12)
                .firstName("Vlad")
                .lastName("Krivonos")
                .middleName("Igorevich")
                .email("vlad58rgmailcom")
                .birthDate(LocalDate.of(2004, 12, 22))
                .passportSeries("1234")
                .passportNumber("123456")
                .build();
    }

    public static LoanApplicationRequestDto notCorrectLoanApplicationDtoWithNotCorrectBirthDate() {
        return LoanApplicationRequestDto.builder()
                .amount(valueOf(105550))
                .term(12)
                .firstName("Vlad")
                .lastName("Krivonos")
                .middleName("Igorevich")
                .email("vlad58r@gmail.com")
                .birthDate(LocalDate.of(2024, 12, 22))
                .passportSeries("1234")
                .passportNumber("123456")
                .build();
    }

    public static LoanApplicationRequestDto notCorrectLoanApplicationDtoWithNotCorrectPassportSeries() {
        return LoanApplicationRequestDto.builder()
                .amount(valueOf(105550))
                .term(12)
                .firstName("Vlad")
                .lastName("Krivonos")
                .middleName("Igorevich")
                .email("vlad58r@gmail.com")
                .birthDate(LocalDate.of(2004, 12, 22))
                .passportSeries("14")
                .passportNumber("123456")
                .build();
    }

    public static LoanApplicationRequestDto notCorrectLoanApplicationDtoWithNotCorrectPassportNumber() {
        return LoanApplicationRequestDto.builder()
                .amount(valueOf(104440))
                .term(12)
                .firstName("Vlad")
                .lastName("Krivonos")
                .middleName("Igorevich")
                .email("vlad58r@gmail.com")
                .birthDate(LocalDate.of(2004, 12, 22))
                .passportSeries("1234")
                .passportNumber("1234")
                .build();
    }

}
