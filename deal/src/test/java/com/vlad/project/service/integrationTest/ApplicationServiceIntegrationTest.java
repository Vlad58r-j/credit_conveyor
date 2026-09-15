package com.vlad.project.service.integrationTest;

import com.vlad.project.IntegrationTestBase;
import com.vlad.project.database.entity.Application;
import com.vlad.project.service.ApplicationService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

public class ApplicationServiceIntegrationTest extends IntegrationTestBase {

    public static final long CORRECT_APPLICATION_ID = 1L;
    public static final long UNCORRECT_APPLICATION_ID = 200L;
    @Autowired
    private ApplicationService applicationService;

    @Test
    void checkFindByCorrectId() {
        Optional<Application> maybeApplication = applicationService.findApplicationById(CORRECT_APPLICATION_ID);

        assertTrue(maybeApplication.isPresent());
    }

    @Test
    void checkFindByUncorrectId() {
        Optional<Application> maybeApplication = applicationService.findApplicationById(UNCORRECT_APPLICATION_ID);

        assertTrue(maybeApplication.isEmpty());
    }

}
