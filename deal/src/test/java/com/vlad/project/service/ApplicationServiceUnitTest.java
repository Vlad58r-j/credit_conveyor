package com.vlad.project.service;

import com.vlad.project.database.entity.Application;
import com.vlad.project.database.repository.ApplicationRepository;
import com.vlad.project.dto.enumStatus.ApplicationStatus;
import com.vlad.project.utils.LoanOfferDtoUtil;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static com.vlad.project.utils.ApplicationUtil.application;
import static com.vlad.project.utils.ApplicationUtil.applicationResult;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ApplicationServiceUnitTest {

    @Mock
    private ApplicationRepository repository;

    @Spy
    @InjectMocks
    private ApplicationServiceImpl service;

    @Test
    void checkUpdateApplication() {
        Application application = application();
        when(repository.findById(1L)).thenReturn(Optional.of(application));
        when(repository.save(any(Application.class))).thenReturn(applicationResult());

        boolean resultService = service.updateApplication(LoanOfferDtoUtil.getCorrectLoanOfferDto());

        verify(repository).save(any(Application.class));
        assertTrue(resultService);
        assertEquals(ApplicationStatus.CC_APPROVED, application.getStatus());
        assertEquals(applicationResult().getAppliedOffer(), application.getAppliedOffer());
        assertEquals(applicationResult().getStatusHistory(), application.getStatusHistory());
    }
}
