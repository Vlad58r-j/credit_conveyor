package com.vlad.project.controller;

import com.vlad.project.service.ApplicationServiceImpl;
import com.vlad.project.service.OfferServiceImpl;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import tools.jackson.databind.ObjectMapper;

import java.util.List;

import static com.vlad.project.util.LoanApplicationRequestDtoUtil.*;
import static com.vlad.project.util.LoanOfferDtoUtil.correctLoanOfferDto;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ApplicationController.class)
public class ApplicationControllerTest {


    @Autowired
    private MockMvc mockMvc;
    private final ObjectMapper mapper = new ObjectMapper();

    @MockitoBean
    private ApplicationServiceImpl applicationService;
    @MockitoBean
    private OfferServiceImpl offerService;

    @Test
    @SneakyThrows
    void checkApplicationValidation() {
        when(applicationService.getApplicationFromMsConveyor(correctLoanApplicationDto()))
                .thenReturn(List.of(correctLoanOfferDto()));

        mockMvc.perform(post("/application")
                        .contentType(APPLICATION_JSON)
                        .content(mapper.writeValueAsString(correctLoanApplicationDto())))
                .andExpect(status().is2xxSuccessful());
    }

    @Test
    @SneakyThrows
    void checkApplicationValidationWithoutMiddleName() {
        mockMvc.perform(post("/application")
                        .contentType(APPLICATION_JSON)
                        .content(mapper.writeValueAsString(correctLoanApplicationDtoWithoutMiddleName())))
                .andExpect(status().is2xxSuccessful());
    }

    @Test
    @SneakyThrows
    void checkApplicationValidationWithNotCorrectAmount() {
        mockMvc.perform(post("/application")
                        .contentType(APPLICATION_JSON)
                        .content(mapper.writeValueAsString(notCorrectLoanApplicationDtoWithNotCorrectAmount())))
                .andExpect(status().isBadRequest());
    }

    @Test
    @SneakyThrows
    void checkApplicationValidationWithNotCorrectTerm() {
        mockMvc.perform(post("/application")
                        .contentType(APPLICATION_JSON)
                        .content(mapper.writeValueAsString(notCorrectLoanApplicationDtoWithNotCorrectTerm())))
                .andExpect(status().isBadRequest());
    }

    @Test
    @SneakyThrows
    void checkApplicationValidationWithNotCorrectFirstName() {
        mockMvc.perform(post("/application")
                        .contentType(APPLICATION_JSON)
                        .content(mapper.writeValueAsString(notCorrectLoanApplicationDtoWithNotCorrectFirstName())))
                .andExpect(status().isBadRequest());
    }

    @Test
    @SneakyThrows
    void checkApplicationValidationWithNotCorrectLastName() {
        mockMvc.perform(post("/application")
                        .contentType(APPLICATION_JSON)
                        .content(mapper.writeValueAsString(notCorrectLoanApplicationDtoWithNotCorrectLastName())))
                .andExpect(status().isBadRequest());
    }

    @Test
    @SneakyThrows
    void checkApplicationValidationWithNotCorrectEmail() {
        mockMvc.perform(post("/application")
                        .contentType(APPLICATION_JSON)
                        .content(mapper.writeValueAsString(notCorrectLoanApplicationDtoWithNotCorrectEmail())))
                .andExpect(status().isBadRequest());
    }

    @Test
    @SneakyThrows
    void checkApplicationValidationWithNotCorrectBirthDate() {
        mockMvc.perform(post("/application")
                        .contentType(APPLICATION_JSON)
                        .content(mapper.writeValueAsString(notCorrectLoanApplicationDtoWithNotCorrectBirthDate())))
                .andExpect(status().isBadRequest());
    }

    @Test
    @SneakyThrows
    void checkApplicationValidationWithNotCorrectPassportSeries() {
        mockMvc.perform(post("/application")
                        .contentType(APPLICATION_JSON)
                        .content(mapper.writeValueAsString(notCorrectLoanApplicationDtoWithNotCorrectPassportSeries())))
                .andExpect(status().isBadRequest());
    }

    @Test
    @SneakyThrows
    void checkApplicationValidationWithNotCorrectPassportNumber() {
        mockMvc.perform(post("/application")
                        .contentType(APPLICATION_JSON)
                        .content(mapper.writeValueAsString(notCorrectLoanApplicationDtoWithNotCorrectPassportNumber())))
                .andExpect(status().isBadRequest());
    }

    @Test
    @SneakyThrows
    void checkOfferApi() {
        doNothing()
                .when(offerService)
                .getOffers(any());

        mockMvc.perform(put("/application/offer")
                        .contentType(APPLICATION_JSON)
                        .content(mapper.writeValueAsString(correctLoanOfferDto())))
                .andExpect(status().is2xxSuccessful());
    }
}
