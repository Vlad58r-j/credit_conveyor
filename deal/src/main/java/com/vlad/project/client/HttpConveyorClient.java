package com.vlad.project.client;

import com.vlad.project.dto.LoanApplicationRequestDto;
import com.vlad.project.dto.LoanOfferDto;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.service.annotation.HttpExchange;
import org.springframework.web.service.annotation.PostExchange;

import java.util.List;

@HttpExchange("http://localhost:8080")
public interface HttpConveyorClient {

    @PostExchange("conveyor/offers")
    List<LoanOfferDto> getOffers(@RequestBody LoanApplicationRequestDto dto);
}
