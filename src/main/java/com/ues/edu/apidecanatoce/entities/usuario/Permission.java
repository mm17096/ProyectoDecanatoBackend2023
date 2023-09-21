package com.ues.edu.apidecanatoce.entities.usuario;


import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum Permission {

    ADMIN_LEER("admin:leer"),
    ADMIN_CREAR("admin:crear"),

    ADMIN_MODIFICAR("admin:modificar"),

    ADMIN_BUSCAR("admin:buscar"),
    ;

    @Getter
    private final String permission;

}
