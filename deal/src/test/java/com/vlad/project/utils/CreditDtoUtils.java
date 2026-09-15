package com.vlad.project.utils;

import com.vlad.project.dto.CreditDto;

import java.util.List;

import static java.math.BigDecimal.valueOf;

public class CreditDtoUtils {

    public static CreditDto getCorrectCreditDto() {
        return new CreditDto(
                valueOf(400000),
                24,
                valueOf(12000),
                valueOf(13),
                valueOf(3400),
                false,
                true,
                List.of()
                );
    }
}
