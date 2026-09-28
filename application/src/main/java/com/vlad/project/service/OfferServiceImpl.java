package com.vlad.project.service;

import com.vlad.project.client.HttpDealClient;
import com.vlad.project.dto.LoanOfferDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OfferServiceImpl implements OfferService{

    private final HttpDealClient client;

    @Override
    public void getOffers(LoanOfferDto dto) {
        client.getOffers(dto);
    }
}
