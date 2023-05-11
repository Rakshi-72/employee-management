package com.rakshi.employeemanagement.services;

import java.util.List;

import com.rakshi.employeemanagement.models.Employee;

public interface EmployeeService {

    List<Employee> getAllEmployees();

    void registerEmployee(Employee employee);

    Employee getEmployeeByEmployeeId(Integer employeeId);

    Employee updateEmployeeByEmployeeId(Integer employeeId, Employee employee);

    void deleteEmployee(Integer id);

    Employee searchEmployeeByEmail(String email);

}
