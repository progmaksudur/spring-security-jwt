package com.spring_assignment.spring_security_jwt.model;


import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    private Long id;
    private String title;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String gender;
    private List<String> roles;
}
