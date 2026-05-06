package com.nexushr.service.impl;

import com.nexushr.dto.employee.*;
import com.nexushr.entity.Department;
import com.nexushr.entity.Employee;
import com.nexushr.entity.EmployeeProfile;
import com.nexushr.exception.ResourceNotFoundException;
import com.nexushr.repository.DepartmentRepository;
import com.nexushr.repository.EmployeeRepository;
import com.nexushr.service.EmployeeService;
import com.nexushr.specification.EmployeeSpecification;
import com.nexushr.util.MapperUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final DepartmentRepository departmentRepository;

    @Override
    public EmployeeResponseDto createEmployee(EmployeeRequestDto dto) {
        Department department = departmentRepository.findById(dto.getDepartmentId())
                .orElseThrow(() -> new ResourceNotFoundException("Department not found with id: " + dto.getDepartmentId()));

        Employee employee = Employee.builder()
                .firstName(dto.getFirstName())
                .lastName(dto.getLastName())
                .email(dto.getEmail())
                .phone(dto.getPhone())
                .designation(dto.getDesignation())
                .salary(dto.getSalary())
                .gender(dto.getGender())
                .skill(dto.getSkill())
                .joiningDate(dto.getJoiningDate())
                .casualLeaveBalance(10)
                .paidLeaveBalance(20)
                .department(department)
                .build();

        EmployeeProfile profile = EmployeeProfile.builder()
                .panNumber(dto.getPanNumber())
                .ssn(dto.getSsn())
                .address(dto.getAddress())
                .emergencyContact(dto.getEmergencyContact())
                .employee(employee)
                .build();

        employee.setEmployeeProfile(profile);
        return MapperUtil.mapEmployee(employeeRepository.save(employee));
}
    @Override
    public Page<EmployeeResponseDto> getAllEmployees(Pageable pageable) {
        return employeeRepository.findAll(pageable).map(MapperUtil::mapEmployee);
    }

    @Override
    public Page<EmployeeResponseDto> searchEmployees(String name, Long departmentId, String skill, Pageable pageable) {
        Specification<Employee> spec = Specification.where(EmployeeSpecification.hasName(name))
                .and(EmployeeSpecification.hasDepartmentId(departmentId))
                .and(EmployeeSpecification.hasSkill(skill));

        return employeeRepository.findAll(spec, pageable).map(MapperUtil::mapEmployee);
    }
    
    @Override
    @Transactional
    public EmployeeResponseDto transferEmployee(Long employeeId, EmployeeTransferDto dto) {
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found with id: " + employeeId));

        Department department = departmentRepository.findById(dto.getNewDepartmentId())
                .orElseThrow(() -> new ResourceNotFoundException("Department not found with id: " + dto.getNewDepartmentId()));

        employee.setDepartment(department);
        return MapperUtil.mapEmployee(employeeRepository.save(employee));
    }
    
    @Override
    @Transactional
    public EmployeeResponseDto promoteEmployee(Long employeeId, PromotionDto dto) {
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found with id: " + employeeId));

        employee.setDesignation(dto.getDesignation());
        employee.setSalary(dto.getSalary());
        return MapperUtil.mapEmployee(employeeRepository.save(employee));
    }
    
    @Override
    public LeaveBalanceDto getLeaveBalance(Long employeeId) {
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found with id: " + employeeId));

        return LeaveBalanceDto.builder()
                .employeeId(employee.getId())
                .casualLeaveRemaining(employee.getCasualLeaveBalance())
                .paidLeaveRemaining(employee.getPaidLeaveBalance())
                .build();
    }
}