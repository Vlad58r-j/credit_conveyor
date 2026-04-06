package com.vlad.project.service;

import com.vlad.project.config.RateProperties;
import com.vlad.project.dto.LoanApplicationRequestDto;
import com.vlad.project.dto.LoanOfferDto;
import com.vlad.project.exception.PreScoringException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import static java.math.BigDecimal.*;

@Slf4j
@Service
@RequiredArgsConstructor
public class LoanApplicationRequestServiceImpl implements LoanApplicationRequestService {

    private final RateProperties rateConfiguration;
    private static Long applicationId;

    @Override
    public List<LoanOfferDto> generateCreditOffers(LoanApplicationRequestDto loan) {
        loanDtoValidation(loan);
        applicationId = 1L;
        var currentRate = rateConfiguration.rate();
        var userAmount = loan.getAmount();
        var userTerm = loan.getTerm();

        log.info("Генерируем 4 кредитных предложения");

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

    private void loanDtoValidation(LoanApplicationRequestDto loan) {
        Optional.ofNullable(loan)
                .orElseThrow(() -> new PreScoringException("LoanDto не может быть null"));

        Optional.ofNullable(loan.getTerm())
                .filter(term -> term.compareTo(6) > -1)
                .orElseThrow(() -> new PreScoringException("Минимальный срок кредита"));

        Optional.ofNullable(loan.getAmount())
                .filter(amount -> amount.compareTo(new BigDecimal(10_000)) > -1)
                .orElseThrow(() -> new PreScoringException("Некорректная сумма кредита"));
    }

    public BigDecimal monthlyPaymentCounter(BigDecimal amount, Integer term,
                                                   BigDecimal rate, Boolean isInsurance,
                                                   Boolean salaryClient) {
        BigDecimal newRate = rate;
        BigDecimal insurance = amount;

        if (isInsurance) {
            newRate = newRate.subtract(valueOf(3));

            var insurancePrice = amount.multiply(valueOf(0.01)).multiply(valueOf(term));
            insurance = insurancePrice.add(insurance);
            log.info("При наличии страховки ставка уменьшается на 3 и становится = {}," +
                     " сумма кредита увеличивается на 15% и становится = {}", newRate, insurance);
        }
        if (salaryClient) {
            newRate = newRate.subtract(ONE);
            log.info("У зарплатных клиентов ставка уменьшается на 1% и становится = {}", newRate);
        }

        var percent = newRate.divide(valueOf(100), 10, RoundingMode.HALF_UP)
                .divide(valueOf(12), 10, RoundingMode.HALF_UP);

        var onePlusPercent = ONE.add(percent);

        var pow = onePlusPercent.pow(term);
        var reversePow = ONE.divide(pow, 10, RoundingMode.HALF_UP);

        var oneMinusPow = ONE.subtract(reversePow);

        return insurance.multiply(percent)
                .divide(oneMinusPow, 0, RoundingMode.HALF_UP);
    }

    public BigDecimal amountCounter(BigDecimal amount, Integer term, BigDecimal rate, Boolean isInsurance, Boolean salaryClient) {
        return monthlyPaymentCounter(amount, term, rate, isInsurance, salaryClient).multiply(valueOf(term));
    }

    private Long generateApplicationId() {
        return applicationId++;
    }

    private BigDecimal getRate(BigDecimal currentRate, Boolean isInsurance, Boolean salaryClient) {
        if (isInsurance) currentRate = currentRate.subtract(valueOf(3));
        if (salaryClient) currentRate = currentRate.subtract(valueOf(1));

        return currentRate;
    }

}
