package com.example.kantor.dto;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class RegisterEmployeeDTO {

    private String userName;
    private String password;
    private String firstName;
    private String lastName;

}
