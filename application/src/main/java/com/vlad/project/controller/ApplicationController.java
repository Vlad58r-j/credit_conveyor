package com.vlad.project.controller;

import com.vlad.project.dto.LoanApplicationRequestDto;
import com.vlad.project.dto.LoanOfferDto;
import com.vlad.project.service.ApplicationService;
import com.vlad.project.service.OfferService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "Заявка", description = """
        Получаем данные клиента и переадресовываем на другой микросервис, где даныне обрабатываются""")
public class ApplicationController {

    private final ApplicationService service;
    private final OfferService offerService;

    @PostMapping()
    @Operation(summary = "Получаем кредитные предложения", description = """
            Приходит request, после чего перенаправляем данные на другой микросервис, откуда приходят предложения
            и данные из request сохраняются в бд""")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Список предложений"),
            @ApiResponse(responseCode = "400", description = "Ошибка при сохранении данных")
    })
    public ResponseEntity<List<LoanOfferDto>> apllication(@RequestBody
                                                          @Valid
                                                          LoanApplicationRequestDto requestDto) {
        return ResponseEntity.ok(service.getApplicationFromMsConveyor(requestDto));
    }

    @PutMapping("/offer")
    @Operation(summary = "Обновление данных о предложениях", description = """
            Приходит request, после чего перенаправляем на другой микросервис,
             где данные в бд обновляются и сохраняются""")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Данные заявки обновляются в базе данных"),
            @ApiResponse(responseCode = "400", description = "Ошибка при обновлении данных")
    })
    public ResponseEntity<Void> offer(@RequestBody
                                      LoanOfferDto requestDto) {
        offerService.getOffers(requestDto);

        return ResponseEntity.ok().build();
    }
}
