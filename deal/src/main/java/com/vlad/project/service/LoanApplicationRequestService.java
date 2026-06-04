package com.vlad.project.service;

import com.vlad.project.database.entity.Application;
import com.vlad.project.dto.LoanApplicationRequestDto;
import com.vlad.project.dto.LoanOfferDto;

import java.util.List;

public interface LoanApplicationRequestService{

    Application createClientAndApplication(LoanApplicationRequestDto dto);

    List<LoanOfferDto> getOffers(LoanApplicationRequestDto dto);

}
