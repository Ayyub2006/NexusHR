package com.nexushr.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "employee_profiles")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EmployeeProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String panNumber;
    private String ssn;
    private String address;
    private String emergencyContact;

    @OneToOne
    @JoinColumn(name = "employee_id")
    @JsonIgnore
    private Employee employee;
}