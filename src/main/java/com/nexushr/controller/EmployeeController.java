package com.nexushr.controller;

import com.nexushr.dto.employee.*;
import com.nexushr.service.EmployeeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/employees")
@RequiredArgsConstructor
public class EmployeeController {
	 private final EmployeeService employeeService;

	    @PostMapping
	    public EmployeeResponseDto createEmployee(@Valid @RequestBody EmployeeRequestDto dto) {
	        return employeeService.createEmployee(dto);
	    }

	    @GetMapping
	    public Page<EmployeeResponseDto> getAllEmployees(
	            @RequestParam(defaultValue = "0") int page,
	            @RequestParam(defaultValue = "5") int size
	    ) {
	        Pageable pageable = PageRequest.of(page, size);
	        return employeeService.getAllEmployees(pageable);
	    }
	    
	    @GetMapping("/search")
	    public Page<EmployeeResponseDto> searchEmployees(
	            @RequestParam(required = false) String name,
	            @RequestParam(required = false) Long departmentId,
	            @RequestParam(required = false) String skill,
	            @RequestParam(defaultValue = "0") int page,
	            @RequestParam(defaultValue = "5") int size
	    ) {
	        Pageable pageable = PageRequest.of(page, size);
	        return employeeService.searchEmployees(name, departmentId, skill, pageable);
	    }
	    
	    @PutMapping("/{id}/transfer")
	    public EmployeeResponseDto transferEmployee(@PathVariable Long id, @Valid @RequestBody EmployeeTransferDto dto) {
	        return employeeService.transferEmployee(id, dto);
	    }

	    @PutMapping("/{id}/promotion")
	    public EmployeeResponseDto promoteEmployee(@PathVariable Long id, @Valid @RequestBody PromotionDto dto) {
	        return employeeService.promoteEmployee(id, dto);
	    }

	    @GetMapping("/{id}/leave-balance")
	    public LeaveBalanceDto getLeaveBalance(@PathVariable Long id) {
	        return employeeService.getLeaveBalance(id);
	    }
	    
}
