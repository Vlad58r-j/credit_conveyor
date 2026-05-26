package com.vlad.project.service;

import com.vlad.project.config.RateProperties;
import com.vlad.project.counter.MonthlyPaymentCounter;
import com.vlad.project.dto.LoanApplicationRequestDto;
import com.vlad.project.dto.LoanOfferDto;
import com.vlad.project.exception.PreScoringException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import static java.math.BigDecimal.*;

@Slf4j
@Service
@RequiredArgsConstructor
public class LoanApplicationRequestServiceImpl implements LoanApplicationRequestService {

    public static final int MIN_CREDIT_AMOUNT = 10_000;
    public static final int MIN_CREDIT_TERM = 6;
    public static final BigDecimal MINUS_RATE_FOR_INSURANCE_CLIENT = valueOf(3);
    public static final BigDecimal MINUS_RATE_FOR_SALARY_CLIENT = valueOf(1);
    public static Long ID_OFFER = 1L;
    private final RateProperties rateConfiguration;

    @Override
    public List<LoanOfferDto> generateCreditOffers(LoanApplicationRequestDto loan) {
        loanDtoValidation(loan);
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
                .filter(term -> term.compareTo(MIN_CREDIT_TERM) > -1)
                .orElseThrow(() -> new PreScoringException("Минимальный срок кредита"));

        Optional.ofNullable(loan.getAmount())
                .filter(amount -> amount.compareTo(new BigDecimal(MIN_CREDIT_AMOUNT)) > -1)
                .orElseThrow(() -> new PreScoringException("Некорректная сумма кредита"));
    }

    public BigDecimal monthlyPaymentCounter(BigDecimal amount, Integer term,
                                            BigDecimal rate, Boolean isInsurance,
                                            Boolean salaryClient) {

        BigDecimal currentRate = getRate(rate, isInsurance, salaryClient);
        return MonthlyPaymentCounter.monthlyPaymentCounter(currentRate, term, amount, isInsurance);
    }

    public BigDecimal amountCounter(BigDecimal amount, Integer term, BigDecimal rate, Boolean isInsurance,
                                    Boolean salaryClient) {
        return monthlyPaymentCounter(amount, term, rate, isInsurance, salaryClient).multiply(valueOf(term));
    }

    private Long generateApplicationId() {
        return ID_OFFER++;
    }

    private BigDecimal getRate(BigDecimal currentRate, Boolean isInsurance, Boolean salaryClient) {
        if (isInsurance) {
            currentRate = currentRate.subtract(LoanApplicationRequestServiceImpl.MINUS_RATE_FOR_INSURANCE_CLIENT);
            log.info("При наличии страховки ставка уменьшается на 3% и становится = {}", currentRate);
        }
        if (salaryClient) {
            currentRate = currentRate.subtract(MINUS_RATE_FOR_SALARY_CLIENT);
            log.info("У зарплатных клиентов ставка уменьшается на 1% и становится = {}", currentRate);
        }

        return currentRate;
    }

}
