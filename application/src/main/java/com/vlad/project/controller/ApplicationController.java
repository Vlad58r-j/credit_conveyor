package com.vlad.project.controller;

import com.vlad.project.dto.LoanApplicationRequestDto;
import com.vlad.project.dto.LoanOfferDto;
import com.vlad.project.service.ApplicationService;
import jakarta.validation.Valid;
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

    @PostMapping()
    public ResponseEntity<List<LoanOfferDto>> apllication(@RequestBody
                                                          @Valid
                                                          LoanApplicationRequestDto requestDto) {
        return ResponseEntity.ok(service.getApplicationFromMsConveyor(requestDto));
    }

    @PutMapping("/offer")
    public ResponseEntity<Void> offer(@RequestBody
                                      LoanOfferDto requestDto) {
        return ResponseEntity.ok().build();
    }
}
