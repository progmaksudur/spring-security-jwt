package com.spring_assignment.spring_security_jwt.service;

import com.spring_assignment.spring_security_jwt.entity.User;
import com.spring_assignment.spring_security_jwt.repository.UserRepository;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private UserRepository repository;

    public UserDetailsServiceImpl(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = (username.contains("@")?repository.findByEmail(username):repository.findByPhone(username))
                .orElseThrow(()-> new UsernameNotFoundException("User not found with username: " + username));


        return  org.springframework.security.core.userdetails.User.builder()
                .username(user.getEmail())
                .password(user.getPassword())
                .roles(user.getRoles().toArray(new String[0]))
                .build();

    }
}
