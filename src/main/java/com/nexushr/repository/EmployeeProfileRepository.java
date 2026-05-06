package com.nexushr.repository;

import com.nexushr.entity.EmployeeProfile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeProfileRepository extends JpaRepository<EmployeeProfile, Long> {
}