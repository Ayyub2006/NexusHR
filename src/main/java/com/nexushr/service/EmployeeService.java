package com.nexushr.service;

import com.nexushr.dto.employee.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface EmployeeService {
    EmployeeResponseDto createEmployee(EmployeeRequestDto dto);
    Page<EmployeeResponseDto> getAllEmployees(Pageable pageable);
    Page<EmployeeResponseDto> searchEmployees(String name, Long departmentId, String skill, Pageable pageable);
    EmployeeResponseDto transferEmployee(Long employeeId, EmployeeTransferDto dto);
    EmployeeResponseDto promoteEmployee(Long employeeId, PromotionDto dto);
    LeaveBalanceDto getLeaveBalance(Long employeeId);
}