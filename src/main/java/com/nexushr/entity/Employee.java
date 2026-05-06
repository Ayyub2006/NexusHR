package com.nexushr.entity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.nexushr.entity.enums.Gender;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "employees")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;
    private String lastName;

    @Column(nullable = false, unique = true)
    private String email;

    private String phone;
    private String designation;
    private Double salary;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    private String skill;
    private LocalDate joiningDate;

    private Integer casualLeaveBalance;
    private Integer paidLeaveBalance;

    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department;

    @OneToOne(mappedBy = "employee", cascade = CascadeType.ALL)
    private EmployeeProfile employeeProfile;

    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL)
    @JsonIgnore
    @Builder.Default
    private List<LeaveRequest> leaveRequests = new ArrayList<>();

    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL)
    @JsonIgnore
    @Builder.Default
    private List<ProjectAssignment> projectAssignments = new ArrayList<>();
}