package com.jagcoder.employee_service.service;


import com.jagcoder.employee_service.dto.APIResponseDto;
import com.jagcoder.employee_service.dto.EmployeeDto;

public interface EmployeeService {
    EmployeeDto saveEmployee(EmployeeDto employeeDto);

    APIResponseDto getEmployeeById(Long employeeId);
}