package com.vlad.project.utils;

import com.vlad.project.dto.LoanOfferDto;

import static java.math.BigDecimal.*;

public class LoanOfferDtoUtil {

    public static LoanOfferDto getCorrectLoanOfferDto() {
        return new LoanOfferDto(1L, valueOf(25000), valueOf(20000), 24, valueOf(1200),
                valueOf(15),true,false);
    }


}
