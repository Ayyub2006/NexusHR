package com.nexushr.dto.department;

import com.nexushr.entity.enums.DepartmentStatus;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DepartmentResponseDto {
    private Long id;
    private String name;
    private String location;
    private Double budget;
    private String departmentHead;
    private DepartmentStatus status;
}