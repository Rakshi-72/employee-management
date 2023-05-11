package com.rakshi.employeemanagement.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rakshi.employeemanagement.models.Employee;

public interface EmployeeRepo extends JpaRepository<Employee, Integer> {

    /**
     * The function finds an employee by their email address and returns an optional
     * object.
     */
    Optional<Employee> findByEmail(String email);
}
