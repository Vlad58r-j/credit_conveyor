package com.vlad.project.service;

import com.vlad.project.database.entity.Application;
import com.vlad.project.database.entity.AppliedOffer;
import com.vlad.project.database.entity.Credit;
import com.vlad.project.database.repository.ApplicationRepository;
import com.vlad.project.dto.LoanOfferDto;
import com.vlad.project.dto.enumStatus.ApplicationStatus;
import com.vlad.project.exception.FindApplicationException;
import com.vlad.project.exception.UpdateApplicationException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class ApplicationServiceImpl implements ApplicationService {

    private final ApplicationRepository applicationRepository;

    @Override
    @Transactional(readOnly = true)
    public Optional<Application> findApplicationById(Long id) {
        log.info("Вызвался метод -> findApplicationById, чтобы достать из бд заявку");
        try {
            return applicationRepository.findById(id);
        } catch (Exception exception) {
            throw new FindApplicationException("Пользователь не был найден");
        }
    }

    @Override
    @Transactional(rollbackFor = UpdateApplicationException.class)
    public boolean updateApplication(LoanOfferDto dto) {
        log.info("Вызвался метод-> updateApplication для обновления данных в заявке после вызова API /deal/offers");
        try {
            Optional<Application> result = findApplicationById(dto.getApplicationId())
                    .map(application -> {
                        application.setStatus(ApplicationStatus.APPROVED);
                        application.getStatusHistory().add(ApplicationStatus.APPROVED);
                        application.setAppliedOffer(AppliedOffer.builder()
                                .applicationId(dto.getApplicationId())
                                .requestedAmount(dto.getRequestedAmount())
                                .totalAmount(dto.getTotalAmount())
                                .term(dto.getTerm())
                                .monthlyPayment(dto.getMonthlyPayment())
                                .rate(dto.getRate())
                                .isInsuranceEnabled(dto.getIsInsuranceEnabled())
                                .isSalaryClient(dto.getIsSalaryClient())
                                .build());
                        return application;
                    })
                    .map(applicationRepository::save);
            return result.isPresent();

        } catch (Exception exception) {
            throw new UpdateApplicationException("Данные заявки не были обнавлены");
        }
    }

    @Override
    @Transactional(rollbackFor = UpdateApplicationException.class)
    public void updateApplicationForCreditData(Credit creidt, Long applicationId) {
        log.info("""
                Вызвался метод-> updateApplicationForCreditData для обновления данных
                в заявке после вызова API /deal/calculate""");
        findApplicationById(applicationId)
                .map(application -> {
                    application.setCredit(creidt);
                    application.setStatus(ApplicationStatus.CC_APPROVED);
                    application.getStatusHistory().add(ApplicationStatus.CC_APPROVED);
                    return application;
                })
                .map(applicationRepository::save)
                .orElseThrow(() -> new UpdateApplicationException("Данные заявки не были обновлены"));
    }

}
