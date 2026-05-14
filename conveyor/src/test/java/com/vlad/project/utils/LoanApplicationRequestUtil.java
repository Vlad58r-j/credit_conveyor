package com.vlad.project.utils;

import com.vlad.project.dto.EmploymentDto;
import com.vlad.project.dto.LoanApplicationRequestDto;
import com.vlad.project.dto.ScoringDataDto;

import java.time.LocalDate;

import static com.vlad.project.dto.enumStatus.EmploymentStatus.*;
import static com.vlad.project.dto.enumStatus.Gender.*;
import static com.vlad.project.dto.enumStatus.MaritalStatus.*;
import static com.vlad.project.dto.enumStatus.Position.*;
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


    public static ScoringDataDto getCorrectScoringDataDto() {
        return new ScoringDataDto(valueOf(20000), 12, "Vlad", "Krivonos", MALE,
                LocalDate.of(2004, 12, 22), "1234", "123456",
                LocalDate.of(2025, 1, 25), "Penza", SINGLE,
                0, getCorrectEmploymentDto(), "vlad58r",
                false, false);
    }

    public static EmploymentDto getCorrectEmploymentDto() {
        return new EmploymentDto(EMPLOYED, "1234567890", valueOf(80_000), WORKER,
                14, 5);
    }

    public static ScoringDataDto getCorrectScoringDataDtoCancel() {
        return new ScoringDataDto(valueOf(20000), 12, "Vlad", "Krivonos", MALE,
                LocalDate.of(2004, 12, 22), "1234", "123456",
                LocalDate.of(2025, 1, 25), "Penza", SINGLE,
                0, getCorrectEmploymentDtoCancel(), "vlad58r",
                false, false);
    }

    public static EmploymentDto getCorrectEmploymentDtoCancel() {
        return new EmploymentDto(UNEMPLOYED, "1234567890", valueOf(80_000), null,
                14, 5);
    }

    public static ScoringDataDto getCorrectScoringDataDtoCancelVersionTwo() {
        return new ScoringDataDto(valueOf(20000000), 12, "Vlad", "Krivonos", MALE,
                LocalDate.of(2004, 12, 22), "1234", "123456",
                LocalDate.of(2025, 1, 25), "Penza", SINGLE,
                0, getCorrectEmploymentDtoCancelVersionTwo(), "vlad58r",
                false, false);
    }

    public static EmploymentDto getCorrectEmploymentDtoCancelVersionTwo() {
        return new EmploymentDto(EMPLOYED, "1234567890", valueOf(80_000), WORKER,
                14, 5);
    }

    public static ScoringDataDto getNotCorrectAmountWithCorrectScoringData() {
        return new ScoringDataDto(valueOf(2000), 12, "Vlad", "Krivonos", MALE,
                LocalDate.of(2004, 12, 22), "1234", "123456",
                LocalDate.of(2025, 1, 25), "Penza", SINGLE,
                0, getNotCorrectEmploymentDtoAmount(), "vlad58r",
                false, false);
    }

    public static EmploymentDto getNotCorrectEmploymentDtoAmount() {
        return new EmploymentDto(EMPLOYED, "1234567890", valueOf(80_000), WORKER,
                14, 5);
    }

    public static ScoringDataDto getCorrectScoringDataDtoCancelAmount() {
        return new ScoringDataDto(valueOf(200), 12, "Vlad", "Krivonos", MALE,
                LocalDate.of(2004, 12, 22), "1234", "123456",
                LocalDate.of(2025, 1, 25), "Penza", SINGLE,
                0, getCorrectEmploymentDtoCancelAmount(), "vlad58r",
                false, false);
    }

    public static EmploymentDto getCorrectEmploymentDtoCancelAmount() {
        return new EmploymentDto(EMPLOYED, "1234567890", valueOf(80_000), WORKER,
                14, 5);
    }

    public static ScoringDataDto getCorrectScoringDataDtoCancelTerm() {
        return new ScoringDataDto(valueOf(20000), 5, "Vlad", "Krivonos", MALE,
                LocalDate.of(2004, 12, 22), "1234", "123456",
                LocalDate.of(2025, 1, 25), "Penza", SINGLE,
                0, getCorrectEmploymentDtoCancelTerm(), "vlad58r",
                false, false);
    }

    public static EmploymentDto getCorrectEmploymentDtoCancelTerm() {
        return new EmploymentDto(EMPLOYED, "1234567890", valueOf(80_000), WORKER,
                14, 5);
    }

    public static ScoringDataDto getCorrectScoringDataDtoPlusOne() {
        return new ScoringDataDto(valueOf(20000), 12, "Vlad", "Krivonos", MALE,
                LocalDate.of(2004, 12, 22), "1234", "123456",
                LocalDate.of(2025, 1, 25), "Penza", SINGLE,
                0, getCorrectEmploymentDtoPlusOne(), "vlad58r",
                false, false);
    }

    public static EmploymentDto getCorrectEmploymentDtoPlusOne() {
        return new EmploymentDto(SELF_EMPLOYED, "1234567890", valueOf(80_000), WORKER,
                14, 5);
    }

    public static ScoringDataDto getCorrectScoringDataDtoPlusThree() {
        return new ScoringDataDto(valueOf(20000), 12, "Vlad", "Krivonos", MALE,
                LocalDate.of(2004, 12, 22), "1234", "123456",
                LocalDate.of(2025, 1, 25), "Penza", SINGLE,
                0, getCorrectEmploymentDtoPlusThree(), "vlad58r",
                false, false);
    }

    public static EmploymentDto getCorrectEmploymentDtoPlusThree() {
        return new EmploymentDto(BUSINESS_OWNER, "1234567890", valueOf(80_000), WORKER,
                14, 5);
    }

    public static ScoringDataDto getCorrectScoringDataDtoMinusTwo() {
        return new ScoringDataDto(valueOf(20000), 12, "Vlad", "Krivonos", MALE,
                LocalDate.of(2004, 12, 22), "1234", "123456",
                LocalDate.of(2025, 1, 25), "Penza", SINGLE,
                0, getCorrectEmploymentDtoMinusTwo(), "vlad58r",
                false, false);
    }

    public static EmploymentDto getCorrectEmploymentDtoMinusTwo() {
        return new EmploymentDto(EMPLOYED, "1234567890", valueOf(80_000), MID_MANAGER,
                14, 5);
    }

    public static ScoringDataDto getCorrectScoringDataDtoMinusFour() {
        return new ScoringDataDto(valueOf(20000), 12, "Vlad", "Krivonos", MALE,
                LocalDate.of(2004, 12, 22), "1234", "123456",
                LocalDate.of(2025, 1, 25), "Penza", SINGLE,
                0, getCorrectEmploymentDtoMinusFour(), "vlad58r",
                false, false);
    }

    public static EmploymentDto getCorrectEmploymentDtoMinusFour() {
        return new EmploymentDto(EMPLOYED, "1234567890", valueOf(80_000), TOP_MANAGER,
                14, 5);
    }

    public static ScoringDataDto getCorrectScoringDataDtoMinusSix() {
        return new ScoringDataDto(valueOf(20000), 12, "Vlad", "Krivonos", MALE,
                LocalDate.of(1990, 12, 22), "1234", "123456",
                LocalDate.of(2025, 1, 25), "Penza", MARRIED,
                0, getCorrectEmploymentDtoMinusSix(), "vlad58r",
                false, false);
    }

    public static EmploymentDto getCorrectEmploymentDtoMinusSix() {
        return new EmploymentDto(EMPLOYED, "1234567890", valueOf(80_000), WORKER,
                14, 5);
    }

    public static ScoringDataDto getCorrectScoringDataDtoMinusThreePlusThree() {
        return new ScoringDataDto(valueOf(20000), 12, "Vlad", "Krivonos", NON_BINARY,
                LocalDate.of(2004, 12, 22), "1234", "123456",
                LocalDate.of(2025, 1, 25), "Penza", MARRIED,
                0, getCorrectEmploymentDtoMinusThreePlusThree(), "vlad58r",
                false, false);
    }

    public static EmploymentDto getCorrectEmploymentDtoMinusThreePlusThree() {
        return new EmploymentDto(EMPLOYED, "1234567890", valueOf(80_000), WORKER,
                14, 5);
    }

    public static ScoringDataDto getCorrectScoringDataDtoInsurance() {
        return new ScoringDataDto(valueOf(20000), 12, "Vlad", "Krivonos", MALE,
                LocalDate.of(2004, 12, 22), "1234", "123456",
                LocalDate.of(2025, 1, 25), "Penza", SINGLE,
                0, getCorrectEmploymentDtoInsurance(), "vlad58r",
                true, false);
    }

    public static EmploymentDto getCorrectEmploymentDtoInsurance() {
        return new EmploymentDto(EMPLOYED, "1234567890", valueOf(80_000), WORKER,
                14, 5);
    }

    public static ScoringDataDto getCorrectScoringDataDtoSalaryClient() {
        return new ScoringDataDto(valueOf(20000), 12, "Vlad", "Krivonos", MALE,
                LocalDate.of(2004, 12, 22), "1234", "123456",
                LocalDate.of(2025, 1, 25), "Penza", SINGLE,
                0, getCorrectEmploymentDtoSalaryClient(), "vlad58r",
                false, true);
    }

    public static EmploymentDto getCorrectEmploymentDtoSalaryClient() {
        return new EmploymentDto(EMPLOYED, "1234567890", valueOf(80_000), WORKER,
                14, 5);
    }

    public static ScoringDataDto getNotCorrectScoringDataDtoBirthday() {
        return new ScoringDataDto(valueOf(20000), 12, "Vlad", "Krivonos", MALE,
                LocalDate.of(2014, 12, 22), "1234", "123456",
                LocalDate.of(2025, 1, 25), "Penza", SINGLE,
                0, getNotCorrectEmploymentDtoBirthday(), "vlad58r",
                false, false);
    }

    public static EmploymentDto getNotCorrectEmploymentDtoBirthday() {
        return new EmploymentDto(EMPLOYED, "1234567890", valueOf(80_000), WORKER,
                14, 5);

    }
}
