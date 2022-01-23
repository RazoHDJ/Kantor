package com.example.kantor.service;

import com.example.kantor.models.MyUserDetails;
import com.example.kantor.models.User;
import com.example.kantor.repository.UserRepository;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SecurityService {

    final private UserRepository userRepository;

    public SecurityService(final UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Optional<User> getCurrentUser(Authentication authentication) {
        if (authentication == null)
            return Optional.empty();

        return userRepository.findById(((MyUserDetails) authentication.getPrincipal()).getUserID());
    }

}
