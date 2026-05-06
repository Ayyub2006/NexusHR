package com.nexushr.dto.employee;

import java.time.LocalDate;

import org.antlr.v4.runtime.misc.NotNull;

import com.nexushr.entity.enums.Gender;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EmployeeRequestDto {

    @NotBlank
    private String firstName;

    @NotBlank
    private String lastName;

    @Email
    @NotBlank
    private String email;

    @NotBlank
    private String phone;

    @NotBlank
    private String designation;

    @NotNull
    @Positive
    private Double salary;

    @NotNull
    private Gender gender;

    @NotBlank
    private String skill;

    @NotNull
    private LocalDate joiningDate;

    @NotNull
    private Long departmentId;

    @NotBlank
    private String panNumber;

    @NotBlank
    private String ssn;

    @NotBlank
    private String address;

    @NotBlank
    private String emergencyContact;
}