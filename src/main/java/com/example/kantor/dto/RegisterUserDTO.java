package com.example.kantor.dto;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class RegisterUserDTO {

    private String userName;
    private String password;
    private String firstName;
    private String lastName;
    private String phoneNumber;

}
