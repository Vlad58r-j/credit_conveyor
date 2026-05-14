package com.vlad.project.service;

import com.vlad.project.counter.CurrentRateCounter;
import com.vlad.project.dto.CreditDto;
import com.vlad.project.dto.PaymentScheduleElement;
import com.vlad.project.dto.ScoringDataDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

import static com.vlad.project.counter.MonthlyPaymentCounter.*;
import static com.vlad.project.counter.PaymentScheduleCounter.*;
import static com.vlad.project.counter.TotalAmountCounter.*;

@Service
@RequiredArgsConstructor
public class CreditServiceImpl implements CreditService {

    @Autowired
    public CurrentRateCounter rateCounter;

    @Override
    public CreditDto calculationCreditPayments(ScoringDataDto scoringDataDto) {
        BigDecimal correctRate = rateCounter.validRate(scoringDataDto);
        BigDecimal amount = scoringDataDto.getAmount();
        Integer term = scoringDataDto.getTerm();
        Boolean isInsuranceEnabled = scoringDataDto.getIsInsuranceEnabled();
        Boolean isSalaryClient = scoringDataDto.getIsSalaryClient();

        BigDecimal monthlyPayment = monthlyPaymentCounter(correctRate, term, amount, isInsuranceEnabled);
        BigDecimal totalCreditAmount = totalCreditAmount(amount, correctRate, term, isInsuranceEnabled);
        List<PaymentScheduleElement> paymentScheduleElements =
                paymentSchedule(correctRate, amount, monthlyPayment, term);

        return new CreditDto(amount, term, monthlyPayment, correctRate, totalCreditAmount, isInsuranceEnabled,
                isSalaryClient, paymentScheduleElements);
    }
}
