package com.jagcoder.employee_service.repository;

import com.jagcoder.employee_service.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}