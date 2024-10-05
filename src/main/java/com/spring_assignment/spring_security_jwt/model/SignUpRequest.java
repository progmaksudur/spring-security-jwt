package com.spring_assignment.spring_security_jwt.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SignUpRequest {

    private String title;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String password;
}
