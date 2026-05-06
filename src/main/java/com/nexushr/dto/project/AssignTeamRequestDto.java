package com.nexushr.dto.project;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AssignTeamRequestDto {
    @NotEmpty
    @Valid
    private List<ProjectAssignmentDto> assignments;
}