package com.ues.edu.apidecanatoce.DataLoaders;

import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
public class Generalmethods {

    public String generarCodigo(){
        LocalDateTime fechaHoraActual = LocalDateTime.now();
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("ddMMyyyyHHmmss");
        String codigo = fechaHoraActual.format(formato);
        return codigo;
    }

}
