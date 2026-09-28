package com.vlad.project.service;

import com.vlad.project.client.HttpDealClient;
import com.vlad.project.dto.LoanApplicationRequestDto;
import com.vlad.project.dto.LoanOfferDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ApplicationServiceImpl implements ApplicationService{

    private final HttpDealClient applicationToDeal;

    @Override
    public List<LoanOfferDto> getApplicationFromMsConveyor(LoanApplicationRequestDto dto) {
        return applicationToDeal.getApplication(dto);
    }
}
