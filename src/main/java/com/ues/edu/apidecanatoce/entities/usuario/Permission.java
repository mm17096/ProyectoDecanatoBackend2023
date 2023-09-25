package com.ues.edu.apidecanatoce.entities.usuario;


import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum Permission {

    ADMIN_LEER("admin:leer"),
    ADMIN_CREAR("admin:crear"),
    ADMIN_MODIFICAR("admin:modificar"),
    ADMIN_BUSCAR("admin:buscar"),

    DOCENTE_BUSCAR("docente:buscar"),
    DOCENTE_LEER("docente:leer"),
    ;

    @Getter
    private final String permission;

}
