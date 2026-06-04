package com.vlad.project.service;

import com.vlad.project.client.HttpConveyorClient;
import com.vlad.project.database.entity.Application;
import com.vlad.project.database.repository.ApplicationRepository;
import com.vlad.project.dto.LoanApplicationRequestDto;
import com.vlad.project.dto.LoanOfferDto;
import com.vlad.project.exception.CreateApplicationException;
import com.vlad.project.mapper.ApplicationEditMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class LoanApplicationRequestServiceImpl implements LoanApplicationRequestService {

    private final ApplicationRepository applicationRepository;
    private final ApplicationEditMapper applicationEditMapper;
    private final HttpConveyorClient applicationToOfferClient;

    @Override
    @Transactional
    public Application createClientAndApplication(LoanApplicationRequestDto dto) {
        Application application = createApplication(dto);

        log.info("Сохранили клиента и кредитные данные в бд");
        return application;
    }

    private Application createApplication(LoanApplicationRequestDto dto) {
        log.info("Зашли в метод сохранения кредитных данных в бд");
        return Optional.of(dto)
                .map(applicationEditMapper::map)
                .map(applicationRepository::save)
                .orElseThrow(() -> new CreateApplicationException("Кредитное данные не сохранены"));
    }

    public List<LoanOfferDto> getOffers(LoanApplicationRequestDto dto) {
        return applicationToOfferClient.getOffers(dto);
    }
}
