package com.vlad.project.counter;

import com.vlad.project.dto.ScoringDataDto;
import com.vlad.project.exception.PreScoringException;
import com.vlad.project.exception.ScoringException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

import static com.vlad.project.counter.MonthlyPaymentCounter.*;
import static com.vlad.project.utils.LoanApplicationRequestUtil.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
public class MonthlyPaymentCounterTest {

    @Autowired
    private CurrentRateCounterImpl rate;

    @Test
    void correctAmountWithCorrectData() {
        ScoringDataDto dto = getCorrectScoringDataDto();
        BigDecimal totalCreditAmount = monthlyPaymentCounter(
                rate.validRate(dto), dto.getTerm(), dto.getAmount(), dto.getIsInsuranceEnabled()
        );

        assertEquals(new BigDecimal(1_815), totalCreditAmount);
    }

    @Test
    void notCorrectAmountWithCorrectData() {
        ScoringDataDto dto = getNotCorrectAmountWithCorrectScoringData();
        PreScoringException exception = assertThrows(PreScoringException.class, () ->
                monthlyPaymentCounter(
                        rate.validRate(dto), dto.getTerm(), dto.getAmount(), dto.getIsInsuranceEnabled())
        );

        assertEquals("Сумма кредита должна быть более 10.000 рублей", exception.getMessage());
    }

    @Test
    void correctAmountWithoutCorrectData() {
        ScoringDataDto dto = getCorrectScoringDataDtoCancel();
        ScoringException exception = assertThrows(ScoringException.class, () ->
                monthlyPaymentCounter(
                        rate.validRate(dto), dto.getTerm(), dto.getAmount(), dto.getIsInsuranceEnabled())
        );

        assertEquals("Заемщик должен быть трудоустроен", exception.getMessage());
    }

    @Test
    void correctAmountWithCorrectDataPlusOnePercent() {
        ScoringDataDto dto = getCorrectScoringDataDtoPlusOne();
        BigDecimal totalCreditAmount = monthlyPaymentCounter(
                rate.validRate(dto), dto.getTerm(), dto.getAmount(), dto.getIsInsuranceEnabled()
        );

        assertEquals(new BigDecimal(1_824), totalCreditAmount);
    }

    @Test
    void correctAmountWithCorrectDataPlusThreePercent() {
        ScoringDataDto dto = getCorrectScoringDataDtoPlusThree();
        BigDecimal totalCreditAmount = monthlyPaymentCounter(
                rate.validRate(dto), dto.getTerm(), dto.getAmount(), dto.getIsInsuranceEnabled()
        );
        assertEquals(new BigDecimal(1_843), totalCreditAmount);
    }

    @Test
    void correctAmountWithCorrectDataMinusTwoPercent() {
        ScoringDataDto dto = getCorrectScoringDataDtoMinusTwo();
        BigDecimal totalCreditAmount = monthlyPaymentCounter(
                rate.validRate(dto), dto.getTerm(), dto.getAmount(), dto.getIsInsuranceEnabled()
        );

        assertEquals(new BigDecimal(1_796), totalCreditAmount);
    }

    @Test
    void correctAmountWithCorrectDataMinusFourPercent() {
        ScoringDataDto dto = getCorrectScoringDataDtoMinusFour();
        BigDecimal totalCreditAmount = monthlyPaymentCounter(
                rate.validRate(dto), dto.getTerm(), dto.getAmount(), dto.getIsInsuranceEnabled()
        );

        assertEquals(new BigDecimal(1_777), totalCreditAmount);
    }

    @Test
    void correctAmountWithCorrectDataMinusSixPercent() {
        ScoringDataDto dto = getCorrectScoringDataDtoMinusSix();
        BigDecimal totalCreditAmount = monthlyPaymentCounter(
                rate.validRate(dto), dto.getTerm(), dto.getAmount(), dto.getIsInsuranceEnabled()
        );

        assertEquals(new BigDecimal(1_758), totalCreditAmount);
    }

    @Test
    void correctAmountWithCorrectDataMinusThreePlusThreePercent() {
        ScoringDataDto dto = getCorrectScoringDataDtoMinusThreePlusThree();
        BigDecimal totalCreditAmount = monthlyPaymentCounter(
                rate.validRate(dto), dto.getTerm(), dto.getAmount(),dto.getIsInsuranceEnabled()
        );

        assertEquals(new BigDecimal(1_815), totalCreditAmount);
    }

    @Test
    void correctAmountWithCorrectDataInsurancePercent() {
        ScoringDataDto dto = getCorrectScoringDataDtoInsurance();
        BigDecimal totalCreditAmount = monthlyPaymentCounter(
                rate.validRate(dto), dto.getTerm(), dto.getAmount(), dto.getIsInsuranceEnabled()
        );

        assertEquals(new BigDecimal(2_001), totalCreditAmount);
    }

    @Test
    void correctAmountWithCorrectDataSalaryClientPercent() {
        ScoringDataDto dto = getCorrectScoringDataDtoSalaryClient();
        BigDecimal totalCreditAmount = monthlyPaymentCounter(
                rate.validRate(dto), dto.getTerm(), dto.getAmount(), dto.getIsInsuranceEnabled()
        );

        assertEquals(new BigDecimal(1_805), totalCreditAmount);
    }

    @Test
    void chekWithFourteenPercentRate() {
        ScoringDataDto dto = getCorrectScoringDataDtoMinusTwo();
        BigDecimal totalCreditAmount = monthlyPaymentCounter(rate.validRate(dto), 12,
                new BigDecimal("200000"), true);

        assertEquals(new BigDecimal("20112"), totalCreditAmount);
    }

    @Test
    void chekWithFourteenPercentRateAndInsuranceEnabled() {
        ScoringDataDto dto = getCorrectScoringDataDtoMinusTwo();
        BigDecimal totalCreditAmount = monthlyPaymentCounter(rate.validRate(dto), 12,
                new BigDecimal("241344"), true);

        assertEquals(new BigDecimal("24270"), totalCreditAmount);
    }

    @Test
    void chekWithFourteenPercentRateAndInsuranceNotEnabled() {
        ScoringDataDto dto = getCorrectScoringDataDtoMinusTwo();
        BigDecimal totalCreditAmount = monthlyPaymentCounter(rate.validRate(dto), 12,
                new BigDecimal("241344"), false);

        assertEquals(new BigDecimal("21670"), totalCreditAmount);
    }

}
