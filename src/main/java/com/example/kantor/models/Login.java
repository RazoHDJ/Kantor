package com.example.kantor.models;


import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "login")
public class Login {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank
    private String userName;
    @NotBlank
    private String password;
    private boolean active;
    @NotBlank
    private String roles;

    @OneToOne(mappedBy = "login")
    private Employee employee;

}
