package com.nexushr.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nexushr.dto.department.DepartmentRaiseDto;
import com.nexushr.dto.department.DepartmentRequestDto;
import com.nexushr.dto.department.DepartmentResponseDto;
import com.nexushr.dto.department.DepartmentStatsDto;
import com.nexushr.service.DepartmentService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/departments")
@RequiredArgsConstructor
public class DepartmentController {
	 private final DepartmentService departmentService;

	    @PostMapping
	    public DepartmentResponseDto createDepartment(@Valid @RequestBody DepartmentRequestDto dto) {
	        return departmentService.createDepartment(dto);
	    }

	    @GetMapping
	    public Page<DepartmentResponseDto> getAllDepartments(
	            @RequestParam(required = false) String keyword,
	            @RequestParam(defaultValue = "0") int page,
	            @RequestParam(defaultValue = "5") int size
	    ) {
	        Pageable pageable = PageRequest.of(page, size);
	        return departmentService.getAllDepartments(keyword, pageable);
	    }

	    @GetMapping("/{id}/stats")
	    public DepartmentStatsDto getDepartmentStats(@PathVariable Long id) {
	        return departmentService.getDepartmentStats(id);
	    }

	    @PutMapping("/{id}")
	    public DepartmentResponseDto updateDepartment(@PathVariable Long id, @Valid @RequestBody DepartmentRequestDto dto) {
	        return departmentService.updateDepartment(id, dto);
	    }

	    @PutMapping("/{id}/raise")
	    public String bulkRaiseSalary(@PathVariable Long id, @Valid @RequestBody DepartmentRaiseDto dto) {
	        return departmentService.bulkRaiseSalary(id, dto);
	    }
	    
	    @DeleteMapping("/{id}")
	    public String deleteDepartment(@PathVariable Long id) {
	        return departmentService.deleteDepartment(id);
	    }
	}