package com.nexushr.dto.employee;

import com.nexushr.entity.enums.Gender;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EmployeeResponseDto {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String designation;
    private Double salary;
    private Gender gender;
    private String skill;
    private LocalDate joiningDate;
    private String departmentName;
    private String panNumber;
    private String ssn;
    private String address;
    private String emergencyContact;
}