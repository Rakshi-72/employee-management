package com.rakshi.employeemanagement.services.impls;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.rakshi.employeemanagement.exceptions.EmployeeNotFoundException;
import com.rakshi.employeemanagement.models.Employee;
import com.rakshi.employeemanagement.models.Role;
import com.rakshi.employeemanagement.repositories.EmployeeRepo;
import com.rakshi.employeemanagement.repositories.RoleRepo;
import com.rakshi.employeemanagement.services.EmployeeService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepo repo;
    private final RoleRepo roleRepo;

    @Override
    public List<Employee> getAllEmployees() {
        return repo.findAll();
    }

    @Override
    public Employee getEmployeeByEmployeeId(Integer employeeId) {
        return repo.findById(employeeId).orElseThrow(() -> new EmployeeNotFoundException(employeeId));
    }

    @Override
    public Employee updateEmployeeByEmployeeId(Integer employeeId, Employee employee) {

        Employee fetchedEmployee = repo.findById(employeeId)
                .orElseThrow(() -> new EmployeeNotFoundException(employeeId));

        fetchedEmployee.setAge(employee.getAge());
        fetchedEmployee.setEmail(employee.getEmail());
        fetchedEmployee.setEmail(employee.getEmail());
        fetchedEmployee.setPassword(employee.getPassword());
        fetchedEmployee.setSalary(employee.getSalary());
        fetchedEmployee.setName(employee.getName());

        return repo.save(fetchedEmployee);

    }

    @Override
    public void deleteEmployee(Integer id) {
        repo.deleteById(id);
    }

    @Override
    public Employee searchEmployeeByEmail(String email) {
        return repo.findByEmail(email).orElseThrow(() -> new EmployeeNotFoundException(email));
    }

    /**
     * This function registers a new employee with a default role of "normal user"
     * and saves it to the
     * repository.
     * 
     * @param employee The employee parameter is an object of the Employee class
     *                 that contains information
     *                 about a new employee being registered.
     */
    @Override
    @ResponseStatus(HttpStatus.CREATED)
    public void registerEmployee(Employee employee) {

        Role normalUser = roleRepo.findById(1).get();

        employee.getRoles().add(normalUser);

        repo.save(employee);

    }

}
