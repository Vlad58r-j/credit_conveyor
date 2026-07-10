package com.vlad.project.controller;

import com.vlad.project.database.entity.Application;
import com.vlad.project.dto.LoanApplicationRequestDto;
import com.vlad.project.dto.LoanOfferDto;
import com.vlad.project.exception.GlobalExceptionHandler;
import com.vlad.project.service.LoanApplicationRequestService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import tools.jackson.databind.ObjectMapper;

import java.util.List;

import static com.vlad.project.utils.LoanApplicationRequestTestUtil.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

@SpringBootTest
class DealControllerTest {

    private MockMvc mockMvc;
    private final ObjectMapper objectMapper = new ObjectMapper();
    @MockitoBean
    private LoanApplicationRequestService loanApplicationService;
    @Autowired
    private GlobalExceptionHandler exceptionHandler;

    @BeforeEach
    void initMock() {
        this.mockMvc = standaloneSetup(new DealController(loanApplicationService))
                .setControllerAdvice(exceptionHandler)
                .build();
    }

    @Test
    void checkDealControllerWithCorrectDto() throws Exception {
        LoanOfferDto offerDto = new LoanOfferDto(1L, null, null, null,
                null, null, null, null);

        Application application = Application.builder()
                .id(1L)
                .build();

        when(loanApplicationService.getOffers(any()))
                .thenReturn(List.of(offerDto));

        when(loanApplicationService.createClientAndApplication(any(LoanApplicationRequestDto.class)))
                .thenReturn(application);

        mockMvc.perform(post("/deal/application")
                        .contentType(APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(correctLoanApplicationDto)))
                .andExpect(status().is2xxSuccessful());
    }

}
