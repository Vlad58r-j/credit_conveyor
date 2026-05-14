package com.vlad.project.filter;

import com.vlad.project.dto.LoanApplicationRequestDto;
import com.vlad.project.filter.exception.PreScoringException;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Period;
import java.util.Optional;

import static java.math.BigDecimal.*;


public class UserFilter {

    public void preScoring(LoanApplicationRequestDto loan) {
        checkedBirthday(loan.getBirthdate());
        checkedAmount(loan.getAmount());
        checkedTerm(loan.getTerm());
        checkedName(loan.getFirstName(), loan.getLastName(), Optional.of(loan.getMiddleName()));
        checkedPassport(loan.getPassportSeries(), loan.getPassportNumber());
        checkedEmail(loan.getEmail());
    }

    private void checkedPassport(String passportSeries, String passportNumber) {
        if (passportSeries.length() != 4 || passportNumber.length() != 6) {
            throw new PreScoringException("Некорректные паспортные данные ");
        }

    }

    private void checkedEmail(String email) {
        var result = email.matches("[\\w.]{2,50}@[\\w.]{2,20}");
        if (!result)
            throw new PreScoringException("Некорректный email адрес");

    }

    private void checkedBirthday(LocalDate birthdate) {
        var nowDate = LocalDate.now();
        var period = Period.between(nowDate, birthdate);
        var age = Math.abs(period.getYears() - period.getDays());
        if (age < 19) {
            throw new PreScoringException("Кредит не выдается несовершеннолетним");
        }
    }

    private void checkedTerm(Integer term) {
        if (term < 6) {
            throw new PreScoringException("Сумма кредита должна быть более 9.999 рублей");
        }

    }

    private void checkedAmount(BigDecimal amount) {
        if (amount.compareTo(valueOf(10_000.0)) < 0) {
            throw new PreScoringException("Сумма кредита должна быть более 9.999 рублей");
        }
    }

    private void checkedName(String firstName, String lastName, Optional<String> middleName) {
        var firstNameLength = firstName.length() < 2 || firstName.length() > 30;
        var lastNameLength = lastName.length() < 2 || lastName.length() > 30;
        if (firstNameLength || lastNameLength) {
            throw new PreScoringException("Некорректные ФИО");
        }

        if (middleName.isPresent()) {
            var middleNameLength = middleName.get().length();
            if (middleNameLength < 2 || middleNameLength > 30) {
                throw new PreScoringException("Некорректные ФИО");
            }
        }
    }

}