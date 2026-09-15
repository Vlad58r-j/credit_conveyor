package com.vlad.project.service;

import com.vlad.project.database.entity.Credit;
import com.vlad.project.dto.CreditDto;
import com.vlad.project.dto.FinishRegistrationRequestDTO;
import com.vlad.project.dto.ScoringDataDto;

public interface CreditService {

    void addDataToScoringDto(FinishRegistrationRequestDTO dto, Long applicationId);

    CreditDto redirectToConveyerMvc(ScoringDataDto dto);

    Credit saveCredit(CreditDto dto);
}
