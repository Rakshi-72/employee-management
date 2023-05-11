package com.rakshi.employeemanagement.services;

import java.util.ArrayList;

import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.rakshi.employeemanagement.models.Employee;
import com.rakshi.employeemanagement.models.Role;
import com.rakshi.employeemanagement.repositories.EmployeeRepo;
import com.rakshi.employeemanagement.repositories.RoleRepo;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class DataLoader implements CommandLineRunner {

    private final RoleRepo roleRepo;
    private final EmployeeRepo employeeRepo;
    private final PasswordEncoder encoder;

    @Override
    public void run(String... args) throws Exception {

        Role userRole = new Role(1, "ROLE_USER");
        Role adminRole = new Role(2, "ROLE_ADMIN");

        Employee adminEmployee = new Employee(2324759, "Rakshith", "2324759@tcs.com", encoder.encode("password"), 24,
                27500.75,
                new ArrayList<>());

        roleRepo.save(userRole);
        adminEmployee.getRoles().add(roleRepo.save(adminRole));

        log.info(employeeRepo.save(adminEmployee).toString());

    }

}
