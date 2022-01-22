package com.example.kantor.repository;

import com.example.kantor.models.Login;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LoginRepository extends JpaRepository<Login, Integer> {
    Optional<Login> findLoginByUserName(String userName);
}
