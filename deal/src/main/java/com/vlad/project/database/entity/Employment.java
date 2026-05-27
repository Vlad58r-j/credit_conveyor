package com.vlad.project.database.entity;

import com.vlad.project.dto.enumStatus.EmploymentStatus;
import com.vlad.project.dto.enumStatus.Position;
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
public class Employment implements BaseEntity<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(value = EnumType.STRING)
    private EmploymentStatus employmentStatus;

    private String employerInn;
    private BigDecimal salary;

    @Enumerated(value = EnumType.STRING)
    private Position position;

    private Integer workExperienceTotal;
    private Integer workExperienceCurrent;

}
