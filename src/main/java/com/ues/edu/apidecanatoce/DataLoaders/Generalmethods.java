package com.ues.edu.apidecanatoce.DataLoaders;

import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;

@Component
public class Generalmethods {

    public String generarCodigo() {
        LocalDateTime fechaHoraActual = LocalDateTime.now();
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("ddMMyyyyHHmmssSSS");
        String codigo = fechaHoraActual.format(formato);

        // Agregar milisegundos
        codigo += "_" + String.format("%03d", fechaHoraActual.getNano() / 1000000);

        // Agregar un hash de letras y caracteres aleatorios
        codigo += generarHashAleatorio(6); // Cambia 6 por el número de caracteres deseados

        return codigo;
    }

    private String generarHashAleatorio(int longitud) {
        String letrasMayusculas = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String letrasMinusculas = "abcdefghijklmnopqrstuvwxyz";
        String numeros = "0123456789";
        String caracteresEspeciales = "!@#$%^&*()-_+=<>?";

        StringBuilder hash = new StringBuilder();

        Random random = new Random();
        for (int i = 0; i < longitud; i++) {
            if (i % 4 == 0) {
                int index = random.nextInt(letrasMayusculas.length());
                hash.append(letrasMayusculas.charAt(index));
            } else if (i % 4 == 1) {
                int index = random.nextInt(letrasMinusculas.length());
                hash.append(letrasMinusculas.charAt(index));
            } else if (i % 4 == 2) {
                int index = random.nextInt(numeros.length());
                hash.append(numeros.charAt(index));
            } else {
                int index = random.nextInt(caracteresEspeciales.length());
                hash.append(caracteresEspeciales.charAt(index));
            }
        }

        return hash.toString();
    }

}
