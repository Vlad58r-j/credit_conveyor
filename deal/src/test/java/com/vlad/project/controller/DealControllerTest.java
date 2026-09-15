package com.vlad.project.controller;

import com.vlad.project.dto.FinishRegistrationRequestDTO;
import com.vlad.project.dto.LoanApplicationRequestDto;
import com.vlad.project.dto.LoanOfferDto;
import com.vlad.project.exception.GlobalExceptionHandler;
import com.vlad.project.service.ApplicationService;
import com.vlad.project.service.CreditService;
import com.vlad.project.service.LoanApplicationRequestService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import tools.jackson.databind.ObjectMapper;

import java.util.List;

import static com.vlad.project.utils.FinishRegistrationRequestDtoUtill.registrationDTO;
import static com.vlad.project.utils.LoanApplicationRequestTestUtil.getCorrectLoanApplicationDto;
import static com.vlad.project.utils.LoanOfferDtoUtil.getCorrectLoanOfferDto;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

@ExtendWith(MockitoExtension.class)
class DealControllerTest {

    public static final long APPLICATION_ID = 1L;
    private MockMvc mockMvc;
    private final ObjectMapper objectMapper = new ObjectMapper();
    @Mock
    private LoanApplicationRequestService loanApplicationService;
    @Mock
    private ApplicationService applicationService;
    @Mock
    private CreditService creditService;
    @Mock
    private GlobalExceptionHandler exceptionHandler;
    @InjectMocks
    private DealController dealController;

    @BeforeEach
    void initMock() {
        mockMvc = standaloneSetup(dealController).setControllerAdvice(exceptionHandler).build();
    }

    @Test
    void checkDealControllerWithCorrectDto() throws Exception {
        LoanApplicationRequestDto requestDto = getCorrectLoanApplicationDto();
        LoanOfferDto offerDto = getCorrectLoanOfferDto();
        List<LoanOfferDto> offers = List.of(offerDto);

        when(loanApplicationService.createClientAndApplication(requestDto)).thenReturn(offers);
        mockMvc.perform(post("/deal/application").contentType(APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestDto))).andExpect(status().is2xxSuccessful());
        verify(loanApplicationService).createClientAndApplication(any(LoanApplicationRequestDto.class));
    }

    @Test
    void checkDealOfferApi() throws Exception {
        LoanOfferDto requestOfferDto = getCorrectLoanOfferDto();
        boolean resultServiceMethod = true;

        when(applicationService.updateApplication(requestOfferDto)).thenReturn(resultServiceMethod);
        mockMvc.perform(put("/deal/offer").contentType(APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestOfferDto))).andExpect(status().is2xxSuccessful());

        verify(applicationService).updateApplication(any(LoanOfferDto.class));
    }

    @Test
    void checkDealOfferApiWithFalseResponse() throws Exception {
        LoanOfferDto requestOfferDto = getCorrectLoanOfferDto();
        boolean resultServiceMethod = false;

        when(applicationService.updateApplication(requestOfferDto)).thenReturn(resultServiceMethod);
        mockMvc.perform(put("/deal/offer").contentType(APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestOfferDto))).andExpect(status().is4xxClientError());

        verify(applicationService).updateApplication(any(LoanOfferDto.class));
    }

    @Test
    void checkDealCalculateIdApi() throws Exception {
        FinishRegistrationRequestDTO registrationDTO = registrationDTO();

        mockMvc.perform(put("/deal/calculate/" + APPLICATION_ID).contentType(APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(registrationDTO))).andExpect(status().is2xxSuccessful());

        verify(creditService).addDataToScoringDto(any(FinishRegistrationRequestDTO.class), any(Long.class));
    }
}
