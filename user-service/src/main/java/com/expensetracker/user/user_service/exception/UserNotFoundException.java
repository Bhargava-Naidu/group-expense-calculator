package com.expensetracker.user.user_service.exception;

public class UserNotFoundException  extends RuntimeException{

    public UserNotFoundException(String message){
        super(message);
    }
}
