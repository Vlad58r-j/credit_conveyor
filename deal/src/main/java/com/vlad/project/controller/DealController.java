package com.vlad.project.controller;

import com.vlad.project.dto.FinishRegistrationRequestDTO;
import com.vlad.project.dto.LoanApplicationRequestDto;
import com.vlad.project.dto.LoanOfferDto;
import com.vlad.project.service.ApplicationService;
import com.vlad.project.service.CreditService;
import com.vlad.project.service.LoanApplicationRequestService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "Сделка", description = """
        Расчитываем возможные условия кредита; выбираем одно из предложений; завершаем регистрацию""")
public class DealController {

    private final LoanApplicationRequestService loanApplicationRequestService;
    private final ApplicationService applicationService;
    private final CreditService creditService;

    @PostMapping("/application")
    @Operation(summary = "Расчитываем возможные условия кредита", description = """
            Приходит request, сохраняем  клиента и кредитное приложение в бд,
            после чего считаем 4 кредитных предложения""")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Список предложений"),
            @ApiResponse(responseCode = "400", description = "Ошибка при сохранении данных")
    })
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
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Данные заявки обновляются в базе данных"),
            @ApiResponse(responseCode = "400", description = "Ошибка при обновлении данных")
    })
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
    @Operation(summary = "Завершение регистрации и полный подсчёт кредита", description = """
            По API приходит FinishRegistrationRequestDTO и applicationId, достается из бд заявка,
            после ScoringDataDTO насыщается данными и отправляется /conveyor/calculation,
            на основе вернувшихся с другого API данных в бд сохраняется сущность Credit и обновляются
            данные в Application""")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Сохраняем кредитные данные по id заявки в базу данных"),
            @ApiResponse(responseCode = "400", description = "Ошибка при сохранении данных в бд")
    })
    public ResponseEntity<Void> calculateId(@Parameter(name = "Данные пользователя",
                                                        description = "Данные для финальной регистрации пользователя")
                                                FinishRegistrationRequestDTO requestDto,
                                            @Parameter(name = "Id", description = "Id предложения") @PathVariable
                                            Long applicationId) {
        log.info("Вызван deal/calculate/{applicationId} API");
        creditService.addDataToScoringDto(requestDto, applicationId);

        return ResponseEntity.ok().build();
    }


}
