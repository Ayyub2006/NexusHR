package com.nexushr.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.nexushr.entity.enums.DepartmentStatus;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "departments")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    private String location;

    private Double budget;

    private String departmentHead;

    @Enumerated(EnumType.STRING)
    private DepartmentStatus status;

    @OneToMany(mappedBy = "department", cascade = CascadeType.ALL)
    @JsonIgnore
    @Builder.Default
    private List<Employee> employees = new ArrayList<>();
}