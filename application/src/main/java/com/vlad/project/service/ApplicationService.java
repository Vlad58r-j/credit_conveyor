package com.vlad.project.service;

import com.vlad.project.dto.LoanApplicationRequestDto;
import com.vlad.project.dto.LoanOfferDto;

import java.util.List;

public interface ApplicationService {

    List<LoanOfferDto> getApplicationFromMsConveyor(LoanApplicationRequestDto dto);
}
