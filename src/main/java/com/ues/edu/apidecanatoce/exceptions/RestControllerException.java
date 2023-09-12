package com.ues.edu.apidecanatoce.exceptions;

import com.ues.edu.apidecanatoce.dtos.MensajeRecord;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class RestControllerException {


    @ExceptionHandler(Exception.class)
    public ResponseEntity<MensajeRecord> handleException(Exception e){
        return ResponseEntity.internalServerError()
                .body(new MensajeRecord(e.getMessage()));
    }

    @ExceptionHandler(CustomException.class)
    public ResponseEntity<MensajeRecord> handleCustomException(CustomException e){
        return ResponseEntity.status(e.getStatus())
                .body(new MensajeRecord(e.getMessage()));
    }




    private String formatMessage(String message) {
        return message.substring(0, 1).toUpperCase() + message.substring(1).toLowerCase();
    }



    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<MensajeRecord> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        List<String> mensajes = new ArrayList<>();
        e.getBindingResult().getAllErrors().forEach(err -> mensajes.add(formatMessage(err.getDefaultMessage())));
        String formattedMessage = mensajes.stream().collect(Collectors.joining(". "));
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new MensajeRecord(formattedMessage));
    }

}
