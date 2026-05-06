package com.nexushr.dto.department;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DepartmentStatsDto {
    private Long totalEmployees;
    private Double totalSalaryExpenditure;
    private Long maleCount;
    private Long femaleCount;
    private Long otherCount;
}