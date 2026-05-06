package com.nexushr.service.impl;

import com.nexushr.dto.project.*;
import com.nexushr.entity.Employee;
import com.nexushr.entity.Project;
import com.nexushr.entity.ProjectAssignment;
import com.nexushr.exception.ResourceNotFoundException;
import com.nexushr.repository.EmployeeRepository;
import com.nexushr.repository.MilestoneRepository;
import com.nexushr.repository.ProjectAssignmentRepository;
import com.nexushr.repository.ProjectRepository;
import com.nexushr.service.ProjectService;
import com.nexushr.util.MapperUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProjectServiceImpl implements ProjectService {
	 private final ProjectRepository projectRepository;
	    private final EmployeeRepository employeeRepository;
	    private final ProjectAssignmentRepository assignmentRepository;
	    private final MilestoneRepository milestoneRepository;

	    @Override
	    public ProjectResponseDto createProject(ProjectRequestDto dto) {
	        Project project = Project.builder()
	                .name(dto.getName())
	                .clientName(dto.getClientName())
	                .startDate(dto.getStartDate())
	                .endDate(dto.getEndDate())
	                .description(dto.getDescription())
	                .build();
	        return MapperUtil.mapProject(projectRepository.save(project));
	    }

	    @Override
	    @Transactional
	    public String assignTeam(Long projectId, AssignTeamRequestDto dto) {
	        Project project = projectRepository.findById(projectId)
	                .orElseThrow(() -> new ResourceNotFoundException("Project not found with id: " + projectId));

	        for (ProjectAssignmentDto assignmentDto : dto.getAssignments()) {
	            Employee employee = employeeRepository.findById(assignmentDto.getEmployeeId())
	                    .orElseThrow(() -> new ResourceNotFoundException("Employee not found with id: " + assignmentDto.getEmployeeId()));

	            ProjectAssignment assignment = ProjectAssignment.builder()
	                    .employee(employee)
	                    .project(project)
	                    .roleInProject(assignmentDto.getRoleInProject())
	                    .build();
	            
	            assignmentRepository.save(assignment);
	        }

	        return "Team assigned successfully.";
	    }

	    @Override
	    @Transactional
	    public String removeEmployeeFromProject(Long projectId, Long employeeId) {
	        ProjectAssignment assignment = assignmentRepository.findByProjectIdAndEmployeeId(projectId, employeeId)
	                .orElseThrow(() -> new ResourceNotFoundException("Assignment not found for project and employee."));

	        assignmentRepository.delete(assignment);
	        return "Employee removed from project successfully.";
	    }

	    @Override
	    public List<MilestoneDto> getProjectBacklog(Long projectId) {
	        projectRepository.findById(projectId)
	                .orElseThrow(() -> new ResourceNotFoundException("Project not found with id: " + projectId));

	        return milestoneRepository.findByProjectId(projectId)
	                .stream()
	                .map(MapperUtil::mapMilestone)
	                .toList();
	    }
	}