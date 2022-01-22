package com.example.kantor.service;

import com.example.kantor.dto.RegisterUserDTO;
import com.example.kantor.models.Login;
import com.example.kantor.models.User;
import com.example.kantor.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class LoginService {

    final private UserRepository userRepository;

    public LoginService(final UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User registerUser(RegisterUserDTO newUser) {
        // szyfrowanie hasła
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        newUser.setPassword(passwordEncoder.encode(newUser.getPassword()));
        // zapisywanie użytkownika + danych logowania do bazy danych
        return userRepository.save(
                User.builder()
                        .firstName(newUser.getFirstName())
                        .lastName(newUser.getLastName())
                        .phoneNumber(newUser.getPhoneNumber())
                        .login(Login.builder()
                                .userName(newUser.getUserName())
                                .password(newUser.getPassword())
                                .roles("ROLE_USER")
                                .active(true)
                                .build())
                        .build()
        );

    }


}
