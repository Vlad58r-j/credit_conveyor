package com.vlad.project.controller;

import com.vlad.project.dto.FinishRegistrationRequestDTO;
import com.vlad.project.dto.LoanApplicationRequestDto;
import com.vlad.project.dto.LoanOfferDto;
import com.vlad.project.service.ApplicationService;
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
    private final ApplicationService applicationService;

    @PostMapping("/application")
    @Operation(summary = "Расчитываем возможные условия кредита", description = """
            Приходит request, сохраняем  клиента и кредитное приложение в бд,
            после чего считаем 4 кредитных предложения""")
    public ResponseEntity<List<LoanOfferDto>> application(@RequestBody @Parameter(name = "Заемщик",
            description = "Данные о заемщики")
                                                          LoanApplicationRequestDto requestDto) {
        log.info("Зашли в метод API deal/application");

        return ResponseEntity.ok(loanApplicationRequestService.createClientAndApplication(requestDto));
    }

    @PutMapping("/offer")
    @Operation(summary = "Обновление данных о предложениях", description = """
            Приходит запрос состоящий из LoanOfferDto, достаем запись из бд по applicationId,
            обновляем статус записи и сохраняем изменения в бд""")
    public ResponseEntity<Void> offer(@RequestBody
                                      @Parameter(name = "Кредит", description = "Кредитное предложение")
                                      LoanOfferDto requestDto) {
        log.info("Вызван deal/offer API");
        boolean resultMethodOfferService = applicationService.updateApplication(requestDto);

        if (resultMethodOfferService) {
            log.info("Запись в базе данных была обновлена");
            return ResponseEntity.ok().build();
        } else {
            log.info("Запись в базе данных не была обновлена");
            return ResponseEntity.badRequest().build();
        }

    }

    @PutMapping("/calculate/{applicationId}")
    public void calculateId(FinishRegistrationRequestDTO requestDto,
                            @RequestParam Long applicationId) {
    }


}
