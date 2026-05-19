package com.vlad.project.controller;

import com.vlad.project.dto.FinishRegistrationRequestDTO;
import com.vlad.project.dto.LoanApplicationRequestDto;
import com.vlad.project.dto.LoanOfferDto;
import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Slf4j
@Controller
@RequestMapping("/deal")
public class DealController {

    @PostMapping("/application")
    @Operation(summary = "Расчитываем возможные условия кредита", description = "")
    public ResponseEntity<List<LoanOfferDto>> application(LoanApplicationRequestDto requestDto){
        return ResponseEntity.ok(null);
    }

    @PutMapping("/offer")
    public void offer(LoanOfferDto requestDto) {
    }

    @PutMapping("/calculate/{applicationId}")
    public void calculateId(FinishRegistrationRequestDTO requestDto,
                            @RequestParam Long applicationId) {
    }


}
