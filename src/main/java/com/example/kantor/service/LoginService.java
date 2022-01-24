package com.example.kantor.service;

import com.example.kantor.dto.RegisterEmployeeDTO;
import com.example.kantor.models.Employee;
import com.example.kantor.models.Login;
import com.example.kantor.models.User;
import com.example.kantor.repository.EmployeeRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class LoginService {

    final private EmployeeRepository employeeRepository;

    public LoginService(final EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public Employee registerEmployee(RegisterEmployeeDTO newEmployee) {
        // szyfrowanie hasła
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        newEmployee.setPassword(passwordEncoder.encode(newEmployee.getPassword()));
        // zapisywanie pracownika + danych logowania do bazy danych
        return employeeRepository.save(
                Employee.builder()
                        .firstName(newEmployee.getFirstName())
                        .lastName(newEmployee.getLastName())
                        .login(Login.builder()
                                .userName(newEmployee.getUserName())
                                .password(newEmployee.getPassword())
                                .roles("ROLE_EMPLOYEE")
                                .active(true)
                                .build())
                        .build()
        );

    }


}
