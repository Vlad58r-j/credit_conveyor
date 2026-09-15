package com.vlad.project.service;

import com.vlad.project.client.HttpConveyorClient;
import com.vlad.project.database.entity.Application;
import com.vlad.project.database.entity.Credit;
import com.vlad.project.database.repository.CreditRepository;
import com.vlad.project.dto.CreditDto;
import com.vlad.project.dto.FinishRegistrationRequestDTO;
import com.vlad.project.dto.ScoringDataDto;
import com.vlad.project.mapper.CreditEditMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static com.vlad.project.utils.ApplicationUtil.applicationResult;
import static com.vlad.project.utils.CreditDtoUtils.getCorrectCreditDto;
import static com.vlad.project.utils.CreditUtils.getCredit;
import static com.vlad.project.utils.FinishRegistrationRequestDtoUtill.registrationDTO;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CreditServiceUnitTest {
    public static final long APPLICATION_ID = 1L;

    @Mock
    private CreditEditMapper creditEditMapper;
    @Mock
    private CreditRepository creditRepository;
    @Mock
    private ApplicationService applicationService;
    @Mock
    private HttpConveyorClient applicationToOfferClient;

    @InjectMocks
    private CreditServiceImpl creditService;

    @Test
    void addDataToScoringDtoTest() {
        Credit credit = getCredit();
        CreditDto creditDto = getCorrectCreditDto();
        Application application = applicationResult();
        FinishRegistrationRequestDTO registrationDTO = registrationDTO();

        when(applicationService.findApplicationById(APPLICATION_ID)).thenReturn(Optional.of(application));
        when(applicationToOfferClient.getCredit(any(ScoringDataDto.class))).thenReturn(creditDto);
        when(creditEditMapper.map(creditDto)).thenReturn(credit);
        when(creditRepository.save(credit)).thenReturn(credit);

        creditService.addDataToScoringDto(
                registrationDTO,
                APPLICATION_ID);

        verify(applicationService).findApplicationById(APPLICATION_ID);
        verify(applicationToOfferClient).getCredit(any(ScoringDataDto.class));
        verify(creditEditMapper).map(creditDto);
        verify(creditRepository).save(credit);
        verify(applicationService).updateApplicationForCreditData(
                        credit,
                        APPLICATION_ID);
    }
}