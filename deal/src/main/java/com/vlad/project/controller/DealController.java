package com.vlad.project.controller;

import com.vlad.project.dto.FinishRegistrationRequestDTO;
import com.vlad.project.dto.LoanApplicationRequestDto;
import com.vlad.project.dto.LoanOfferDto;
import com.vlad.project.service.LoanApplicationRequestService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@Controller
@RequestMapping("/deal")
@RequiredArgsConstructor
public class DealController {

    private final LoanApplicationRequestService loanApplicationRequestService;

    @PostMapping("/application")
    @Operation(summary = "Расчитываем возможные условия кредита", description = "Приходит request, сохраняем " +
            "клиента и кредитное приложение в бд, после чего считаем 4 кредитных предложения")
    public ResponseEntity<List<LoanOfferDto>> application(@RequestBody @Parameter(name = "Заемщик",
            description = "Данные о заемщики")
                                                          LoanApplicationRequestDto requestDto) {
        log.info("Зашли в метод API deal/application");

        return ResponseEntity.ok(loanApplicationRequestService.createClientAndApplication(requestDto));
    }

    @PutMapping("/offer")
    public void offer(LoanOfferDto requestDto) {
    }

    @PutMapping("/calculate/{applicationId}")
    public void calculateId(FinishRegistrationRequestDTO requestDto,
                            @RequestParam Long applicationId) {
    }


}
