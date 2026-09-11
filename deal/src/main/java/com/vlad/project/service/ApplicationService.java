package com.vlad.project.service;

import com.vlad.project.database.entity.Application;
import com.vlad.project.database.entity.Credit;
import com.vlad.project.dto.LoanOfferDto;

import java.util.Optional;

public interface ApplicationService{

    Optional<Application> findApplicationById(Long id);

    boolean updateApplication(LoanOfferDto dto);

    void updateApplicationForCreditData(Credit creidt, Long applicationId);

}
