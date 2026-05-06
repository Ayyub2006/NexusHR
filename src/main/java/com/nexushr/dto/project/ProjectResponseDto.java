package com.nexushr.dto.project;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProjectResponseDto {
    private Long id;
    private String name;
    private String clientName;
    private LocalDate startDate;
    private LocalDate endDate;
    private String description;
}