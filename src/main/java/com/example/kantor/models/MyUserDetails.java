package com.example.kantor.models;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serial;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class MyUserDetails implements UserDetails, Serializable {

    @Serial
    private static final long serialVersionUID = 6529685098267757690L;

    private Integer employeeID;
    private String userName;
    private String password;
    private boolean active;
    private List<GrantedAuthority> authorities;

    public MyUserDetails(Login login) {
        this.employeeID = login.getEmployee().getId();
        this.userName = login.getUserName();
        this.password = login.getPassword();
        this.active = login.isActive();
        this.authorities = Arrays.stream(login.getRoles().split(",")).map(SimpleGrantedAuthority::new).collect(Collectors.toList());

    }

    public MyUserDetails() {}

    public Integer getEmployeeID() {
        return employeeID;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return userName;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return active;
    }
}
