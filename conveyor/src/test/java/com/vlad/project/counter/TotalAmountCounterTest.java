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
    private CurrentRateCounter rate;

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


}
