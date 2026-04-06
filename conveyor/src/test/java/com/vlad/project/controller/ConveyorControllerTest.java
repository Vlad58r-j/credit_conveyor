package com.vlad.project.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import tools.jackson.databind.ObjectMapper;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.beans.factory.annotation.Autowired;
import com.vlad.project.service.LoanApplicationRequestServiceImpl;

import static org.springframework.http.MediaType.*;
import static com.vlad.project.utils.LoanApplicationRequestUtil.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@SpringBootTest
public class ConveyorControllerTest {

    private MockMvc mockMvc;
    private final ObjectMapper objectMapper = new ObjectMapper();
    @Autowired
    private LoanApplicationRequestServiceImpl service;

    @BeforeEach
    void initMock() {
        this.mockMvc = standaloneSetup(new ConveyorController(service)).build();
    }

    @Test
    void checkOffersStatusRequestWithCorrectDto() throws Exception {
        mockMvc.perform(post("/conveyor/offers")
                        .contentType(APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(getCorrectLoanApplicationRequestDto())))
                .andExpect(status().is2xxSuccessful());
    }

    @Test
    void checkOffersStatusRequestWithNotCorrectNameDto() throws Exception{
        mockMvc.perform(post("/conveyor/offers")
                        .contentType(APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(getNotCorrectNameLoanApplicationRequestDto())))
                .andExpect(status().is4xxClientError());
    }

    @Test
    void checkOffersStatusRequestWithNotCorrectLastNameDto() throws Exception{
        mockMvc.perform(post("/conveyor/offers")
                        .contentType(APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(getNotCorrectLastNameLoanApplicationRequestDto())))
                .andExpect(status().is4xxClientError());
    }

    @Test
    void checkOffersStatusRequestWithNotCorrectMiddleNameDto() throws Exception{
        mockMvc.perform(post("/conveyor/offers")
                        .contentType(APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(getNotCorrectMidleNameLoanApplicationRequestDto())))
                .andExpect(status().is4xxClientError());
    }

    @Test
    void checkOffersStatusRequestWithNotCorrectBirthDateDto() throws Exception{
        mockMvc.perform(post("/conveyor/offers")
                        .contentType(APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(getNotCorrectBirthdateLoanApplicationRequestDto())))
                .andExpect(status().is4xxClientError());
    }

    @Test
    void checkOffersStatusRequestWithNotCorrectEmailDto() throws Exception{
        mockMvc.perform(post("/conveyor/offers")
                        .contentType(APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(getNotCorrectEmailLoanApplicationRequestDto())))
                .andExpect(status().is4xxClientError());
    }

    @Test
    void checkOffersStatusRequestWithNotCorrectPassportSeriesDto() throws Exception{
        mockMvc.perform(post("/conveyor/offers")
                        .contentType(APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(getNotCorrectPassportSeriesLoanApplicationRequestDto())))
                .andExpect(status().is4xxClientError());
    }

    @Test
    void checkOffersStatusRequestWithNotCorrectPassportNumberDto() throws Exception{
        mockMvc.perform(post("/conveyor/offers")
                        .contentType(APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(getNotCorrectPassportNumberLoanApplicationRequestDto())))
                .andExpect(status().is4xxClientError());
    }

    @Test
    void checkOffersStatusRequestWithNotCorrectAmountDto() throws Exception{
        mockMvc.perform(post("/conveyor/offers")
                        .contentType(APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(getNotCorrectAmountLoanApplicationRequestDto())))
                .andExpect(status().is4xxClientError());
    }

    @Test
    void checkOffersStatusRequestWithNotCorrectTermDto() throws Exception{
        mockMvc.perform(post("/conveyor/offers")
                        .contentType(APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(getNotCorrectTermLoanApplicationRequestDto())))
                .andExpect(status().is4xxClientError());
    }

}

