package com.example.kantor.controller;

import com.example.kantor.models.User;
import com.example.kantor.repository.UserRepository;
import com.example.kantor.service.SecurityService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("/user")
public class UserController {

    final private UserRepository userRepository;
    final private SecurityService securityService;

    public UserController(final UserRepository userRepository, final SecurityService securityService) {
        this.userRepository = userRepository;
        this.securityService = securityService;
    }
//
//    @PostMapping("/user")
//    public User updateUser(){
//
//    }



}
