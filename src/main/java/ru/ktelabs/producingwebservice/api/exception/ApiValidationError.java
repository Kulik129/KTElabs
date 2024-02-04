package ru.ktelabs.producingwebservice.api.exception;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.validation.FieldError;
@Getter
@Setter
@AllArgsConstructor
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class ApiValidationError {
    private String field;
    private String message;

    public static ApiValidationError mapToResponse(FieldError fieldError) {
        return new ApiValidationError(fieldError.getField(), fieldError.getDefaultMessage());
    }
}