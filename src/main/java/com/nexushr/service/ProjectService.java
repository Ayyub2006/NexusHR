package com.nexushr.service;

import com.nexushr.dto.project.*;

import java.util.List;

public interface ProjectService {
    ProjectResponseDto createProject(ProjectRequestDto dto);
    String assignTeam(Long projectId, AssignTeamRequestDto dto);
    String removeEmployeeFromProject(Long projectId, Long employeeId);
    List<MilestoneDto> getProjectBacklog(Long projectId);
}