package com.nexushr.specification;

import com.nexushr.entity.Employee;
import org.springframework.data.jpa.domain.Specification;

public class EmployeeSpecification {

    public static Specification<Employee> hasName(String name) {
        return (root, query, cb) -> {
            if (name == null || name.isBlank()) return null;
            String like = "%" + name.toLowerCase() + "%";
            return cb.or(
                    cb.like(cb.lower(root.get("firstName")), like),
                    cb.like(cb.lower(root.get("lastName")), like)
            );
        };
    }

    public static Specification<Employee> hasDepartmentId(Long departmentId) {
        return (root, query, cb) -> {
            if (departmentId == null) return null;
            return cb.equal(root.get("department").get("id"), departmentId);
        };
    }

    public static Specification<Employee> hasSkill(String skill) {
        return (root, query, cb) -> {
            if (skill == null || skill.isBlank()) return null;
            return cb.like(cb.lower(root.get("skill")), "%" + skill.toLowerCase() + "%");
        };
    }
}