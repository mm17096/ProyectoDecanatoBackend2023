package com.ues.edu.apidecanatoce.exceptions;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestController
@ControllerAdvice
public class ResponseExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<ExceptionResponse> manejarTodasExcepciones(ModeloNotFoundException ex, WebRequest request) {
        ExceptionResponse er = new ExceptionResponse(LocalDateTime.now(), ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<ExceptionResponse>(er, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(ModeloNotFoundException.class)
    public final ResponseEntity<ExceptionResponse> manejarModeloException(ModeloNotFoundException ex, WebRequest request) {
        ExceptionResponse er = new ExceptionResponse(LocalDateTime.now(), ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<ExceptionResponse>(er, HttpStatus.NOT_FOUND);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String message = error.getDefaultMessage();
            errors.put(fieldName, message);
        });
        ExceptionResponse er = new ExceptionResponse(LocalDateTime.now(), errors.toString(), request.getDescription(false));
        return new ResponseEntity<Object>(er, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InvalidDataException.class)
    public ResponseEntity<?> invalidDataException(InvalidDataException ex, WebRequest 
    request) {
     List<FieldError> errors = ex.getResult().getFieldErrors();
     for (FieldError error : errors) {
     logger.error("Filed Name ::: " + error.getField() + "Error Message :::" + error.getDefaultMessage());
     }
     return CommonErrorHandler.fieldErrorResponse("Error", CommonErrorHandler.getFieldErrorResponse(ex.getResult()));
    }




}
