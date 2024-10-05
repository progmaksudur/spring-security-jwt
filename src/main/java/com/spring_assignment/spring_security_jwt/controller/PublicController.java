package com.spring_assignment.spring_security_jwt.controller;


import com.spring_assignment.spring_security_jwt.model.AuthRequest;
import com.spring_assignment.spring_security_jwt.model.SignUpRequest;
import com.spring_assignment.spring_security_jwt.security.JwtUtil;
import com.spring_assignment.spring_security_jwt.service.UserDetailsServiceImpl;
import com.spring_assignment.spring_security_jwt.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1")
public class PublicController {

    private AuthenticationManager authenticationManager;

    private UserDetailsServiceImpl userDetailsService;

    private UserService userService;

    private JwtUtil jwtUtil;

    public PublicController(AuthenticationManager authenticationManager, UserDetailsServiceImpl userDetailsService, UserService userService, JwtUtil jwtUtil) {
        this.authenticationManager = authenticationManager;
        this.userDetailsService = userDetailsService;
        this.userService = userService;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/auth/signup")
    public ResponseEntity<?> signupUser(@RequestBody SignUpRequest request){
        String message = userService.createUser(request);

        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @PostMapping("/auth/login")
    public ResponseEntity<?> login(@RequestBody AuthRequest request){
        try{
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getEmailOrPhone(), request.getPassword()));
            System.out.println(request.getEmailOrPhone());
            System.out.println(request.getPassword());
            UserDetails userDetails = userDetailsService.loadUserByUsername(request.getEmailOrPhone());
            String jwt = jwtUtil.generateToken(userDetails.getUsername());
            return new ResponseEntity<>(jwt, HttpStatus.OK);
        }catch (Exception e){
            System.out.println(e.toString());
            return new ResponseEntity<>("Incorrect username or password", HttpStatus.BAD_REQUEST);
        }

    }
}
