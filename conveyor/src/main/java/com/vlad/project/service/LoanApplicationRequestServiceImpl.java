package com.vlad.project.service;

import com.vlad.project.config.RateConfiguration;
import com.vlad.project.dto.LoanApplicationRequestDto;
import com.vlad.project.dto.LoanOfferDto;
import com.vlad.project.filter.UserFilter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;

import static java.math.BigDecimal.*;

@Slf4j
@Service
@RequiredArgsConstructor
public class LoanApplicationRequestServiceImpl implements LoanApplicationRequestService {

    private final RateConfiguration rateConfiguration;
    private Long applicationId;

    @Override
    public List<LoanOfferDto> preScoring(LoanApplicationRequestDto loan) {
        log.info("Вызвался метод preScoring = {}", loan);
        new UserFilter().preScoring(loan);

        applicationId = 1L;
        var currentRate = rateConfiguration.rate();
        var userAmount = loan.getAmount();
        var userTerm = loan.getTerm();

        var firstOffer = new LoanOfferDto(generateApplicationId(), userAmount,
                amountCounter(userAmount, userTerm, currentRate, false, false), userTerm,
                monthlyPaymentCounter(userAmount, userTerm, currentRate, false, false),
                getRate(currentRate, false, false), false, false);

        var secondOffer = new LoanOfferDto(generateApplicationId(), userAmount,
                amountCounter(userAmount, userTerm, currentRate, false, true),
                userTerm, monthlyPaymentCounter(userAmount, userTerm, currentRate, false, true),
                getRate(currentRate, false, true), false, true);


        var thirdOffer = new LoanOfferDto(generateApplicationId(), userAmount,
                amountCounter(userAmount, userTerm, currentRate, true, false),
                userTerm, monthlyPaymentCounter(userAmount, userTerm, currentRate, true, false),
                getRate(currentRate, true, false), true, false);

        var fourthOffer = new LoanOfferDto(generateApplicationId(), userAmount,
                amountCounter(userAmount, userTerm, currentRate, true, true),
                userTerm, monthlyPaymentCounter(userAmount, userTerm, currentRate, true, true),
                getRate(currentRate, true, true), true, true);


        return Stream.of(firstOffer, secondOffer, thirdOffer, fourthOffer)
                .sorted(Comparator.comparing(LoanOfferDto::getTotalAmount).reversed()).toList();
    }

    private static BigDecimal monthlyPaymentCounter(BigDecimal amount, Integer term, BigDecimal rate, Boolean isInsurance, Boolean salaryClient) {
        BigDecimal newRate = rate;
        BigDecimal insurance = amount;

        if (isInsurance) {
            newRate = newRate.subtract(valueOf(3));
             insurance = amount.multiply(valueOf(0.15)).add(insurance);
        }
        if (salaryClient) {
            newRate = newRate.subtract(valueOf(1));
        }

        var divideResult = newRate.divide(valueOf(1200), 10, RoundingMode.HALF_UP);

        var powWithSum = valueOf(1).add(divideResult).pow(term);

        var denominator = valueOf(1).subtract(valueOf(1).divide(powWithSum, 10, RoundingMode.HALF_UP));
        var formula = divideResult.divide(denominator, 10, RoundingMode.HALF_UP);

        return insurance.multiply(formula);
    }

    private static BigDecimal amountCounter(BigDecimal amount, Integer term, BigDecimal rate, Boolean isInsurance, Boolean salaryClient) {

        return monthlyPaymentCounter(amount, term, rate, isInsurance, salaryClient).multiply(valueOf(term));
    }

    private Long generateApplicationId() {
        return applicationId++;
    }

    private static BigDecimal getRate(BigDecimal currentRate, Boolean isInsurance, Boolean salaryClient) {
        if (isInsurance) currentRate = currentRate.subtract(valueOf(3));
        if (salaryClient) currentRate = currentRate.subtract(valueOf(1));

        return currentRate;
    }

}
