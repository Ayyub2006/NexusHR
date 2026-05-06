package com.nexushr.dto.project;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProjectAssignmentDto {
    @NotNull
    private Long employeeId;

    @NotBlank
    private String roleInProject;
}