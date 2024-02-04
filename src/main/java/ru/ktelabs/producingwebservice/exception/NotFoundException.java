package ru.ktelabs.producingwebservice.exception;

public class NotFoundException extends  ApiException{
    public NotFoundException(String message, String errorCode) {
        super(message, errorCode);
    }
}
