package com.example.producingwebservice.exception;

public class UsersNotFoundException extends ApiException{
    public UsersNotFoundException(String message, String errorCode) {
        super(message, errorCode);
    }
}
