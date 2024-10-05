package com.spring_assignment.spring_security_jwt.exceptions;

public class UserAlreadyExistException extends RuntimeException{

    public UserAlreadyExistException(String userName){
        super(String.format("This user id %s already exist", userName));
    }
}
