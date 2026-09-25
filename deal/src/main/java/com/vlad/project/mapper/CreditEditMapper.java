package com.vlad.project.mapper;

import com.vlad.project.database.entity.Credit;
import com.vlad.project.dto.CreditDto;
import com.vlad.project.dto.enumStatus.CreditStatus;
import org.springframework.stereotype.Component;

@Component
public class CreditEditMapper implements Mapper<CreditDto, Credit> {


    @Override
    public Credit map(CreditDto dto) {
        Credit credit = new Credit();
        copy(dto, credit);

        return credit;
    }

    private void copy(CreditDto dto, Credit credit) {
        credit.setAmount(dto.getAmount());
        credit.setTerm(dto.getTerm());
        credit.setMonthlyPayment(dto.getMonthlyPayment());
        credit.setRate(dto.getRate());
        credit.setPsk(dto.getPsk());
        credit.setPaymentSchedule(dto.getPaymentSchedule());
        credit.setIsInsuranceEnabled(dto.getIsInsuranceEnabled());
        credit.setIsSalaryClient(dto.getIsSalaryClient());
        credit.setCreditStatus(CreditStatus.CALCULATED);
    }


}
