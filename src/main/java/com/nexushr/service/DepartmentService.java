package com.nexushr.service;

import com.nexushr.dto.department.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface DepartmentService {
    DepartmentResponseDto createDepartment(DepartmentRequestDto dto);
    Page<DepartmentResponseDto> getAllDepartments(String keyword, Pageable pageable);
    DepartmentStatsDto getDepartmentStats(Long departmentId);
    DepartmentResponseDto updateDepartment(Long id, DepartmentRequestDto dto);
    String bulkRaiseSalary(Long departmentId, DepartmentRaiseDto dto);
    String deleteDepartment(Long id);
}