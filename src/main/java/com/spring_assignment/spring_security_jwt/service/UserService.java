package com.spring_assignment.spring_security_jwt.service;

import com.spring_assignment.spring_security_jwt.model.SignUpRequest;
import com.spring_assignment.spring_security_jwt.model.UserDTO;

import java.util.List;

public interface UserService {

    public String createUser(SignUpRequest request);

    public UserDTO getUser(String emailOrPhone);

    public List<UserDTO> getAllUser();






}
