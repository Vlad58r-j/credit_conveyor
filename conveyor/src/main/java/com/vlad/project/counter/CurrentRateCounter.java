package com.vlad.project.counter;

import com.vlad.project.dto.ScoringDataDto;

import java.math.BigDecimal;

public interface CurrentRateCounter {

    BigDecimal validRate(ScoringDataDto employee);
}
