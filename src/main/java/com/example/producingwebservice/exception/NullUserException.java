package com.example.producingwebservice.exception;

public class NullUserException extends ApiException{
    public NullUserException(String message, String errorCode) {
        super(message, errorCode);
    }
}
