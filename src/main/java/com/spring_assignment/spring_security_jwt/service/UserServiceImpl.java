package com.spring_assignment.spring_security_jwt.service;

import com.spring_assignment.spring_security_jwt.entity.User;
import com.spring_assignment.spring_security_jwt.exceptions.UserAlreadyExistException;
import com.spring_assignment.spring_security_jwt.model.SignUpRequest;
import com.spring_assignment.spring_security_jwt.model.UserDTO;
import com.spring_assignment.spring_security_jwt.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService{

    private ModelMapper modelMapper;
    private UserRepository repository;
    private PasswordEncoder passwordEncoder;

    public UserServiceImpl(ModelMapper modelMapper, UserRepository repository, PasswordEncoder passwordEncoder) {
        this.modelMapper = modelMapper;
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public String createUser(SignUpRequest request) {
        if(repository.existsByPhone(request.getPhone())){
            System.out.println("phone found");
            return "This phone already have account";
        }else if(repository.existsByEmail(request.getEmail())){
            System.out.println("email found");
            return "This email already have account";
        }else{
            System.out.println("I am here");
            request.setPassword(passwordEncoder.encode(request.getPassword()));
            User user= modelMapper.map(request,User.class);
            user.setRoles(List.of("USER"));
            repository.save(user);
            return "User Successfully Created";
        }


    }

    @Override
    public UserDTO getUser(String emailOrPhone) {
        User user = (emailOrPhone.contains("@")?repository.findByEmail(emailOrPhone):repository.findByPhone(emailOrPhone))
                .orElseThrow(()-> new UsernameNotFoundException("This username : "+emailOrPhone+" not found"));
        return modelMapper.map(user,UserDTO.class);
    }

    @Override
    public List<UserDTO> getAllUser() {
        return List.of();
    }
}
