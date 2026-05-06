package com.nexushr.repository;

import com.nexushr.entity.ProjectAssignment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProjectAssignmentRepository extends JpaRepository<ProjectAssignment, Long> {
    Optional<ProjectAssignment> findByProjectIdAndEmployeeId(Long projectId, Long employeeId);
}