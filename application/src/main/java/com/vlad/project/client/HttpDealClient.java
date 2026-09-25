package com.vlad.project.client;

import com.vlad.project.dto.LoanApplicationRequestDto;
import com.vlad.project.dto.LoanOfferDto;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.service.annotation.HttpExchange;
import org.springframework.web.service.annotation.PostExchange;
import org.springframework.web.service.annotation.PutExchange;

import java.util.List;

@HttpExchange("http://localhost:8081/deal")
public interface HttpDealClient {

    @PostExchange("/application")
    List<LoanOfferDto> getApplication(@RequestBody LoanApplicationRequestDto dto);

    @PutExchange("/offer")
    void getOffers(@RequestBody LoanOfferDto dto);
}
