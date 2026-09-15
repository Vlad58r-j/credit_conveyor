package com.vlad.project.database.entity;

import com.vlad.project.dto.enumStatus.Gender;
import com.vlad.project.dto.enumStatus.MaritalStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Client implements BaseEntity<Long>{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "last_name")
    private String lastname;

    @Column(name = "first_name")
    private String firstname;
    private String middleName;
    private LocalDate birthDate;

    @Column(unique = true)
    private String email;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Enumerated(EnumType.STRING)
    private MaritalStatus maritalStatus;

    private Integer dependentAmount;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "passport_id")
    private Passport passport;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "employment_id")
    private Employment employment;
}
