package com.sparkminds.fresher_project_backend.exception;

import com.sparkminds.fresher_project_backend.constant.CommonValidationConstant;
import com.sparkminds.fresher_project_backend.payload.ResponsePayload;
import com.sparkminds.fresher_project_backend.utility.ResponsePayloadUtility;
import jakarta.validation.ConstraintViolationException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
@RequiredArgsConstructor
public class GlobalExceptionHandler {
    private final ResponsePayloadUtility responsePayloadUtility;

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ResponsePayload> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        ResponsePayload responsePayload = responsePayloadUtility.buildResponse(
                CommonValidationConstant.VALIDATION_EXCEPTION,
                HttpStatus.BAD_REQUEST,
                errors,
                CommonValidationConstant.VALIDATION_EXCEPTION
        );
        return new ResponseEntity<>(responsePayload, responsePayload.getStatus());
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ResponsePayload> handleConstraintViolationExceptions(ConstraintViolationException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getConstraintViolations().forEach(violation -> {
            String fieldName = violation.getPropertyPath().toString();
            String errorMessage = violation.getMessage();
            errors.put(fieldName, errorMessage);
        });

        ResponsePayload responsePayload = responsePayloadUtility.buildResponse(
                CommonValidationConstant.CONSTRAINT_EXCEPTION,
                HttpStatus.BAD_REQUEST,
                errors,
                CommonValidationConstant.CONSTRAINT_EXCEPTION
        );
        return new ResponseEntity<>(responsePayload, responsePayload.getStatus());
    }
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ResponsePayload> handleResourceNotFoundException(ResourceNotFoundException ex) {
        ResponsePayload responsePayload = responsePayloadUtility.buildResponse(
                ex.getMessage(),
                HttpStatus.NOT_FOUND,
                null,
                ex.getMessage()
        );
        return new ResponseEntity<>(responsePayload, responsePayload.getStatus());
    }
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ResponsePayload> handleAllExceptions(Exception ex) {
        ResponsePayload responsePayload = responsePayloadUtility.buildResponse(
                ex.getMessage(),
                HttpStatus.INTERNAL_SERVER_ERROR,
                null,
                ex.getMessage()
        );
        return new ResponseEntity<>(responsePayload, responsePayload.getStatus());
    }
}
