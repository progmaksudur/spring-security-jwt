package com.spring_assignment.spring_security_jwt.controller;



import com.spring_assignment.spring_security_jwt.model.UserDTO;
import com.spring_assignment.spring_security_jwt.security.JwtUtil;
import com.spring_assignment.spring_security_jwt.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/user")
public class UserController {


    private UserService userService;
    private JwtUtil jwtUtil;

    public UserController(UserService userService, JwtUtil jwtUtil) {
        this.userService = userService;
        this.jwtUtil = jwtUtil;
    }


    @GetMapping("/getDetails")
    public ResponseEntity<UserDTO> getSingleUser(@RequestHeader(name="Authorization") String token){

        String userName= jwtUtil.extractUsernameBearerToken(token);


        return new ResponseEntity<>(userService.getUser(userName), HttpStatus.OK);
    }
}
