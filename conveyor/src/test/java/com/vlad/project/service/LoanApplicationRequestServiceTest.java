package com.vlad.project.service;

import com.vlad.project.dto.LoanOfferDto;
import com.vlad.project.exception.PreScoringException;
import com.vlad.project.utils.LoanApplicationRequestUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class LoanApplicationRequestServiceTest {

    @Autowired
    private LoanApplicationRequestServiceImpl loanApplicationRequestService;

    @Test
    void generateFourCreditOffersIfValidationCorrect() {
        List<LoanOfferDto> result = loanApplicationRequestService.generateCreditOffers(
                LoanApplicationRequestUtil.getCorrectLoanApplicationRequestDto());

        assertEquals(4, result.size());
    }

    @Test
    void exceptionIfNotCorrectAmountInDto() {
        PreScoringException exception = assertThrows(PreScoringException.class, () ->
                loanApplicationRequestService.generateCreditOffers(
                        LoanApplicationRequestUtil.getNotCorrectAmountLoanApplicationRequestDto())
        );

        assertEquals("Некорректная сумма кредита", exception.getMessage());
    }


    @Test
    void exceptionIfNotCorrectTermIsNull() {
        assertThrows(PreScoringException.class, () ->
                loanApplicationRequestService.generateCreditOffers(
                        LoanApplicationRequestUtil.getNotCorrectTermLoanApplicationRequestDto())

        );
    }

    @Test
    void exceptionIfDtoNotPresent() {
        var ex = assertThrows(PreScoringException.class, () ->
                loanApplicationRequestService.generateCreditOffers(null)
        );

        assertEquals("LoanDto не может быть null", ex.getMessage());
    }

    @Test
    void monthlyPaymentCheckNotSalaryClientAndNotHaveInsurance() {
        BigDecimal monthlyPaymentCounter = loanApplicationRequestService.monthlyPaymentCounter(
                new BigDecimal(200_000), 12, new BigDecimal(16), false, false);

        assertEquals(new BigDecimal(18_146), monthlyPaymentCounter);
    }

    @Test
    void monthlyPaymentCheckNotSalaryClientAndHaveInsurance() {
        BigDecimal monthlyPaymentCounter = loanApplicationRequestService.monthlyPaymentCounter(
                new BigDecimal(200_000), 12, new BigDecimal(16), true, false);

        assertEquals(new BigDecimal(20_007), monthlyPaymentCounter.setScale(0, RoundingMode.HALF_UP));
    }

    @Test
    void monthlyPaymentCheckSalaryClientAndNotHaveInsurance() {
        BigDecimal monthlyPaymentCounter = loanApplicationRequestService.monthlyPaymentCounter(
                new BigDecimal(200_000), 12, new BigDecimal(16), false, true);

        assertEquals(new BigDecimal(18_052), monthlyPaymentCounter.setScale(0, RoundingMode.HALF_UP));
    }

    @Test
    void monthlyPaymentCheckSalaryClientAndHaveInsurance() {
        BigDecimal monthlyPaymentCounter = loanApplicationRequestService.monthlyPaymentCounter(
                new BigDecimal(200_000), 12, new BigDecimal(16), true, true);

        assertEquals(new BigDecimal(19_902), monthlyPaymentCounter.setScale(0, RoundingMode.HALF_UP));
    }

    @Test
    void totalAmountCheckNotSalaryClientAndNotHaveInsurance() {
        BigDecimal monthlyPaymentCounter = loanApplicationRequestService.amountCounter(
                new BigDecimal(200_000), 12, new BigDecimal(16), false, false);

        assertEquals(new BigDecimal(217_752), monthlyPaymentCounter.setScale(0, RoundingMode.HALF_UP));
    }

    @Test
    void totalAmountCheckSalaryClientAndNotHaveInsurance() {
        BigDecimal monthlyPaymentCounter = loanApplicationRequestService.amountCounter(
                new BigDecimal(200_000), 12, new BigDecimal(16), false, true);

        assertEquals(new BigDecimal(216_624), monthlyPaymentCounter.setScale(0, RoundingMode.HALF_UP));
    }

    @Test
    void totalAmountCheckNotSalaryClientAndHaveInsurance() {
        BigDecimal monthlyPaymentCounter = loanApplicationRequestService.amountCounter(
                new BigDecimal(200_000), 12, new BigDecimal(16), true, false);

        assertEquals(new BigDecimal(240_084), monthlyPaymentCounter.setScale(0, RoundingMode.HALF_UP));
    }

    @Test
    void totalAmountCheckSalaryClientAndHaveInsurance() {
        BigDecimal monthlyPaymentCounter = loanApplicationRequestService.amountCounter(
                new BigDecimal(200_000), 12, new BigDecimal(16), true, true);

        assertEquals(new BigDecimal(238_824), monthlyPaymentCounter.setScale(0, RoundingMode.HALF_UP));
    }


}
