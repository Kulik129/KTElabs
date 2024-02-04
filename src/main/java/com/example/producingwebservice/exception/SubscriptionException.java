package com.example.producingwebservice.exception;

public class SubscriptionException extends ApiException{
    public SubscriptionException(String message, String errorCode) {
        super(message, errorCode);
    }
}
