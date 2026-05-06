package com.nexushr.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.nexushr.entity.enums.LeaveStatus;
import com.nexushr.entity.enums.LeaveType;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "leave_requests")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LeaveRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private LeaveType leaveType;

    private LocalDate startDate;
    private LocalDate endDate;

    @Column(length = 1000)
    private String reason;

    @Enumerated(EnumType.STRING)
    private LeaveStatus status;

    @ManyToOne
    @JoinColumn(name = "employee_id")
    @JsonIgnore
    private Employee employee;
}