package com.vlad.project.database.entity;

import com.vlad.project.dto.enumStatus.ApplicationStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.time.LocalDate;
import java.util.List;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Application implements BaseEntity<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "client_id")
    private Client client;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "credit_id")
    private Credit credit;

    @Enumerated(value = EnumType.STRING)
    private ApplicationStatus status;

    private LocalDate creationDate;

    @JdbcTypeCode(SqlTypes.JSON)
    private AppliedOffer appliedOffer;
    private LocalDate signDate;
    private String sesCode;

    @Enumerated(value = EnumType.STRING)
    private List<ApplicationStatus> statusHistory;
}
