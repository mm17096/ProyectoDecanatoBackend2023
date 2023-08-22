package com.ues.edu.apidecanatoce.DataLoaders;

import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
public class Generalmethods {

    public String generarCodigoAutor(){
        LocalDateTime fechaHoraActual = LocalDateTime.now();
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("ddMMyyyyHHmmss");
        String codigoAutor = fechaHoraActual.format(formato);
        return codigoAutor;
    }
    
}
