package com.vlad.project.controller;

import com.vlad.project.dto.LoanApplicationRequestDto;
import com.vlad.project.dto.LoanOfferDto;
import com.vlad.project.service.ApplicationService;
import com.vlad.project.service.OfferService;import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@Controller
@RequestMapping("/application")
@RequiredArgsConstructor
public class ApplicationController {

    private final ApplicationService service;
    private final OfferService offerService;

    @PostMapping()
    public ResponseEntity<List<LoanOfferDto>> apllication(@RequestBody
                                                          @Valid
                                                          LoanApplicationRequestDto requestDto) {
        return ResponseEntity.ok(service.getApplicationFromMsConveyor(requestDto));
    }

    @PutMapping("/offer")
    public ResponseEntity<Void> offer(@RequestBody
                                      LoanOfferDto requestDto) {
        offerService.getOffers(requestDto);

        return ResponseEntity.ok().build();
    }
}
