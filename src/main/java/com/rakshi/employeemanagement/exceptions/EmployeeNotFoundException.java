package com.rakshi.employeemanagement.exceptions;

public class EmployeeNotFoundException extends RuntimeException {
    public EmployeeNotFoundException(Integer employeeId) {
        super("employee with the given employeeId " + employeeId + " not found");
    }

    public EmployeeNotFoundException(String email) {
        super("employee with the given email " + email + " not found");
    }
}
