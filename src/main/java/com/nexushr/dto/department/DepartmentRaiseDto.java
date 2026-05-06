package com.nexushr.dto.department;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DepartmentRaiseDto {
    @NotNull
    @Positive
    private Double percentage;
}