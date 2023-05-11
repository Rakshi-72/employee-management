package com.rakshi.employeemanagement.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rakshi.employeemanagement.models.Role;

public interface RoleRepo extends JpaRepository<Role, Integer> {

}
