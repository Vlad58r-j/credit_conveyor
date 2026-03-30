package com.vlad.project.controller;

import com.vlad.project.dto.LoanApplicationRequestDto;
import com.vlad.project.dto.LoanOfferDto;
import com.vlad.project.service.LoanApplicationRequestServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Slf4j
@Validated
@Controller
@RequiredArgsConstructor
@RequestMapping("/conveyor")
@Tag(name = "Контроллер для кредитных заявок",
        description = "Рассчитываем и выводим кредитные заявки и предложения")
public class ConveyorController {

    private final LoanApplicationRequestServiceImpl loanApplicationRequestService;

    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Список предложений"),
            @ApiResponse(responseCode = "400", description = "Ошибка в прескоринге данных")
    })
    @Operation(summary = "Рассчитываем возможные условия кредита",
            description = "Приходит заявка на кредит, происходит валидация, " +
                          "если все условия удовлетворяют, то выводится 4 кредитных предложения")
    @PostMapping("/offers")
    public ResponseEntity<List<LoanOfferDto>> offers(@RequestBody @Valid
                                                     @Parameter(name = "Заемщик",
                                                             description = "Содержит все данные о заемщике")
                                                     LoanApplicationRequestDto loan) {
        log.info("Зашли в метод offers для генерации кредитных предложений");
        return ResponseEntity.ok(loanApplicationRequestService.generateCreditOffers(loan));
    }


}