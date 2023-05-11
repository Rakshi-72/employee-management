package com.rakshi.employeemanagement.models;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Employee
 */

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer employeeId;

    @Size(min = 5, max = 20)
    private String name;

    @Email
    @Column(unique = true)
    private String email;
    private String password;

    @Min(value = 18)
    @Max(value = 55)
    private Integer age;
    private Double salary;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "employeeRoles", joinColumns = @JoinColumn(name = "empId"), inverseJoinColumns = @JoinColumn(name = "roleId"))
    private List<Role> roles;

}