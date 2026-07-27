package com.vlad.project.service;

import com.vlad.project.database.entity.Application;
import com.vlad.project.database.entity.Client;
import com.vlad.project.database.repository.ApplicationRepository;
import com.vlad.project.dto.LoanApplicationRequestDto;
import com.vlad.project.dto.LoanOfferDto;
import com.vlad.project.dto.enumStatus.ApplicationStatus;
import com.vlad.project.dto.enumStatus.Gender;
import com.vlad.project.mapper.ApplicationEditMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;

import static com.vlad.project.utils.LoanApplicationRequestTestUtil.getCorrectLoanApplicationDto;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class LoanApplicationRequestServiceTest {

    @Mock
    private ApplicationRepository repository;

    @Mock
    private ApplicationEditMapper applicationEditMapper;

    @Spy
    @InjectMocks
    private LoanApplicationRequestServiceImpl service;

    @Test
    void checkCreateClientAndApplication() {
        LoanApplicationRequestDto dto = getCorrectLoanApplicationDto();

        Application saveApplication = Application.builder()
                .client(Client.builder()
                        .firstname(dto.getFirstName())
                        .lastname(dto.getLastName())
                        .middleName(dto.getMiddleName())
                        .birthDate(dto.getBirthdate())
                        .email(dto.getEmail())
                        .gender(Gender.NON_BINARY)
                        .build())
                .status(ApplicationStatus.PREAPPROVAL)
                .creationDate(LocalDate.now())
        .build();

        when(applicationEditMapper.map(dto))
                .thenReturn(saveApplication);

        when(repository.save(saveApplication))
                .thenReturn(saveApplication);

        List<LoanOfferDto> applicationResult = service.createClientAndApplication(dto);

        assertNotNull(applicationResult);

        verify(repository, times(1)).save(any(Application.class));
    }

}
