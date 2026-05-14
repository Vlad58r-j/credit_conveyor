package com.vlad.project.service;

import com.vlad.project.dto.CreditDto;
import com.vlad.project.dto.ScoringDataDto;

public interface CreditService {

    CreditDto calculationCreditPayments(ScoringDataDto scoringDataDto);

}
