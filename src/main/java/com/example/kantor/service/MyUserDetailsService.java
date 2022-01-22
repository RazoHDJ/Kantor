package com.example.kantor.service;

import com.example.kantor.models.Login;
import com.example.kantor.models.MyUserDetails;
import com.example.kantor.repository.LoginRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MyUserDetailsService implements UserDetailsService {

    final private LoginRepository loginRepository;

    public MyUserDetailsService(final LoginRepository loginRepository) {
        this.loginRepository = loginRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Login> login =  loginRepository.findLoginByUserName(username);

        login.orElseThrow(() -> new UsernameNotFoundException("Not found: " + username));

        return login.map(MyUserDetails::new).get();

    }
}
