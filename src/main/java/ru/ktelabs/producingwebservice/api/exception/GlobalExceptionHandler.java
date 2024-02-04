package ru.ktelabs.producingwebservice.api.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import ru.ktelabs.producingwebservice.exception.NotFoundException;
import ru.ktelabs.producingwebservice.exception.NullUserException;
import ru.ktelabs.producingwebservice.exception.UsersNotFoundException;

@ControllerAdvice
@RestController
public class GlobalExceptionHandler {
    @ExceptionHandler
    public ResponseEntity<ApiValidationError> notFound(NotFoundException ex) {
        return new ResponseEntity<>(new ApiValidationError("", ex.getMessage()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<ApiValidationError> nullUser(NullUserException ex) {
        return new ResponseEntity<>(new ApiValidationError("", ex.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public ResponseEntity<ApiValidationError> usersNotFound(UsersNotFoundException ex) {
        return new ResponseEntity<>(new ApiValidationError("", ex.getMessage()),HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<Object> handleOtherExceptions(Exception ex) {
        return new ResponseEntity<>(new ApiValidationError("", ex.getMessage()),HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
