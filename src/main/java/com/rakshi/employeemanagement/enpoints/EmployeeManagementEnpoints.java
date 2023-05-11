package com.rakshi.employeemanagement.enpoints;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.rakshi.employeemanagement.models.Employee;
import com.rakshi.employeemanagement.services.EmployeeService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class EmployeeManagementEnpoints {

    private final EmployeeService service;

    @GetMapping("/employees")
    public ResponseEntity<List<Employee>> getEmployees() {
        return ResponseEntity.ok().body(service.getAllEmployees());
    }

    @GetMapping("/employees/{id}")
    public ResponseEntity<Employee> getEmployeeByEmpId(@PathVariable Integer id) {
        return ResponseEntity.ok(service.getEmployeeByEmployeeId(id));
    }

    @PostMapping("/employee")
    public void addEmployee(@RequestBody Employee employee) {
        service.registerEmployee(employee);
    }

}
