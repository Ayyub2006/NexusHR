package com.nexushr.util;

import com.nexushr.dto.department.DepartmentResponseDto;
import com.nexushr.dto.employee.EmployeeResponseDto;
import com.nexushr.dto.leave.LeaveResponseDto;
import com.nexushr.dto.project.MilestoneDto;
import com.nexushr.dto.project.ProjectResponseDto;
import com.nexushr.entity.Department;
import com.nexushr.entity.Employee;
import com.nexushr.entity.LeaveRequest;
import com.nexushr.entity.Milestone;
import com.nexushr.entity.Project;

public class MapperUtil {

    public static EmployeeResponseDto mapEmployee(Employee employee) {
        return EmployeeResponseDto.builder()
                .id(employee.getId())
                .firstName(employee.getFirstName())
                .lastName(employee.getLastName())
                .email(employee.getEmail())
                .phone(employee.getPhone())
                .designation(employee.getDesignation())
                .salary(employee.getSalary())
                .gender(employee.getGender())
                .skill(employee.getSkill())
                .joiningDate(employee.getJoiningDate())
                .departmentName(employee.getDepartment() != null ? employee.getDepartment().getName() : null)
                .panNumber(employee.getEmployeeProfile() != null ? employee.getEmployeeProfile().getPanNumber() : null)
                .ssn(employee.getEmployeeProfile() != null ? employee.getEmployeeProfile().getSsn() : null)
                .address(employee.getEmployeeProfile() != null ? employee.getEmployeeProfile().getAddress() : null)
                .emergencyContact(employee.getEmployeeProfile() != null ? employee.getEmployeeProfile().getEmergencyContact() : null)
                .build();
    }

    public static DepartmentResponseDto mapDepartment(Department department) {
        return DepartmentResponseDto.builder()
                .id(department.getId())
                .name(department.getName())
                .location(department.getLocation())
                .budget(department.getBudget())
                .departmentHead(department.getDepartmentHead())
                .status(department.getStatus())
                .build();
    }

    public static ProjectResponseDto mapProject(Project project) {
        return ProjectResponseDto.builder()
                .id(project.getId())
                .name(project.getName())
                .clientName(project.getClientName())
                .startDate(project.getStartDate())
                .endDate(project.getEndDate())
                .description(project.getDescription())
                .build();
    }

    public static MilestoneDto mapMilestone(Milestone milestone) {
        return MilestoneDto.builder()
                .id(milestone.getId())
                .title(milestone.getTitle())
                .description(milestone.getDescription())
                .dueDate(milestone.getDueDate())
                .status(milestone.getStatus())
                .build();
    }

    public static LeaveResponseDto mapLeave(LeaveRequest leave) {
        return LeaveResponseDto.builder()
                .id(leave.getId())
                .employeeId(leave.getEmployee().getId())
                .employeeName(leave.getEmployee().getFirstName() + " " + leave.getEmployee().getLastName())
                .leaveType(leave.getLeaveType())
                .startDate(leave.getStartDate())
                .endDate(leave.getEndDate())
                .reason(leave.getReason())
                .status(leave.getStatus())
                .build();
    }
}