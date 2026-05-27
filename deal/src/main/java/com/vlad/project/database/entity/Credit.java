package com.vlad.project.database.entity;

import com.vlad.project.dto.enumStatus.CreditStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Credit implements BaseEntity<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private BigDecimal amount;
    private Integer term;
    private BigDecimal monthlyPayment;
    private BigDecimal rate;
    private BigDecimal psk;
    private String paymentSchedule;
    private Boolean isInsuranceEnabled;
    private Boolean isSalaryClient;

    @Enumerated(value = EnumType.STRING)
    private CreditStatus creditStatus;
}
