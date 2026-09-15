package com.vlad.project.utils;

import com.vlad.project.database.entity.Application;
import com.vlad.project.database.entity.AppliedOffer;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static com.vlad.project.dto.enumStatus.ApplicationStatus.*;
import static com.vlad.project.utils.ClientUtill.*;
import static java.math.BigDecimal.valueOf;

public class ApplicationUtil {

    public static Application application() {
        return new Application(1L, client(), null, PREAPPROVAL,
                LocalDate.now(), null, LocalDate.now(), "3231",
                new ArrayList<>(List.of(PREAPPROVAL)));
    }

    public static Application applicationResult() {
        return new Application(1L, client(), null, APPROVED,
                LocalDate.now(), new AppliedOffer(1L, valueOf(25000), valueOf(20000), 24, valueOf(1200),
                valueOf(15),true,false), LocalDate.now(), "3231",
                new ArrayList<>(List.of(PREAPPROVAL, APPROVED)));
    }
}
