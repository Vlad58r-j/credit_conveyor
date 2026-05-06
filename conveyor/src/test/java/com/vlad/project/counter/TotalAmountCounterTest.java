package com.vlad.project.counter;

import com.vlad.project.dto.ScoringDataDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

import static com.vlad.project.counter.TotalAmountCounter.*;
import static com.vlad.project.utils.LoanApplicationRequestUtil.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class TotalAmountCounterTest {

    @Autowired
    private CurrentRateCounterImpl rate;

    @Test
    void correctAmountWithCorrectData() {
        ScoringDataDto dto = getCorrectScoringDataDto();
        BigDecimal totalCreditAmount = totalCreditAmount(dto.getAmount(), rate.validRate(dto), dto.getTerm(),
                dto.getIsInsuranceEnabled());

        assertEquals(new BigDecimal(21_780), totalCreditAmount);
    }

    @Test
    void correctAmountWithCorrectDataPlusOnePercent() {
        ScoringDataDto dto = getCorrectScoringDataDtoPlusOne();
        BigDecimal totalCreditAmount = totalCreditAmount(dto.getAmount(), rate.validRate(dto), dto.getTerm(),
                dto.getIsInsuranceEnabled());

        assertEquals(new BigDecimal(21_888), totalCreditAmount);
    }

    @Test
    void correctAmountWithCorrectDataPlusThreePercent() {
        ScoringDataDto dto = getCorrectScoringDataDtoPlusThree();
        BigDecimal totalCreditAmount = totalCreditAmount(dto.getAmount(), rate.validRate(dto), dto.getTerm(),
                dto.getIsInsuranceEnabled());

        assertEquals(new BigDecimal(22_116), totalCreditAmount);
    }

    @Test
    void correctAmountWithCorrectDataMinusTwoPercent() {
        ScoringDataDto dto = getCorrectScoringDataDtoMinusTwo();
        BigDecimal totalCreditAmount = totalCreditAmount(dto.getAmount(), rate.validRate(dto), dto.getTerm(),
                dto.getIsInsuranceEnabled());

        assertEquals(new BigDecimal(21_552), totalCreditAmount);
    }

    @Test
    void correctAmountWithCorrectDataMinusFourPercent() {
        ScoringDataDto dto = getCorrectScoringDataDtoMinusFour();
        BigDecimal totalCreditAmount = totalCreditAmount(dto.getAmount(), rate.validRate(dto), dto.getTerm(),
                dto.getIsInsuranceEnabled());

        assertEquals(new BigDecimal(21_324), totalCreditAmount);
    }

    @Test
    void correctAmountWithCorrectDataMinusSixPercent() {
        ScoringDataDto dto = getCorrectScoringDataDtoMinusSix();
        BigDecimal totalCreditAmount = totalCreditAmount(dto.getAmount(), rate.validRate(dto), dto.getTerm(),
                dto.getIsInsuranceEnabled());

        assertEquals(new BigDecimal(21_096), totalCreditAmount);
    }

    @Test
    void correctAmountWithCorrectDataMinusThreePlusThreePercent() {
        ScoringDataDto dto = getCorrectScoringDataDtoMinusThreePlusThree();
        BigDecimal totalCreditAmount = totalCreditAmount(dto.getAmount(), rate.validRate(dto), dto.getTerm(),
                dto.getIsInsuranceEnabled());

        assertEquals(new BigDecimal(21_780), totalCreditAmount);
    }

    @Test
    void correctAmountWithCorrectDataInsurancePercent() {
        ScoringDataDto dto = getCorrectScoringDataDtoInsurance();
        BigDecimal totalCreditAmount = totalCreditAmount(dto.getAmount(), rate.validRate(dto), dto.getTerm(),
                dto.getIsInsuranceEnabled());

        assertEquals(new BigDecimal(24_012), totalCreditAmount);
    }

    @Test
    void correctAmountWithCorrectDataSalaryClientPercent() {
        ScoringDataDto dto = getCorrectScoringDataDtoSalaryClient();
        BigDecimal totalCreditAmount = totalCreditAmount(dto.getAmount(), rate.validRate(dto), dto.getTerm(),
                dto.getIsInsuranceEnabled());

        assertEquals(new BigDecimal(21_660), totalCreditAmount);
    }

    @Test
    void chekWithFourteenPercentRate() {
        ScoringDataDto dto = getCorrectScoringDataDtoMinusTwo();
        BigDecimal totalCreditAmount = totalCreditAmount(new BigDecimal("200000"), rate.validRate(dto), 12,
                true);

        assertEquals(new BigDecimal("241344"), totalCreditAmount);
    }

    @Test
    void chekWithFourteenPercentRateAndInsuranceEnabled() {
        ScoringDataDto dto = getCorrectScoringDataDtoMinusTwo();
        BigDecimal totalCreditAmount = totalCreditAmount(new BigDecimal("241344"), rate.validRate(dto), 12,
                true);

        assertEquals(new BigDecimal("291240"), totalCreditAmount);
    }

    @Test
    void chekWithFourteenPercentRateAndInsuranceNotEnabled() {
        ScoringDataDto dto = getCorrectScoringDataDtoMinusTwo();
        BigDecimal totalCreditAmount = totalCreditAmount(new BigDecimal("241344"), rate.validRate(dto), 12,
                false);

        assertEquals(new BigDecimal("260040"), totalCreditAmount);
    }


}
