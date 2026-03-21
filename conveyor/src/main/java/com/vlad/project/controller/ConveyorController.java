package com.vlad.project.controller;

import com.vlad.project.dto.LoanApplicationRequestDto;
import com.vlad.project.dto.LoanOfferDto;
import com.vlad.project.filter.exception.PreScoringException;
import com.vlad.project.service.LoanApplicationRequestServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/conveyor")
@RequiredArgsConstructor
public class ConveyorController {

    private final LoanApplicationRequestServiceImpl loanApplicationRequestService;

    @PostMapping("/offers")
    public ResponseEntity<List<LoanOfferDto>> offers(@RequestBody LoanApplicationRequestDto loan) {
        return ResponseEntity.ok(loanApplicationRequestService.preScoring(loan));
    }

    @ExceptionHandler(PreScoringException.class)
    public ResponseEntity<String> exceptionHandler(PreScoringException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }
}
