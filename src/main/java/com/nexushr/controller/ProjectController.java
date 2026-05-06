package com.nexushr.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nexushr.dto.project.AssignTeamRequestDto;
import com.nexushr.dto.project.MilestoneDto;
import com.nexushr.dto.project.ProjectRequestDto;
import com.nexushr.dto.project.ProjectResponseDto;
import com.nexushr.service.ProjectService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/projects")
@RequiredArgsConstructor
public class ProjectController {

    private final ProjectService projectService;
    @PostMapping
    public ProjectResponseDto createProject(@Valid @RequestBody ProjectRequestDto dto) {
        return projectService.createProject(dto);
    }

    @PostMapping("/{projectId}/assign")
    public String assignTeam(@PathVariable Long projectId, @Valid @RequestBody AssignTeamRequestDto dto) {
        return projectService.assignTeam(projectId, dto);
    }

    @DeleteMapping("/{projectId}/employees/{employeeId}")
    public String removeEmployeeFromProject(@PathVariable Long projectId, @PathVariable Long employeeId) {
        return projectService.removeEmployeeFromProject(projectId, employeeId);
    }
    
    @GetMapping("/{id}/backlog")
    public List<MilestoneDto> getProjectBacklog(@PathVariable Long id) {
        return projectService.getProjectBacklog(id);
    }
}