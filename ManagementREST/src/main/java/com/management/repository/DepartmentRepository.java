package com.management.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.management.entity.Department;
import com.management.util.EStatus;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {
	Department findByIdAndStatus(Long id, EStatus status);
}
