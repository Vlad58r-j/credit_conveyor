package com.vlad.project.service;

import com.vlad.project.config.RateProperties;
import com.vlad.project.dto.LoanApplicationRequestDto;
import com.vlad.project.dto.LoanOfferDto;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

import static java.math.BigDecimal.*;

@Slf4j
@Service
@RequiredArgsConstructor
public class LoanApplicationRequestServiceImpl implements LoanApplicationRequestService {

    private final RateProperties rateConfiguration;
    private static Long applicationId;

    @Override
    public List<LoanOfferDto> generateCreditOffers(LoanApplicationRequestDto loan) {
        applicationId = 1L;
        var currentRate = rateConfiguration.rate();
        var userAmount = loan.getAmount();
        var userTerm = loan.getTerm();

        log.info("Генерируем 4 кредитных предложений");

        return List.of(
                new LoanOfferDto(generateApplicationId(), userAmount,
                        amountCounter(userAmount, userTerm, currentRate, false, false),
                        userTerm, monthlyPaymentCounter(userAmount, userTerm, currentRate, false, false),
                        currentRate, false, false),

                new LoanOfferDto(generateApplicationId(), userAmount,
                        amountCounter(userAmount, userTerm, currentRate, false, true),
                        userTerm, monthlyPaymentCounter(userAmount, userTerm, currentRate, false, true),
                        currentRate, false, true),

                new LoanOfferDto(generateApplicationId(), userAmount,
                        amountCounter(userAmount, userTerm, currentRate, true, false),
                        userTerm, monthlyPaymentCounter(userAmount, userTerm, currentRate, true, false),
                        currentRate, true, false),

                new LoanOfferDto(generateApplicationId(), userAmount,
                        amountCounter(userAmount, userTerm, currentRate, true, true),
                        userTerm, monthlyPaymentCounter(userAmount, userTerm, currentRate, true, true),
                        currentRate, true, true)

        );
    }

    private static BigDecimal monthlyPaymentCounter(BigDecimal amount, Integer term, BigDecimal rate, Boolean isInsurance, Boolean salaryClient) {
        BigDecimal newRate = rate;
        BigDecimal insurance = amount.multiply(valueOf(0.15));

        if (isInsurance) {
            newRate = rate.subtract(valueOf(3));
            insurance = amount.add(insurance);
        }
        if (salaryClient) {
            newRate = rate.subtract(valueOf(1));
        }

        var divideResult = newRate.divide(valueOf(12), 3, RoundingMode.HALF_UP);

        var denominator = 1 - Math.pow((1.0 + divideResult.doubleValue()), -term.doubleValue());

        var formula = divideResult.divide(valueOf(denominator), 3, RoundingMode.HALF_UP);

        return insurance.multiply(formula);
    }

    private static BigDecimal amountCounter(BigDecimal amount, Integer term, BigDecimal rate, Boolean isInsurance, Boolean salaryClient) {
        log.info("Считаем общую сумму для applicationId = {} выплат при amount = {}, term = {}, rate = {}, insurance = {}, salaryClient = {}",
                applicationId - 1, amount, term, rate, isInsurance, salaryClient);
        return monthlyPaymentCounter(amount, term, rate, isInsurance, salaryClient).multiply(valueOf(term));
    }

    private Long generateApplicationId() {
        return applicationId++;
    }

}
