package com.nexushr.dto.employee;

import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EmployeeTransferDto {
    @NotNull
    private Long newDepartmentId;
}