package com.nexushr.dto.department;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DepartmentRequestDto {
    @NotBlank
    private String name;

    @NotBlank
    private String location;

    @NotNull
    @Positive
    private Double budget;

    @NotBlank
    private String departmentHead;
}