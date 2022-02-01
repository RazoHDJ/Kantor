package com.example.kantor.service;

import com.example.kantor.models.Employee;
import com.example.kantor.models.MyUserDetails;
import com.example.kantor.repository.EmployeeRepository;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SecurityService {

    final private EmployeeRepository employeeRepository;

    public SecurityService(final EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public Optional<Employee> getCurrentEmployee(Authentication authentication) {
        if (authentication == null)
            return Optional.empty();

        return employeeRepository.findById(((MyUserDetails) authentication.getPrincipal()).getEmployeeID());
    }

    public void saveEmployee(Employee emp) {
        employeeRepository.save(emp);
    }
}
