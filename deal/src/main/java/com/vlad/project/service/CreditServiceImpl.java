package com.vlad.project.service;

import com.vlad.project.client.HttpConveyorClient;
import com.vlad.project.database.entity.Application;
import com.vlad.project.database.entity.Credit;
import com.vlad.project.database.repository.CreditRepository;
import com.vlad.project.dto.CreditDto;
import com.vlad.project.dto.FinishRegistrationRequestDTO;
import com.vlad.project.dto.ScoringDataDto;
import com.vlad.project.exception.CreateCreditException;
import com.vlad.project.exception.FindApplicationException;
import com.vlad.project.mapper.CreditEditMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class CreditServiceImpl implements CreditService {

    private final CreditEditMapper creditEditMapper;
    private final CreditRepository creditRepository;
    private final ApplicationService applicationService;
    private final HttpConveyorClient applicationToOfferClient;

    @Override
    @Transactional(rollbackFor = CreateCreditException.class)
    public void addDataToScoringDto(FinishRegistrationRequestDTO dto, Long applicationId) {
        log.info("Вызван метод -> addDataToScoringDto для насыщения ScoringDataDto информацией");
        Application application = applicationService.findApplicationById(applicationId)
                .orElseThrow(() -> new FindApplicationException("Заявка не найдена"));

        ScoringDataDto actualScoringDataDto = ScoringDataDto.builder()
                .amount(application.getAppliedOffer().getRequestedAmount())
                .term(application.getAppliedOffer().getTerm())
                .firstName(application.getClient().getFirstname())
                .lastName(application.getClient().getLastname())
                .gender(dto.getGender())
                .birthday(application.getClient().getBirthDate())
                .passportSeries(application.getClient().getPassport().getSeries())
                .passportNumber(application.getClient().getPassport().getNumber())
                .passportIssueDate(dto.getPassportIssueDate())
                .passportIssueBranch(dto.getPassportIssueBranch())
                .maritalStatus(dto.getMaritalStatus())
                .dependentAmount(dto.getDependentAmount())
                .employment(dto.getEmployment())
                .account(dto.getAccount())
                .isInsuranceEnabled(application.getAppliedOffer().getIsInsuranceEnabled())
                .isSalaryClient(application.getAppliedOffer().getIsSalaryClient())
                .build();

        CreditDto creditDto = redirectToConveyerMvc(actualScoringDataDto);
        Credit savedCredit = saveCredit(creditDto);
        applicationService.updateApplicationForCreditData(savedCredit, applicationId);
    }

    @Override
    public CreditDto redirectToConveyerMvc(ScoringDataDto dto) {
        log.info("Вызван метод -> redirectToConveyerMvc для переадресации на другой API");
        return applicationToOfferClient.getCredit(dto);
    }

    @Override
    @Transactional(rollbackFor = CreateCreditException.class)
    public Credit saveCredit(CreditDto dto) {
        log.info("Зашли в метод -> saveCredit для сохранения данных о кредите в бд");
        return Optional.of(dto)
                .map(creditEditMapper::map)
                .map(creditRepository::save)
                .orElseThrow(() -> new CreateCreditException("Ошибка при сохренении кредита"));

    }
}
