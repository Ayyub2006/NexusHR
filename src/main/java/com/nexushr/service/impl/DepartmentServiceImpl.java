package com.nexushr.service.impl;

import com.nexushr.dto.department.*;
import com.nexushr.entity.Department;
import com.nexushr.entity.Employee;
import com.nexushr.entity.enums.DepartmentStatus;
import com.nexushr.entity.enums.Gender;
import com.nexushr.exception.BadRequestException;
import com.nexushr.exception.ResourceNotFoundException;
import com.nexushr.repository.DepartmentRepository;
import com.nexushr.repository.EmployeeRepository;
import com.nexushr.service.DepartmentService;
import com.nexushr.util.MapperUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class DepartmentServiceImpl implements DepartmentService {
	 private final DepartmentRepository departmentRepository;
	    private final EmployeeRepository employeeRepository;

	    @Override
	    public DepartmentResponseDto createDepartment(DepartmentRequestDto dto) {
	        Department department = Department.builder()
	                .name(dto.getName())
	                .location(dto.getLocation())
	                .budget(dto.getBudget())
	                .departmentHead(dto.getDepartmentHead())
	                .status(DepartmentStatus.ACTIVE)
	                .build();

	        return MapperUtil.mapDepartment(departmentRepository.save(department));
	    }
	    
	    @Override
	    public Page<DepartmentResponseDto> getAllDepartments(String keyword, Pageable pageable) {
	        String value = keyword == null ? "" : keyword;
	        return departmentRepository
	                .findByNameContainingIgnoreCaseOrLocationContainingIgnoreCase(value, value, pageable)
	                .map(MapperUtil::mapDepartment);
	    }

	    @Override
	    public DepartmentStatsDto getDepartmentStats(Long departmentId) {
	        Department department = departmentRepository.findById(departmentId)
	                .orElseThrow(() -> new ResourceNotFoundException("Department not found with id: " + departmentId));
	        long totalEmployees = department.getEmployees().size();
	        double totalSalary = department.getEmployees().stream().mapToDouble(Employee::getSalary).sum();
	        long male = department.getEmployees().stream().filter(e -> e.getGender() == Gender.MALE).count();
	        long female = department.getEmployees().stream().filter(e -> e.getGender() == Gender.FEMALE).count();
	        long other = department.getEmployees().stream().filter(e -> e.getGender() == Gender.OTHER).count();

	        return DepartmentStatsDto.builder()
	                .totalEmployees(totalEmployees)
	                .totalSalaryExpenditure(totalSalary)
	                .maleCount(male)
	                .femaleCount(female)
	                .otherCount(other)
	                .build();
	    }
	   
	    @Override
	    public DepartmentResponseDto updateDepartment(Long id, DepartmentRequestDto dto) {
	        Department department = departmentRepository.findById(id)
	                .orElseThrow(() -> new ResourceNotFoundException("Department not found with id: " + id));

	        department.setName(dto.getName());
	        department.setLocation(dto.getLocation());
	        department.setBudget(dto.getBudget());
	        department.setDepartmentHead(dto.getDepartmentHead());

	        return MapperUtil.mapDepartment(departmentRepository.save(department));
	    }

	    @Override
	    @Transactional
	    public String bulkRaiseSalary(Long departmentId, DepartmentRaiseDto dto) {
	        Department department = departmentRepository.findById(departmentId)
	                .orElseThrow(() -> new ResourceNotFoundException("Department not found with id: " + departmentId));

	        for (Employee employee : department.getEmployees()) {
	            double newSalary = employee.getSalary() + (employee.getSalary() * dto.getPercentage() / 100);
	            employee.setSalary(newSalary);
	        }

	        return "Salary updated successfully for department: " + department.getName();
	    }

	    @Override
	    public String deleteDepartment(Long id) {
	        Department department = departmentRepository.findById(id)
	                .orElseThrow(() -> new ResourceNotFoundException("Department not found with id: " + id));

	        if (employeeRepository.existsByDepartmentId(id)) {
	            throw new BadRequestException("Cannot delete department because employees are still assigned.");
	        }

	        department.setStatus(DepartmentStatus.INACTIVE);
	        departmentRepository.save(department);
	        return "Department deactivated successfully.";
	    }
	}