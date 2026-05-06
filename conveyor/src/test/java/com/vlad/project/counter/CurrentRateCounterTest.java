package com.vlad.project.counter;

import com.vlad.project.exception.PreScoringException;
import com.vlad.project.exception.ScoringException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

import static com.vlad.project.utils.LoanApplicationRequestUtil.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class CurrentRateCounterTest {

    @Autowired
    private CurrentRateCounterImpl counter;

    @Test
    void checkCorrectCurrentRateOnlyWorker() {
        BigDecimal actualRate = counter.validRate(getCorrectScoringDataDto());

        assertEquals(new BigDecimal(16), actualRate);
    }

    @Test
    void checkCorrectCurrentRateOnlyWorkerButCancel() {
        ScoringException scoringException = assertThrows(
                ScoringException.class, () -> counter.validRate(getCorrectScoringDataDtoCancel()));

        assertEquals("Заемщик должен быть трудоустроен", scoringException.getMessage());
    }

    @Test
    void checkCorrectCurrentRateOnlyWorkerButCancelVersionTwo() {
        ScoringException scoringException = assertThrows(
                ScoringException.class, () -> counter.validRate(getCorrectScoringDataDtoCancelVersionTwo()));

        assertEquals("Сумма кредита должна быть меньше 20 зарплат", scoringException.getMessage());
    }

    @Test
    void checkCorrectCurrentRateOnlyWorkerButCancelAmount() {
        PreScoringException preScoringException = assertThrows(
                PreScoringException.class, () -> counter.validRate(getCorrectScoringDataDtoCancelAmount()));

        assertEquals("Сумма кредита должна быть более 10.000 рублей", preScoringException.getMessage());
    }

    @Test
    void checkCorrectCurrentRateOnlyWorkerButCancelTerm() {
        PreScoringException preScoringException = assertThrows(
                PreScoringException.class, () -> counter.validRate(getCorrectScoringDataDtoCancelTerm()));

        assertEquals("Минимальный срок кредита 6 месяцев", preScoringException.getMessage());
    }

    @Test
    void checkCorrectCurrentRatePlusOne() {
        BigDecimal actualRate = counter.validRate(getCorrectScoringDataDtoPlusOne());

        assertEquals(new BigDecimal(17), actualRate);
    }

    @Test
    void checkCorrectCurrentRatePlusThree() {
        BigDecimal actualRate = counter.validRate(getCorrectScoringDataDtoPlusThree());

        assertEquals(new BigDecimal(19), actualRate);
    }

    @Test
    void checkCorrectCurrentRateMinusTwo() {
        BigDecimal actualRate = counter.validRate(getCorrectScoringDataDtoMinusTwo());

        assertEquals(new BigDecimal(14), actualRate);
    }

    @Test
    void checkCorrectCurrentRateMinusFour() {
        BigDecimal actualRate = counter.validRate(getCorrectScoringDataDtoMinusFour());

        assertEquals(new BigDecimal(12), actualRate);
    }

    @Test
    void checkCorrectCurrentRateMinusSix() {
        BigDecimal actualRate = counter.validRate(getCorrectScoringDataDtoMinusSix());

        assertEquals(new BigDecimal(10), actualRate);
    }

    @Test
    void checkCorrectCurrentRateMinusThreePlusThree() {
        BigDecimal actualRate = counter.validRate(getCorrectScoringDataDtoMinusThreePlusThree());

        assertEquals(new BigDecimal(16), actualRate);
    }

    @Test
    void checkCorrectCurrentRateInsurance() {
        BigDecimal actualRate = counter.validRate(getCorrectScoringDataDtoInsurance());

        assertEquals(new BigDecimal(13), actualRate);
    }

    @Test
    void checkCorrectCurrentRateSalaryClient() {
        BigDecimal actualRate = counter.validRate(getCorrectScoringDataDtoSalaryClient());

        assertEquals(new BigDecimal(15), actualRate);
    }


}
