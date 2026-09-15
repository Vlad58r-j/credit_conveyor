package com.vlad.project.service.integrationTest;

import com.vlad.project.IntegrationTestBase;
import com.vlad.project.database.entity.Credit;
import com.vlad.project.service.CreditService;
import com.vlad.project.utils.CreditDtoUtils;
import com.vlad.project.utils.CreditUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class CreditServiceIntegrationTest extends IntegrationTestBase {

    @Autowired
    private CreditService creditService;

    @Test
    void checkSaveCredit() {
        Credit resultCredit = creditService.saveCredit(CreditDtoUtils.getCorrectCreditDto());

        Assertions.assertEquals(CreditUtils.getCreditForCreditTest(), resultCredit);
    }
}