package com.vlad.project.counter;

import com.vlad.project.dto.PaymentScheduleElement;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import static java.math.BigDecimal.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class PaymentScheduleCounterTest {

    @Test
    void checkPaymentScheduleWithCorrectData() {
        List<PaymentScheduleElement> paymentScheduleElements = PaymentScheduleCounter.paymentSchedule(
                valueOf(16), new BigDecimal("20000"), new BigDecimal("1815"), 12);

        assertEquals(new PaymentScheduleElement(1,
                LocalDate.now().plusMonths(1), valueOf(20000), valueOf(266.67),
                valueOf(1548.33), valueOf(18451.67)), paymentScheduleElements.getFirst());
    }

    @Test
    void checkPaymentScheduleWithMillion() {
        List<PaymentScheduleElement> paymentScheduleElements = PaymentScheduleCounter.paymentSchedule(
                valueOf(15), valueOf(1_000_000), valueOf(23790), 60);

        assertEquals(new PaymentScheduleElement(1,
                LocalDate.now().plusMonths(1), new BigDecimal("1000000"), new BigDecimal("12500.00"),
                new BigDecimal("11290.00"), new BigDecimal("988710.00")), paymentScheduleElements.getFirst());
    }
}
